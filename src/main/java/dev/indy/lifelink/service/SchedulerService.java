package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.NotOwnerOfEntityException;
import dev.indy.lifelink.model.Medicine;
import dev.indy.lifelink.model.MedicineSchedule;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Person;
import dev.indy.lifelink.model.request.AddMedicineScheduleRequest;
import dev.indy.lifelink.model.response.ScheduledTaskResponse;
import dev.indy.lifelink.repository.MedicineScheduleLogRepository;
import dev.indy.lifelink.repository.MedicineScheduleRepository;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerService {
    private static final Logger log = LoggerFactory.getLogger(SchedulerService.class);
    private final TaskScheduler _taskScheduler;
    private final Map<Long, ScheduledFuture<?>> _userTasks;
    private final AuthService _authService;
    private final MedicineService _medicineService;
    private final EmailService _emailService;
    private final MedicineScheduleRepository _scheduleRepository;
    private final MedicineScheduleLogRepository _scheduleLogRepository;

    @Autowired
    public SchedulerService(
        @Qualifier("taskScheduler") TaskScheduler taskScheduler,
        AuthService authService,
        MedicineService medicineService,
        EmailService emailService,
        MedicineScheduleRepository scheduleRepository,
        MedicineScheduleLogRepository scheduleLogRepository
    ) {
        this._taskScheduler = taskScheduler;
        this._authService = authService;
        this._medicineService = medicineService;
        this._emailService = emailService;
        this._scheduleRepository = scheduleRepository;
        this._scheduleLogRepository = scheduleLogRepository;
        this._userTasks = new ConcurrentHashMap<>();
    }

    public MedicineSchedule addSchedule(HttpSession session, AddMedicineScheduleRequest body) throws EntityNotFoundException {
        Patient patient = this._authService.getActivePatient(session);
        Medicine medicine = this._medicineService.getMedicine(session, body.medicineId());

        MedicineSchedule schedule = new MedicineSchedule(
            patient,
            medicine,
            body.weekDays(),
            body.executionTime(),
            body.notes(),
            body.dosage()
        );

        this._scheduleRepository.save(schedule);
        this.scheduleMedicineReminder(schedule);

        return schedule;
    }

    public MedicineSchedule getSchedule(HttpSession session, long id) throws EntityNotFoundException {
        MedicineSchedule schedule = this._scheduleRepository.findByScheduleId(id);
        if(schedule == null) throw new EntityNotFoundException(MedicineSchedule.class, id);

        Patient patient = this._authService.getActivePatient(session);
        if(schedule.getPatient().getPatientId() != patient.getPatientId())
            throw new NotOwnerOfEntityException(MedicineSchedule.class);

        return schedule;
    }

    public MedicineSchedule updateSchedule(HttpSession session, long id, AddMedicineScheduleRequest body) throws EntityNotFoundException {
        MedicineSchedule schedule = this.getSchedule(session, id);

        if(body.weekDays() != null) schedule.setWeekDays(body.weekDays());
        if(body.executionTime() != null) schedule.setExecutionTime(body.executionTime());
        if(body.notes() != null) schedule.setNotes(body.notes());
        if(body.dosage() != null) schedule.setDosage(body.dosage());
        if(body.medicineId() != null) {
            Medicine medicine = this._medicineService.getMedicine(session, body.medicineId());
            schedule.setMedicine(medicine);
        }

        return this._scheduleRepository.save(schedule);
    }

    public void deleteSchedule(HttpSession session, long id) throws EntityNotFoundException {
        MedicineSchedule schedule = this.getSchedule(session, id);

        this.cancelTask(schedule);
        this._scheduleRepository.delete(schedule);
    }

    public Page<MedicineSchedule> listPatientSchedules(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._scheduleRepository.findAllByPatient(patient, pageable);
    }

    public List<MedicineSchedule> listPatientSchedulesBetween(Patient patient, LocalTime lower, LocalTime upper) {
        return this._scheduleRepository.findMedicineSchedulesBetween(patient, lower, upper);
    }

    public List<MedicineSchedule> listSchedules() {
        return this._scheduleRepository.findAll();
    }

    public void scheduleTask(MedicineSchedule schedule, Runnable task) {
        String cron = Util.timeToCron(schedule.getExecutionTime(), schedule.getWeekDays());
        ScheduledFuture<?> future = this._taskScheduler.schedule(task, new CronTrigger(cron));

        this._userTasks.put(schedule.getScheduleId(), future);
    }

    public void cancelTask(MedicineSchedule schedule) {
        ScheduledFuture<?> future = this._userTasks.remove(schedule.getScheduleId());

        if(future != null) future.cancel(false);
    }

    public void scheduleMedicineReminder(MedicineSchedule schedule) {
        Runnable task = () -> {
            LocalDate today = LocalDate.now();
            if(this._scheduleLogRepository.existsMedicineScheduleLogByDate(schedule, today)) return;

            Patient patient = schedule.getPatient();
            Person peron = patient.getPerson();
            Person contactPerson = patient.getContactPerson();

            this._emailService.sendMedicineReminder(peron.getEmail(), schedule);
            this._emailService.notifyAboutMedicineNotTaken(contactPerson.getEmail(), schedule);
        };

        this.scheduleTask(schedule, task);
    }

    public List<ScheduledTaskResponse> getScheduledTasks() {
        List<ScheduledTaskResponse> tasks = new ArrayList<>();

        for(long key : this._userTasks.keySet()) {
            MedicineSchedule schedule = this._scheduleRepository.findByScheduleId(key);
            tasks.add(new ScheduledTaskResponse(schedule.getPatient().getPatientId(), schedule));
        }

        return tasks;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void restoreScheduledTasks() {
        List<MedicineSchedule> schedules = this.listSchedules();
        for(MedicineSchedule schedule : schedules) this.scheduleMedicineReminder(schedule);
    }
}