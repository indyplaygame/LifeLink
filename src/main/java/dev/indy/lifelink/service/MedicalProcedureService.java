package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.model.MedicalProcedure;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.request.AddMedicalProcedureRequest;
import dev.indy.lifelink.repository.MedicalProcedureRepository;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicalProcedureService {
    private final MedicalProcedureRepository _medicalProcedureRepository;
    private final AuthService _authService;

    @Autowired
    public MedicalProcedureService(MedicalProcedureRepository medicalProcedureRepository, AuthService authService) {
        this._medicalProcedureRepository = medicalProcedureRepository;
        this._authService = authService;
    }

    public MedicalProcedure addMedicalProcedure(HttpSession session, AddMedicalProcedureRequest body) {
        Patient patient = this._authService.getActivePatient(session);

        MedicalProcedure procedure = new MedicalProcedure(
            body.cptCode(),
            body.procedureDescription(),
            Util.parseDate(body.procedureDate()),
            patient
        );

        return this._medicalProcedureRepository.save(procedure);
    }

    public MedicalProcedure getMedicalProcedure(long id) throws EntityNotFoundException {
        MedicalProcedure procedure = this._medicalProcedureRepository.findByProcedureId(id);
        if(procedure == null) throw new EntityNotFoundException(MedicalProcedure.class, id);

        return procedure;
    }

    public MedicalProcedure updateMedicalProcedure(long id, AddMedicalProcedureRequest body) throws EntityNotFoundException {
        MedicalProcedure procedure = this.getMedicalProcedure(id);

        if(body.cptCode() != null) procedure.setCptCode(body.cptCode());
        if(body.procedureDescription() != null) procedure.setProcedureDescription(body.procedureDescription());
        if(body.procedureDate() != null) procedure.setProcedureDate(Util.parseDate(body.procedureDate()));

        return this._medicalProcedureRepository.save(procedure);
    }

    public void deleteMedicalProcedure(long id) throws EntityNotFoundException {
        MedicalProcedure procedure = this.getMedicalProcedure(id);
        this._medicalProcedureRepository.delete(procedure);
    }

    public Page<MedicalProcedure> listPatientMedicalProcedures(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._medicalProcedureRepository.findAllByPatient(patient, pageable);
    }
}
