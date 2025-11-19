package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.Vaccination;
import dev.indy.lifelink.model.request.AddVaccinationRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.VaccinationService;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaccinations")
public class VaccinationController {
    private final VaccinationService _vaccinationService;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService) {
        this._vaccinationService = vaccinationService;
    }

    @AuthRequired
    @PostMapping(value = "/add")
    public ResponseEntity<Vaccination> add(
            @Validated(ValidationGroups.OnCreate.class) @RequestBody AddVaccinationRequest body, HttpSession session
    ) {
        try {
            Vaccination vaccination = this._vaccinationService.addVaccination(session, body);
            return ResponseEntity.status(HttpStatus.CREATED).body(vaccination);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<Vaccination> get(@PathVariable Long id) {
        try {
            Vaccination vaccination = this._vaccinationService.getVaccination(id);
            return ResponseEntity.ok(vaccination);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<Vaccination> update(
            @PathVariable Long id,
            @Valid @RequestBody AddVaccinationRequest body
    ) {
        try {
            Vaccination vaccination = this._vaccinationService.updateVaccination(id, body);
            return ResponseEntity.ok(vaccination);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            this._vaccinationService.deleteVaccination(id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<Vaccination>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._vaccinationService.listPatientVaccinations(session, pageable)));
    }
}
