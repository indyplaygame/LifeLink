package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthMethod;
import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.exception.PatientExistsException;
import dev.indy.lifelink.model.request.CreatePatientRequest;
import dev.indy.lifelink.service.PatientService;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService _patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this._patientService = patientService;
    }

    @AuthRequired(AuthMethod.TOKEN)
    @GetMapping("/card")
    public ResponseEntity<Map<String, ?>> getPatientCard(
        @RequestHeader(HttpHeaders.AUTHORIZATION)
        String authHeader
    ) {
        final Map<String, ?> patientCard = this._patientService.getPatientCard(authHeader);
        return ResponseEntity.ok(patientCard);
    }

    @AuthRequired
    @GetMapping("/details")
    public ResponseEntity<Map<String, ?>> getPatientDetails(HttpSession session) {
        final Map<String, ?> patientCard = this._patientService.getPatientCard(session);
        return ResponseEntity.ok(patientCard);
    }

    @AuthRequired
    @PutMapping("/update")
    public ResponseEntity<Map<String, ?>> updatePatientDetails(
        @Validated(ValidationGroups.OnUpdate.class) @RequestBody CreatePatientRequest body,
        HttpSession session
    ) {
        try {
            final Map<String, ?> updatedPatient = this._patientService.updatePatientDetails(body, session);
            return ResponseEntity.ok(updatedPatient);
        } catch(PatientExistsException e) {
            throw new HttpException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
