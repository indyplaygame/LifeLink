package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.model.MedicalCheckup;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Vaccination;
import dev.indy.lifelink.model.Vaccine;
import dev.indy.lifelink.model.request.AddMedicalCheckupRequest;
import dev.indy.lifelink.model.request.AddVaccinationRequest;
import dev.indy.lifelink.repository.MedicalCheckupRepository;
import dev.indy.lifelink.repository.VaccinationRepository;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicalCheckupService {
    private final MedicalCheckupRepository _medicalCheckupRepository;
    private final AuthService _authService;

    @Autowired
    public MedicalCheckupService(MedicalCheckupRepository medicalCheckupRepository, AuthService authService) {
        this._medicalCheckupRepository = medicalCheckupRepository;
        this._authService = authService;
    }

    public MedicalCheckup addMedicalCheckup(HttpSession session, AddMedicalCheckupRequest body) {
        Patient patient = this._authService.getActivePatient(session);

        MedicalCheckup checkup = new MedicalCheckup(
            body.details(),
            Util.parseDate(body.date()),
            patient
        );

        return this._medicalCheckupRepository.save(checkup);
    }

    public MedicalCheckup getMedicalCheckup(long id) throws EntityNotFoundException {
        MedicalCheckup checkup = this._medicalCheckupRepository.findByCheckupId(id);
        if(checkup == null) throw new EntityNotFoundException(MedicalCheckup.class, id);

        return checkup;
    }

    public MedicalCheckup updateMedicalCheckup(long id, AddMedicalCheckupRequest body) throws EntityNotFoundException {
        MedicalCheckup checkup = this.getMedicalCheckup(id);

        if(body.details() != null) checkup.setCheckupDetails(body.details());
        if(body.date() != null) checkup.setCheckupDate(Util.parseDate(body.date()));

        return this._medicalCheckupRepository.save(checkup);
    }

    public void deleteMedicalCheckup(long id) throws EntityNotFoundException {
        MedicalCheckup checkup = this.getMedicalCheckup(id);
        this._medicalCheckupRepository.delete(checkup);
    }

    public Page<MedicalCheckup> listPatientMedicalCheckups(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._medicalCheckupRepository.findAllByPatient(patient, pageable);
    }
}
