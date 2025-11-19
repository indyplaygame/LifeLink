package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.MedicalProcedure;
import dev.indy.lifelink.model.request.AddMedicalProcedureRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.MedicalProcedureService;
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
@RequestMapping("/procedures")
public class MedicalProcedureController {
    private final MedicalProcedureService _medicalProcedureService;

    @Autowired
    public MedicalProcedureController(MedicalProcedureService medicalProcedureService) {
        this._medicalProcedureService = medicalProcedureService;
    }

    @AuthRequired
    @PostMapping(value = "/add")
    public ResponseEntity<MedicalProcedure> add(
            @Validated(ValidationGroups.OnCreate.class) @RequestBody AddMedicalProcedureRequest body, HttpSession session
    ) {
        MedicalProcedure procedure = this._medicalProcedureService.addMedicalProcedure(session, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(procedure);
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<MedicalProcedure> get(@PathVariable Long id) {
        try {
            MedicalProcedure procedure = this._medicalProcedureService.getMedicalProcedure(id);
            return ResponseEntity.ok(procedure);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<MedicalProcedure> update(
            @PathVariable Long id,
            @Valid @RequestBody AddMedicalProcedureRequest body
    ) {
        try {
            MedicalProcedure procedure = this._medicalProcedureService.updateMedicalProcedure(id, body);
            return ResponseEntity.ok(procedure);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            this._medicalProcedureService.deleteMedicalProcedure(id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<MedicalProcedure>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._medicalProcedureService.listPatientMedicalProcedures(session, pageable)));
    }
}
