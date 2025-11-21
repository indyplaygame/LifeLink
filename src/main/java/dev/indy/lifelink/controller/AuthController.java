package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthMethod;
import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.exception.InvalidAuthenticationCredentialsException;
import dev.indy.lifelink.exception.PatientExistsException;
import dev.indy.lifelink.exception.SessionActiveException;
import dev.indy.lifelink.model.request.CreatePatientRequest;
import dev.indy.lifelink.model.request.LoginRequest;
import dev.indy.lifelink.model.request.NfcTagRequest;
import dev.indy.lifelink.model.response.MessageResponse;
import dev.indy.lifelink.service.AuthService;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService _authService;

    @Autowired
    public AuthController(AuthService authService) {
        this._authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(
        @Validated(ValidationGroups.OnCreate.class) @RequestBody CreatePatientRequest body, HttpSession session
    ) {
        try {
            this._authService.createPatient(session, body);

            return ResponseEntity.ok(new MessageResponse("Patient registered successfully."));
        } catch(PatientExistsException | SessionActiveException e) {
            throw new HttpException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(
        @Validated @RequestBody LoginRequest body, HttpSession session
    ) {
        try {
            this._authService.authenticate(session, body);

            return ResponseEntity.ok(new MessageResponse("Logged in successfully."));
        } catch(InvalidAuthenticationCredentialsException e) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch(SessionActiveException e) {
            throw new HttpException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(HttpSession session) {
        this._authService.logout(session);
        return ResponseEntity.ok(new MessageResponse("Logged out successfully."));
    }

    @GetMapping("/session-status")
    public ResponseEntity<Map<String, Boolean>> session(HttpSession session) {
        boolean loggedIn = this._authService.isAuthenticated(session);
        return ResponseEntity.ok(Map.of("active", loggedIn));
    }

    @PostMapping("/generate-token")
    public ResponseEntity<Map<String, String>> generateToken(
        @Validated @RequestBody NfcTagRequest body
    ) {
        try {
            String token = this._authService.generateToken(body);

            return ResponseEntity.ok(Map.of("token", token));
        } catch(InvalidAuthenticationCredentialsException e) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @AuthRequired
    @PostMapping("/nfc/register")
    public ResponseEntity<MessageResponse> registerNfcTag(
        @Validated @RequestBody NfcTagRequest body,
        HttpSession session
    ) {
        this._authService.registerNfcTag(session, body);
        return ResponseEntity.ok(new MessageResponse("NFC tag registered successfully."));
    }

    @AuthRequired
    @PostMapping("/nfc/deregister")
    public ResponseEntity<MessageResponse> deregisterNfcTag(HttpSession session) {
        this._authService.deregisterNfcTag(session);
        return ResponseEntity.ok(new MessageResponse("NFC tag deregistered successfully."));
    }
}
