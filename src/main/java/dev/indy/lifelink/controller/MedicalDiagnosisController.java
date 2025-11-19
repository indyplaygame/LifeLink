package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.MedicalCheckup;
import dev.indy.lifelink.model.MedicalDiagnosis;
import dev.indy.lifelink.model.request.AddMedicalCheckupRequest;
import dev.indy.lifelink.model.request.AddMedicalDiagnosisRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.MedicalCheckupService;
import dev.indy.lifelink.service.MedicalDiagnosisService;
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
@RequestMapping("/diagnoses")
public class MedicalDiagnosisController {
    private final MedicalDiagnosisService _medicalDiagnosisService;

    @Autowired
    public MedicalDiagnosisController(MedicalDiagnosisService medicalDiagnosisService) {
        this._medicalDiagnosisService = medicalDiagnosisService;
    }

    @AuthRequired
    @PostMapping(value = "/add")
    public ResponseEntity<MedicalDiagnosis> add(
            @Validated(ValidationGroups.OnCreate.class) @RequestBody AddMedicalDiagnosisRequest body, HttpSession session
    ) {
        MedicalDiagnosis diagnosis = this._medicalDiagnosisService.addMedicalDiagnosis(session, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnosis);
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<MedicalDiagnosis> get(@PathVariable Long id) {
        try {
            MedicalDiagnosis diagnosis = this._medicalDiagnosisService.getMedicalDiagnosis(id);
            return ResponseEntity.ok(diagnosis);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<MedicalDiagnosis> update(
            @PathVariable Long id,
            @Validated(ValidationGroups.OnUpdate.class) @RequestBody AddMedicalDiagnosisRequest body
    ) {
        try {
            MedicalDiagnosis diagnosis = this._medicalDiagnosisService.updateMedicalDiagnosis(id, body);
            return ResponseEntity.ok(diagnosis);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            this._medicalDiagnosisService.deleteMedicalDiagnosis(id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<MedicalDiagnosis>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._medicalDiagnosisService.listPatientMedicalDiagnoses(session, pageable)));
    }
}
