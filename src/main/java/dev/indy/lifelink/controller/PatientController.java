package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthMethod;
import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.service.PatientService;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService _patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this._patientService = patientService;
    }

    @Validated
    @AuthRequired(AuthMethod.TOKEN)
    @GetMapping("/card/{nfcUid}")
    public ResponseEntity<Map<String, ?>> getPatientCard(
        @PathVariable
        @Pattern(regexp = Util.NFC_UID_REGEXP, message = "NFC tag UID must be a valid hexadecimal string (with optional colons or hyphens).")
        String nfcUid
    ) {
        Map<String, ?> patientCard = this._patientService.getPatientCard(nfcUid);
        return ResponseEntity.ok(patientCard);
    }

    @AuthRequired
    @GetMapping("/details")
    public ResponseEntity<Map<String, ?>> getPatientDetails(HttpSession session) {
        Map<String, ?> patientCard = this._patientService.getPatientCard(session);
        return ResponseEntity.ok(patientCard);
    }
}
