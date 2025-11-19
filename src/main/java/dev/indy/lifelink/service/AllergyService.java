package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.model.Allergy;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.request.AddAllergyRequest;
import dev.indy.lifelink.repository.AllergyRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AllergyService {
    private final AllergyRepository _allergyRepository;
    private final AuthService _authService;

    @Autowired
    public AllergyService(AllergyRepository allergyRepository, AuthService authService) {
        this._allergyRepository = allergyRepository;
        this._authService = authService;
    }

    public Allergy addAllergy(HttpSession session, AddAllergyRequest body) {
        Patient patient = this._authService.getActivePatient(session);

        Allergy allergy = new Allergy(
            body.name(),
            body.description(),
            patient
        );

        return this._allergyRepository.save(allergy);
    }

    public Allergy getAllergy(long id) throws EntityNotFoundException {
        Allergy allergy = this._allergyRepository.findByAllergyId(id);
        if(allergy == null)
            throw new EntityNotFoundException(Allergy.class, id);

        return allergy;
    }

    public Allergy updateAllergy(long allergyId, AddAllergyRequest body) throws EntityNotFoundException {
        Allergy allergy = this.getAllergy(allergyId);
        if(allergy == null) throw new EntityNotFoundException(Allergy.class, allergyId);

        if(body.name() != null) allergy.setName(body.name());
        if(body.description() != null) allergy.setDescription(body.description());

        return this._allergyRepository.save(allergy);
    }

    public void deleteAllergy(long allergyId) throws EntityNotFoundException {
        Allergy allergy = this.getAllergy(allergyId);
        if(allergy == null) throw new EntityNotFoundException(Allergy.class, allergyId);

        this._allergyRepository.delete(allergy);
    }

    public Page<Allergy> listPatientAllergies(HttpSession session, Pageable pageable) {
        Patient patient = this._authService.getActivePatient(session);
        return this._allergyRepository.findAllByPatient(patient, pageable);
    }
}
