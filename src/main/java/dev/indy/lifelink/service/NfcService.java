package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.exception.InvalidAuthenticationCredentialsException;
import dev.indy.lifelink.model.MedicineSchedule;
import dev.indy.lifelink.model.MedicineScheduleLog;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.request.RegisterNfcTagRequest;
import dev.indy.lifelink.repository.MedicineScheduleLogRepository;
import dev.indy.lifelink.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class NfcService {
    private static final int GRACE_PERIOD_BEFORE = 15;
    private static final int GRACE_PERIOD_AFTER = 15;

    private final AuthService _authService;
    private final SchedulerService _schedulerService;
    private final EmailService _emailService;
    private final PatientRepository _patientRepository;
    private final MedicineScheduleLogRepository _scheduleLogRepository;

    @Autowired
    public NfcService(
        AuthService authService, SchedulerService schedulerService, EmailService emailService,
        PatientRepository patientRepository, MedicineScheduleLogRepository scheduleLogRepository
    ) {
        this._authService = authService;
        this._schedulerService = schedulerService;
        this._emailService = emailService;
        this._patientRepository = patientRepository;
        this._scheduleLogRepository = scheduleLogRepository;
    }

    public void registerDispenserNfcTag(RegisterNfcTagRequest body) throws InvalidAuthenticationCredentialsException {
        final Patient patient = this._authService.getPatientByPesel(body.pesel());
        if(patient == null || !this._authService.verifyPassword(body.password(), patient.getPasswordHash()))
            throw new InvalidAuthenticationCredentialsException();

        if(this._authService.userWithDispenserNfcTagExists(body.nfcTagUid()))
            throw new HttpException(HttpStatus.CONFLICT, "NFC tag is already registered to another patient.");

        patient.setDispenserNfcTagHash(this._authService.hash(body.nfcTagUid()));

        this._patientRepository.save(patient);
    }

    public boolean verifyDispenserNfcTag(String nfcTagUid) {
        // Implementation for verifying dispenser NFC tag goes here
        Patient patient = this._authService.getPatientByDispenserNfcTag(nfcTagUid);
        if(patient == null) return false;

        LocalTime now = LocalTime.now();
        LocalTime lowerBound = now.minusMinutes(GRACE_PERIOD_BEFORE);
        LocalTime upperBound = now.plusMinutes(GRACE_PERIOD_AFTER);
        List<MedicineSchedule> schedules = this._schedulerService.listPatientSchedulesBetween(patient, lowerBound, upperBound);
        if(schedules.isEmpty()) return false;

        LocalDate today = LocalDate.now();
        boolean valid = false;
        for(MedicineSchedule schedule : schedules) {
            if(this._scheduleLogRepository.existsMedicineScheduleLogByDate(schedule, today)) continue;

            MedicineScheduleLog log = new MedicineScheduleLog(schedule);
            this._scheduleLogRepository.save(log);
            this._emailService.notifyAboutMedicineTaken(schedule);

            valid = true;
        }

        return valid;
    }
}
