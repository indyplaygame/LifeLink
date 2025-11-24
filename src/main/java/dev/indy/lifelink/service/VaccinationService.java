package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.NotOwnerOfEntityException;
import dev.indy.lifelink.model.Allergy;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Vaccination;
import dev.indy.lifelink.model.Vaccine;
import dev.indy.lifelink.model.request.AddVaccinationRequest;
import dev.indy.lifelink.model.request.AddVaccineRequest;
import dev.indy.lifelink.repository.VaccinationRepository;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationService {
    private final VaccinationRepository _vaccinationRepository;
    private final VaccineService _vaccineService;
    private final AuthService _authService;

    @Autowired
    public VaccinationService(VaccinationRepository vaccineRepository, VaccineService vaccineService, AuthService authService) {
        this._vaccinationRepository = vaccineRepository;
        this._vaccineService = vaccineService;
        this._authService = authService;
    }

    public Vaccination addVaccination(HttpSession session, AddVaccinationRequest body) throws EntityNotFoundException {
        Patient patient = this._authService.getActivePatient(session);
        Vaccine vaccine = this._vaccineService.getVaccine(session, body.vaccineId());

        Vaccination vaccination = new Vaccination(
            body.doseNumber(),
            Util.parseDate(body.vaccinationDate()),
            body.notes(),
            vaccine,
            patient
        );

        return this._vaccinationRepository.save(vaccination);
    }

    public Vaccination getVaccination(HttpSession session, long id) throws EntityNotFoundException {
        Vaccination vaccination = this._vaccinationRepository.findByVaccinationId(id);
        if(vaccination == null) throw new EntityNotFoundException(Vaccination.class, id);

        Patient patient = this._authService.getActivePatient(session);
        if(vaccination.getPatient().getPatientId() != patient.getPatientId())
            throw new NotOwnerOfEntityException(Vaccination.class);

        return vaccination;
    }

    public Vaccination updateVaccination(HttpSession session, long id, AddVaccinationRequest body) throws EntityNotFoundException {
        Vaccination vaccination = this.getVaccination(session, id);

        if(body.doseNumber() != null) vaccination.setDoseNumber(body.doseNumber());
        if(body.vaccinationDate() != null) vaccination.setVaccinationDate(Util.parseDate(body.vaccinationDate()));
        if(body.notes() != null) vaccination.setNotes(body.notes());
        if(body.vaccineId() != null) {
            Vaccine vaccine = this._vaccineService.getVaccine(session, body.vaccineId());
            vaccination.setVaccine(vaccine);
        }

        return this._vaccinationRepository.save(vaccination);
    }

    public void deleteVaccination(HttpSession session, long id) throws EntityNotFoundException {
        Vaccination vaccination = this.getVaccination(session, id);
        this._vaccinationRepository.delete(vaccination);
    }

    public Page<Vaccination> listPatientVaccinations(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._vaccinationRepository.findAllByPatient(patient, pageable);
    }

    public List<Vaccination> listPatientVaccinations(Patient patient) {
        return this._vaccinationRepository.findAllByPatient(patient);
    }
}
