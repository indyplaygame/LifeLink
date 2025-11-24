package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.NotOwnerOfEntityException;
import dev.indy.lifelink.model.*;
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

import java.util.List;

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

    public MedicalCheckup getMedicalCheckup(HttpSession session, long id) throws EntityNotFoundException {
        MedicalCheckup checkup = this._medicalCheckupRepository.findByCheckupId(id);
        if(checkup == null) throw new EntityNotFoundException(MedicalCheckup.class, id);

        Patient patient = this._authService.getActivePatient(session);
        if(checkup.getPatient().getPatientId() != patient.getPatientId())
            throw new NotOwnerOfEntityException(MedicalCheckup.class);

        return checkup;
    }

    public MedicalCheckup updateMedicalCheckup(HttpSession session, long id, AddMedicalCheckupRequest body) throws EntityNotFoundException {
        MedicalCheckup checkup = this.getMedicalCheckup(session, id);

        if(body.details() != null) checkup.setCheckupDetails(body.details());
        if(body.date() != null) checkup.setCheckupDate(Util.parseDate(body.date()));

        return this._medicalCheckupRepository.save(checkup);
    }

    public void deleteMedicalCheckup(HttpSession session, long id) throws EntityNotFoundException {
        MedicalCheckup checkup = this.getMedicalCheckup(session, id);
        this._medicalCheckupRepository.delete(checkup);
    }

    public Page<MedicalCheckup> listPatientMedicalCheckups(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._medicalCheckupRepository.findAllByPatient(patient, pageable);
    }

    public List<MedicalCheckup> listPatientMedicalCheckups(Patient patient) {
        return this._medicalCheckupRepository.findAllByPatient(patient);
    }
}
