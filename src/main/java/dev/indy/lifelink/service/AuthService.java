package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.InvalidLoginCredentialsException;
import dev.indy.lifelink.exception.PatientExistsException;
import dev.indy.lifelink.model.Address;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Person;
import dev.indy.lifelink.model.request.CreateAddressRequest;
import dev.indy.lifelink.model.request.CreatePatientRequest;
import dev.indy.lifelink.model.request.CreatePersonRequest;
import dev.indy.lifelink.model.request.LoginRequest;
import dev.indy.lifelink.repository.PatientRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AuthService {
    private final static String ACTIVE_PATIENT_SESSION_KEY = "activeUser";

    private final PatientRepository _patientRepository;
    private final BCryptPasswordEncoder _passwordEncoder;

    @Autowired
    public AuthService(PatientRepository patientRepository) {
        this._patientRepository = patientRepository;
        this._passwordEncoder = new BCryptPasswordEncoder();
    }

    private String hashPassword(String password) {
        return password != null ? this._passwordEncoder.encode(password) : null;
    }

    private boolean verifyPassword(String password, String hash) {
        return this._passwordEncoder.matches(password, hash);
    }
    
    private boolean userWithPeselExists(String pesel) {
        return this._patientRepository.findByPesel(pesel) != null;
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
            personBody.phoneNumber(),
            personBody.gender(),
            address
        );
    }

    public Patient createPatient(HttpSession session, CreatePatientRequest body) {
        if(this.userWithPeselExists(body.pesel())) {
            throw new PatientExistsException();
        }

        final Person person = this.createPerson(body.person());
        final Person emergencyContact = this.createPerson(body.emergencyContact());

        final Patient patient = new Patient(
            LocalDate.parse(body.dateOfBirth()),
            body.email(),
            body.pesel(),
            this.hashPassword(body.password()),
            person,
            emergencyContact
        );

        this.setActivePatient(session, patient);
        return this._patientRepository.save(patient);
    }

    public Patient authenticate(HttpSession session, LoginRequest body) {
        final Patient patient = this._patientRepository.findByPesel(body.pesel());
        if(patient == null || !this.verifyPassword(body.password(), patient.getPasswordHash()))
            throw new InvalidLoginCredentialsException();

        this.setActivePatient(session, patient);
        return patient;
    }

    public void logout(HttpSession session) {
        if(session != null) session.invalidate();
    }

    public Patient getActivePatient(HttpSession session) {
        if(session == null) return null;

        final Object patientId = session.getAttribute(ACTIVE_PATIENT_SESSION_KEY);
        if(patientId == null) return null;

        return this._patientRepository.findByPatientId((UUID) patientId);
    }

    public void setActivePatient(HttpSession session, Patient patient) {
        session.setAttribute(ACTIVE_PATIENT_SESSION_KEY, patient.getUserId());
    }
}
