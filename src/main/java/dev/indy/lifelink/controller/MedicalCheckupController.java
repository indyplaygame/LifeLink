package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.MedicalCheckup;
import dev.indy.lifelink.model.Vaccination;
import dev.indy.lifelink.model.request.AddMedicalCheckupRequest;
import dev.indy.lifelink.model.request.AddVaccinationRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.MedicalCheckupService;
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
@RequestMapping("/checkups")
public class MedicalCheckupController {
    private final MedicalCheckupService _medicalCheckupService;

    @Autowired
    public MedicalCheckupController(MedicalCheckupService medicalCheckupService) {
        this._medicalCheckupService = medicalCheckupService;
    }

    @AuthRequired
    @PostMapping(value = "/add")
    public ResponseEntity<MedicalCheckup> add(
        @Validated(ValidationGroups.OnCreate.class) @RequestBody AddMedicalCheckupRequest body, HttpSession session
    ) {
        MedicalCheckup checkup = this._medicalCheckupService.addMedicalCheckup(session, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(checkup);
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<MedicalCheckup> get(@PathVariable Long id, HttpSession session) {
        try {
            MedicalCheckup checkup = this._medicalCheckupService.getMedicalCheckup(session, id);
            return ResponseEntity.ok(checkup);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<MedicalCheckup> update(
        @PathVariable Long id,
        @Validated(ValidationGroups.OnUpdate.class) @RequestBody AddMedicalCheckupRequest body,
        HttpSession session
    ) {
        try {
            MedicalCheckup checkup = this._medicalCheckupService.updateMedicalCheckup(session, id, body);
            return ResponseEntity.ok(checkup);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpSession session) {
        try {
            this._medicalCheckupService.deleteMedicalCheckup(session, id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<MedicalCheckup>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._medicalCheckupService.listPatientMedicalCheckups(session, pageable)));
    }
}
