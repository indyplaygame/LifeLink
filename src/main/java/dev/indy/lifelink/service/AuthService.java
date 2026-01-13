package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.exception.InvalidAuthenticationCredentialsException;
import dev.indy.lifelink.exception.PatientExistsException;
import dev.indy.lifelink.exception.SessionActiveException;
import dev.indy.lifelink.model.Address;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Person;
import dev.indy.lifelink.model.request.*;
import dev.indy.lifelink.repository.PatientRepository;
import dev.indy.lifelink.util.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {
    private final static String ACTIVE_PATIENT_SESSION_KEY = "activeUser";
    private final static long JWT_EXPIRATION_MS = 1800000L;

    private final PatientRepository _patientRepository;
    private final BCryptPasswordEncoder _passwordEncoder;
    private final MessageDigest _digest;
    private final Base64.Encoder _encoder;
    private final SecretKey _key;

    @Autowired
    public AuthService(PatientRepository patientRepository, @Value("${jwt.secret}") String secret) throws NoSuchAlgorithmException {
        this._patientRepository = patientRepository;
        this._passwordEncoder = new BCryptPasswordEncoder();
        this._digest = MessageDigest.getInstance("SHA-256");
        this._encoder = Base64.getEncoder();
        this._key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    private String generateJwtToken(String nfcTagId) {
        return Jwts.builder()
            .setSubject(nfcTagId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
            .signWith(this._key, SignatureAlgorithm.HS256)
            .compact();
    }

    private Claims parseJwtToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(this._key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private boolean checkJwtTokenExpiration(Claims claims) {
        final Date expiration = claims.getExpiration();
        return expiration != null && expiration.after(new Date());
    }

    private Person createPerson(CreatePersonRequest personBody) {
        final CreateAddressRequest addressBody = personBody.address();

        final Address address = new Address(
            addressBody.country(),
            addressBody.postalCode(),
            addressBody.city(),
            addressBody.street(),
            addressBody.buildingNumber()
        );

        return new Person(
            personBody.firstName(),
            personBody.middleName(),
            personBody.lastName(),
            personBody.email(),
            personBody.phoneNumber(),
            personBody.gender(),
            address
        );
    }

    private String hashPassword(String password) {
        return password != null ? this._passwordEncoder.encode(password) : null;
    }

    public String hash(String str) {
        byte[] encoded = this._digest.digest(str.getBytes(StandardCharsets.UTF_8));
        return this._encoder.encodeToString(encoded);
    }

    public boolean verifyPassword(String password, String hash) {
        return this._passwordEncoder.matches(password, hash);
    }

    public boolean userWithPeselExists(String pesel) {
        return this._patientRepository.findByPesel(pesel) != null;
    }

    public boolean userWithDispenserNfcTagExists(String nfcTagUid) {
        return this.getPatientByDispenserNfcTag(nfcTagUid) != null;
    }

    public void createPatient(HttpSession session, CreatePatientRequest body) throws PatientExistsException, SessionActiveException {
        if(this.isAuthenticated(session)) throw new SessionActiveException();
        if(this.userWithPeselExists(body.pesel())) throw new PatientExistsException();

        final Person person = this.createPerson(body.person());
        final Person emergencyContact = this.createPerson(body.emergencyContact());

        final Patient patient = new Patient(
            Util.parseDate(body.dateOfBirth()),
            body.pesel(),
            this.hashPassword(body.password()),
            body.bloodType(),
            person,
            emergencyContact
        );

        this.setActivePatient(session, patient);
        this._patientRepository.save(patient);
    }

    public void authenticate(HttpSession session, LoginRequest body) throws InvalidAuthenticationCredentialsException, SessionActiveException {
        if(this.isAuthenticated(session)) throw new SessionActiveException();

        final Patient patient = this.getPatientByPesel(body.pesel());
        if(patient == null || !this.verifyPassword(body.password(), patient.getPasswordHash()))
            throw new InvalidAuthenticationCredentialsException();

        this.setActivePatient(session, patient);
    }

    public void logout(HttpSession session) {
        if(session != null) session.invalidate();
    }

    public boolean isAuthenticated(HttpSession session) {
        return this.getActivePatient(session) != null;
    }

    public String generateToken(NfcTagRequest body) throws InvalidAuthenticationCredentialsException {
        final Patient patient = this.getPatientByNfcTag(body.nfcTagUid());
        if(patient == null)
            throw new HttpException(HttpStatus.NOT_FOUND, "No patient found for the provided NFC tag.");

        final String nfcCodeHash = patient.getNfcCodeHash();
        if(!this.verifyPassword(body.nfcCode(), nfcCodeHash))
            throw new InvalidAuthenticationCredentialsException();

        return this.generateJwtToken(body.nfcTagUid());
    }

    public boolean isTokenValid(String token) {
        try {
            final Claims claims = this.parseJwtToken(token);
            return this.checkJwtTokenExpiration(claims);
        } catch(Exception e) {
            return false;
        }
    }

    public void registerNfcTag(HttpSession session, NfcTagRequest body) {
        final Patient patient = this.getActivePatient(session);

        patient.setNfcTagHash(this.hash(body.nfcTagUid()));
        patient.setNfcCodeHash(this.hashPassword(body.nfcCode()));

        this._patientRepository.save(patient);
    }

    public void deregisterNfcTag(HttpSession session) {
        final Patient patient = this.getActivePatient(session);

        if(patient.getNfcTagHash() == null || patient.getNfcCodeHash() == null)
            throw new HttpException(HttpStatus.NOT_FOUND, "No NFC tag is registered for the active patient.");

        patient.setNfcTagHash(null);
        patient.setNfcCodeHash(null);

        this._patientRepository.save(patient);
    }

    public Patient getPatientByPesel(String pesel) {
        return this._patientRepository.findByPesel(pesel);
    }

    public Patient getPatientByNfcTag(String nfcUid) {
        return this._patientRepository.findByNfcTagHash(this.hash(nfcUid));
    }

    public Patient getPatientByDispenserNfcTag(String nfcTagUid) {
        return this._patientRepository.findByDispenserNfcTagHash(this.hash(nfcTagUid));
    }

    public Patient getPatientByToken(String token) {
        try {
            final Claims claims = this.parseJwtToken(token);
            final String nfcTag = claims.getSubject();

            return this.getPatientByNfcTag(nfcTag);
        } catch(Exception e) {
            return null;
        }
    }

    public Patient getActivePatient(HttpSession session) {
        if(session == null) return null;

        final Object patientId = session.getAttribute(ACTIVE_PATIENT_SESSION_KEY);
        if(patientId == null) return null;

        return this._patientRepository.findByPatientId((UUID) patientId);
    }

    public void setActivePatient(HttpSession session, Patient patient) {
        session.setAttribute(ACTIVE_PATIENT_SESSION_KEY, patient.getPatientId());
    }
}
