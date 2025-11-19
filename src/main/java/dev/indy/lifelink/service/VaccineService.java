package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityHasRelatedDataException;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Vaccination;
import dev.indy.lifelink.model.Vaccine;
import dev.indy.lifelink.model.request.AddVaccineRequest;
import dev.indy.lifelink.repository.VaccinationRepository;
import dev.indy.lifelink.repository.VaccineRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VaccineService {
    private final VaccineRepository _vaccineRepository;
    private final VaccinationRepository _vaccinationRepository;
    private final AuthService _authService;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository, VaccinationRepository vaccinationRepository, AuthService authService) {
        this._vaccineRepository = vaccineRepository;
        this._vaccinationRepository = vaccinationRepository;
        this._authService = authService;
    }

    public Vaccine addVaccine(HttpSession session, AddVaccineRequest body) {
        Patient patient = this._authService.getActivePatient(session);

        Vaccine vaccine = new Vaccine(body.name(), patient);
        return this._vaccineRepository.save(vaccine);
    }

    public Vaccine getVaccine(long id) throws EntityNotFoundException {
        Vaccine vaccine = this._vaccineRepository.findByVaccineId(id);
        if(vaccine == null) throw new EntityNotFoundException(Vaccine.class, id);

        return vaccine;
    }

    public Vaccine updateVaccine(long id, AddVaccineRequest body) throws EntityNotFoundException {
        Vaccine vaccine = this.getVaccine(id);

        if(body.name() != null) vaccine.setName(body.name());

        return this._vaccineRepository.save(vaccine);
    }

    public void deleteVaccine(long id) throws EntityNotFoundException, EntityHasRelatedDataException {
        Vaccine vaccine = this.getVaccine(id);
        if(this._vaccinationRepository.existsByVaccine(vaccine))
            throw new EntityHasRelatedDataException(Vaccine.class, Vaccination.class);

        this._vaccineRepository.delete(vaccine);
    }

    public Page<Vaccine> listPatientVaccines(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._vaccineRepository.findAllByPatient(patient, pageable);
    }
}
