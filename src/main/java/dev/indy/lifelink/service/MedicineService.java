package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.model.Medicine;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.request.AddMedicineRequest;
import dev.indy.lifelink.repository.MedicineRepository;
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
    private final AuthService _authService;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository, AuthService authService) {
        this._medicineRepository = medicineRepository;
        this._authService = authService;
    }

    public Medicine addMedicine(HttpSession session, AddMedicineRequest body) {
        Patient patient = this._authService.getActivePatient(session);

        Medicine medicine = new Medicine(
            body.name(),
            body.notes(),
            body.dosage(),
            body.frequency(),
            Util.parseDate(body.startDate()),
            Util.parseDate(body.endDate()),
            patient
        );

        return this._medicineRepository.save(medicine);
    }

    public Medicine getMedicine(long id) throws EntityNotFoundException {
        Medicine medicine = this._medicineRepository.findByMedicineId(id);
        if(medicine == null) throw new EntityNotFoundException(Medicine.class, id);

        return medicine;
    }

    public Medicine updateMedicine(long id, AddMedicineRequest body) throws EntityNotFoundException {
        Medicine medicine = this.getMedicine(id);

        if(body.name() != null) medicine.setMedicineName(body.name());
        if(body.notes() != null) medicine.setNotes(body.notes());
        if(body.dosage() != null) medicine.setDosage(body.dosage());
        if(body.frequency() != null) medicine.setFrequency(body.frequency());
        if(body.startDate() != null) medicine.setStartDate(Util.parseDate(body.startDate()));
        if(body.endDate() != null) medicine.setEndDate(Util.parseDate(body.endDate()));

        return this._medicineRepository.save(medicine);
    }

    public void deleteMedicine(long id) throws EntityNotFoundException {
        Medicine medicine = this.getMedicine(id);
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
