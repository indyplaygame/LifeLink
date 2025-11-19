package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
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
            patient
        );

        return this._medicalDiagnosisRepository.save(diagnosis);
    }

    public MedicalDiagnosis getMedicalDiagnosis(long id) throws EntityNotFoundException {
        MedicalDiagnosis diagnosis = this._medicalDiagnosisRepository.findByDiagnosisId(id);
        if(diagnosis == null) throw new EntityNotFoundException(MedicalDiagnosis.class, id);

        return diagnosis;
    }

    public MedicalDiagnosis updateMedicalDiagnosis(long id, AddMedicalDiagnosisRequest body) throws EntityNotFoundException {
        MedicalDiagnosis diagnosis = this.getMedicalDiagnosis(id);

        if(body.icdCode() != null) diagnosis.setIcdCode(body.icdCode());
        if(body.description() != null) diagnosis.setDescription(body.description());

        return this._medicalDiagnosisRepository.save(diagnosis);
    }

    public void deleteMedicalDiagnosis(long id) throws EntityNotFoundException {
        MedicalDiagnosis diagnosis = this.getMedicalDiagnosis(id);
        this._medicalDiagnosisRepository.delete(diagnosis);
    }

    public Page<MedicalDiagnosis> listPatientMedicalDiagnoses(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._medicalDiagnosisRepository.findAllByPatient(patient, pageable);
    }
}
