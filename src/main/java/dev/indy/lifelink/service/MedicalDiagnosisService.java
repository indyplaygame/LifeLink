package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.NotOwnerOfEntityException;
import dev.indy.lifelink.model.Allergy;
import dev.indy.lifelink.model.MedicalCheckup;
import dev.indy.lifelink.model.MedicalDiagnosis;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.request.AddMedicalCheckupRequest;
import dev.indy.lifelink.model.request.AddMedicalDiagnosisRequest;
import dev.indy.lifelink.repository.MedicalCheckupRepository;
import dev.indy.lifelink.repository.MedicalDiagnosisRepository;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalDiagnosisService {
    private final MedicalDiagnosisRepository _medicalDiagnosisRepository;
    private final AuthService _authService;

    @Autowired
    public MedicalDiagnosisService(MedicalDiagnosisRepository medicalDiagnosisRepository, AuthService authService) {
        this._medicalDiagnosisRepository = medicalDiagnosisRepository;
        this._authService = authService;
    }

    public MedicalDiagnosis addMedicalDiagnosis(HttpSession session, AddMedicalDiagnosisRequest body) {
        Patient patient = this._authService.getActivePatient(session);

        MedicalDiagnosis diagnosis = new MedicalDiagnosis(
            body.icdCode(),
            body.description(),
            Util.parseDate(body.date()),
            patient
        );

        return this._medicalDiagnosisRepository.save(diagnosis);
    }

    public MedicalDiagnosis getMedicalDiagnosis(HttpSession session, long id) throws EntityNotFoundException {
        MedicalDiagnosis diagnosis = this._medicalDiagnosisRepository.findByDiagnosisId(id);
        if(diagnosis == null) throw new EntityNotFoundException(MedicalDiagnosis.class, id);

        Patient patient = this._authService.getActivePatient(session);
        if(diagnosis.getPatient().getPatientId() != patient.getPatientId())
            throw new NotOwnerOfEntityException(MedicalDiagnosis.class);

        return diagnosis;
    }

    public MedicalDiagnosis updateMedicalDiagnosis(HttpSession session, long id, AddMedicalDiagnosisRequest body) throws EntityNotFoundException {
        MedicalDiagnosis diagnosis = this.getMedicalDiagnosis(session, id);

        if(body.icdCode() != null) diagnosis.setIcdCode(body.icdCode());
        if(body.description() != null) diagnosis.setDescription(body.description());

        return this._medicalDiagnosisRepository.save(diagnosis);
    }

    public void deleteMedicalDiagnosis(HttpSession session, long id) throws EntityNotFoundException {
        MedicalDiagnosis diagnosis = this.getMedicalDiagnosis(session, id);
        this._medicalDiagnosisRepository.delete(diagnosis);
    }

    public Page<MedicalDiagnosis> listPatientMedicalDiagnoses(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._medicalDiagnosisRepository.findAllByPatient(patient, pageable);
    }

    public List<MedicalDiagnosis> listPatientMedicalDiagnoses(Patient patient) {
        return this._medicalDiagnosisRepository.findAllByPatient(patient);
    }
}
