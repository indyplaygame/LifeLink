package dev.indy.lifelink.service;

import dev.indy.lifelink.exception.PatientExistsException;
import dev.indy.lifelink.model.Address;
import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Person;
import dev.indy.lifelink.model.request.CreateAddressRequest;
import dev.indy.lifelink.model.request.CreatePatientRequest;
import dev.indy.lifelink.model.request.CreatePersonRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.repository.PatientRepository;
import dev.indy.lifelink.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PatientService {
    private final AuthService _authService;
    private final AllergyService _allergyService;
    private final ChronicDiseaseService _chronicDiseaseService;
    private final MedicalCheckupService _medicalCheckupService;
    private final MedicalDiagnosisService _medicalDiagnosisService;
    private final MedicalProcedureService _medicalProcedureService;
    private final MedicineService _medicineService;
    private final VaccinationService _vaccinationService;
    private final PatientRepository _patientRepository;

    public PatientService(
        AuthService authService,
        AllergyService allergyService,
        ChronicDiseaseService chronicDiseaseService,
        MedicalCheckupService medicalCheckupService,
        MedicalDiagnosisService medicalDiagnosisService,
        MedicalProcedureService medicalProcedureService,
        MedicineService medicineService,
        VaccinationService vaccinationService,
        PatientRepository patientRepository
    ) {
        this._authService = authService;
        this._allergyService = allergyService;
        this._chronicDiseaseService = chronicDiseaseService;
        this._medicalCheckupService = medicalCheckupService;
        this._medicalDiagnosisService = medicalDiagnosisService;
        this._medicalProcedureService = medicalProcedureService;
        this._medicineService = medicineService;
        this._vaccinationService = vaccinationService;
        this._patientRepository = patientRepository;
    }

    private Person updatePerson(Person person, CreatePersonRequest body) {
        if(body.firstName() != null) person.setFirstName(body.firstName());
        if(body.middleName() != null) person.setMiddleName(body.middleName());
        if(body.lastName() != null) person.setLastName(body.lastName());
        if(body.phoneNumber() != null) person.setPhoneNumber(body.phoneNumber());
        if(body.gender() != null) person.setGender(body.gender());

        final Address address = person.getAddress();
        final CreateAddressRequest addressBody = body.address();
        if(addressBody != null) {
            if(addressBody.country() != null) address.setCountry(addressBody.country());
            if(addressBody.postalCode() != null) address.setPostalCode(addressBody.postalCode());
            if(addressBody.city() != null) address.setCity(addressBody.city());
            if(addressBody.street() != null) address.setStreet(addressBody.street());
            if(addressBody.buildingNumber() != null) address.setBuildingNumber(addressBody.buildingNumber());

            person.setAddress(address);
        }

        return person;
    }

    public Map<String, ?> updatePatientDetails(CreatePatientRequest body, HttpSession session) throws PatientExistsException {
        final Patient patient = this._authService.getActivePatient(session);
        final Person person = patient.getPerson();
        final Person contactPerson = patient.getContactPerson();

        if(body.dateOfBirth() != null) patient.setDateOfBirth(Util.parseDate(body.dateOfBirth()));
        if(body.email() != null) patient.setEmail(body.email());
        if(body.bloodType() != null) patient.setBloodType(body.bloodType());
        if(body.pesel() != null) {
            final String pesel = body.pesel();
            if(this._authService.userWithPeselExists(pesel) && !pesel.equals(patient.getPesel()))
                throw new PatientExistsException();

            patient.setPesel(pesel);
        }

        final CreatePersonRequest personBody = body.person();
        if(personBody != null) patient.setPerson(this.updatePerson(person, personBody));

        final CreatePersonRequest contactPersonBody = body.emergencyContact();
        if(contactPersonBody != null) patient.setContactPerson(this.updatePerson(contactPerson, contactPersonBody));

        return this.getPatientCard(this._patientRepository.save(patient));
    }

    public Map<String, ?> getPatientCard(String authHeader) {
        String token = Util.retrieveToken(authHeader);
        Patient patient = this._authService.getPatientByToken(token);

        return this.getPatientCard(patient);
    }

    public Map<String, ?> getPatientCard(HttpSession session) {
        Patient patient = this._authService.getActivePatient(session);

        return this.getPatientCard(patient);
    }

    public Map<String, ?> getPatientCard(Patient patient) {
        return Map.of(
            "patient", patient,
            "card", Map.of(
                "allergies", this._allergyService.listPatientAllergies(patient),
                "chronicDiseases", this._chronicDiseaseService.listPatientChronicDiseases(patient),
                "medicalCheckups", this._medicalCheckupService.listPatientMedicalCheckups(patient),
                "medicalDiagnoses", this._medicalDiagnosisService.listPatientMedicalDiagnoses(patient),
                "medicalProcedures", this._medicalProcedureService.listPatientMedicalProcedures(patient),
                "medicines", this._medicineService.listPatientMedicines(patient),
                "vaccinations", this._vaccinationService.listPatientVaccinations(patient)
            )
        );
    }
}
