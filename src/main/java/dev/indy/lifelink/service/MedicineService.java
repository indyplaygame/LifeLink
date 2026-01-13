package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityHasRelatedDataException;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.NotOwnerOfEntityException;
import dev.indy.lifelink.model.Allergy;
import dev.indy.lifelink.model.Medicine;
import dev.indy.lifelink.model.MedicineSchedule;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.request.AddMedicineRequest;
import dev.indy.lifelink.repository.MedicineRepository;
import dev.indy.lifelink.repository.MedicineScheduleRepository;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository _medicineRepository;
    private final MedicineScheduleRepository _scheduleRepository;
    private final AuthService _authService;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository, MedicineScheduleRepository scheduleRepository, AuthService authService) {
        this._medicineRepository = medicineRepository;
        this._scheduleRepository = scheduleRepository;
        this._authService = authService;
    }

    public Medicine addMedicine(HttpSession session, AddMedicineRequest body) {
        Patient patient = this._authService.getActivePatient(session);

        Medicine medicine = new Medicine(
            body.name(),
            body.notes(),
            patient
        );

        return this._medicineRepository.save(medicine);
    }

    public Medicine getMedicine(HttpSession session, long id) throws EntityNotFoundException {
        Medicine medicine = this._medicineRepository.findByMedicineId(id);
        if(medicine == null) throw new EntityNotFoundException(Medicine.class, id);

        Patient patient = this._authService.getActivePatient(session);
        if(medicine.getPatient().getPatientId() != patient.getPatientId())
            throw new NotOwnerOfEntityException(Medicine.class);

        return medicine;
    }

    public Medicine updateMedicine(HttpSession session, long id, AddMedicineRequest body) throws EntityNotFoundException {
        Medicine medicine = this.getMedicine(session, id);

        if(body.name() != null) medicine.setMedicineName(body.name());
        if(body.notes() != null) medicine.setNotes(body.notes());

        return this._medicineRepository.save(medicine);
    }

    public void deleteMedicine(HttpSession session, long id) throws EntityNotFoundException, EntityHasRelatedDataException {
        Medicine medicine = this.getMedicine(session, id);
        if(this._scheduleRepository.existsByMedicine(medicine))
            throw new EntityHasRelatedDataException(Medicine.class, MedicineSchedule.class);

        this._medicineRepository.delete(medicine);
    }

    public Page<Medicine> listPatientMedicines(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._medicineRepository.findAllByPatient(patient, pageable);
    }

    public List<Medicine> listPatientMedicines(Patient patient) {
        return this._medicineRepository.findAllByPatient(patient);
    }
}
