package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.exception.InvalidAuthenticationCredentialsException;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.request.RegisterNfcTagRequest;
import dev.indy.lifelink.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class NfcService {
    private final AuthService _authService;
    private final PatientRepository _patientRepository;

    @Autowired
    public NfcService(AuthService authService, PatientRepository patientRepository) {
        this._authService = authService;
        this._patientRepository = patientRepository;
    }

    public void registerDispenserNfcTag(RegisterNfcTagRequest body) throws InvalidAuthenticationCredentialsException {
        final Patient patient = this._authService.getPatientByPesel(body.pesel());
        if(patient == null || !this._authService.verifyPassword(body.password(), patient.getPasswordHash()))
            throw new InvalidAuthenticationCredentialsException();

        if(this._authService.userWithDispenserNfcTagExists(body.nfcTagUid()))
            throw new HttpException(HttpStatus.CONFLICT, "NFC tag is already registered to another patient.");

        patient.setDispenserNfcTagHash(this._authService.hash(body.nfcTagUid()));

        this._patientRepository.save(patient);
    }

    public boolean verifyDispenserNfcTag(String nfcTagUid) {
        // Implementation for verifying dispenser NFC tag goes here
        return this._authService.userWithDispenserNfcTagExists(nfcTagUid);
    }
}
