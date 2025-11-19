package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.model.ChronicDisease;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.request.AddChronicDiseaseRequest;
import dev.indy.lifelink.repository.ChronicDiseaseRepository;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChronicDiseaseService {
    private final ChronicDiseaseRepository _chronicDiseaseRepository;
    private final AuthService _authService;

    @Autowired
    public ChronicDiseaseService(ChronicDiseaseRepository chronicDiseaseRepository, AuthService authService) {
        this._chronicDiseaseRepository = chronicDiseaseRepository;
        this._authService = authService;
    }

    public ChronicDisease addChronicDisease(HttpSession session, AddChronicDiseaseRequest body) {
        Patient patient = this._authService.getActivePatient(session);

        ChronicDisease disease = new ChronicDisease(
            body.name(),
            body.notes(),
            Util.parseDate(body.diagnosisDate()),
            patient
        );

        return this._chronicDiseaseRepository.save(disease);
    }

    public ChronicDisease getChronicDisease(long id) throws EntityNotFoundException {
        ChronicDisease disease = this._chronicDiseaseRepository.findByDiseaseId(id);
        if(disease == null) throw new EntityNotFoundException(ChronicDisease.class, id);

        return disease;
    }

    public ChronicDisease updateChronicDisease(long id, AddChronicDiseaseRequest body) throws EntityNotFoundException {
        ChronicDisease disease = this.getChronicDisease(id);
        if(disease == null) throw new EntityNotFoundException(ChronicDisease.class, id);

        if(body.name() != null) disease.setName(body.name());
        if(body.notes() != null) disease.setNotes(body.notes());
        if(body.diagnosisDate() != null) disease.setDiagnosisDate(Util.parseDate(body.diagnosisDate()));

        return this._chronicDiseaseRepository.save(disease);
    }

    public void deleteChronicDisease(long id) throws EntityNotFoundException {
        ChronicDisease disease = this.getChronicDisease(id);
        if(disease == null) throw new EntityNotFoundException(ChronicDisease.class, id);

        this._chronicDiseaseRepository.delete(disease);
    }

    public Page<ChronicDisease> listPatientChronicDiseases(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._chronicDiseaseRepository.findAllByPatient(patient, pageable);
    }
}
