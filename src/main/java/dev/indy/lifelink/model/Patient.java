package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "patients")
@SuppressWarnings("AssociationNotMarkedInspection")
public class Patient {
    private UUID _patientId;
    private LocalDate _dateOfBirth;
    private String _email;
    private String _pesel;
    private String _nfcTagHash;
    private String _nfcCodeHash;
    private String _passwordHash;
    private BloodType _bloodType;
    private Person _person;
    private Person _contactPerson;

    public enum BloodType {
        A_POSITIVE,
        A_NEGATIVE,
        B_POSITIVE,
        B_NEGATIVE,
        AB_POSITIVE,
        AB_NEGATIVE,
        O_POSITIVE,
        O_NEGATIVE;

        @JsonCreator
        public static BloodType fromString(String value) {
            return switch(value.toUpperCase()) {
                case "A+" -> A_POSITIVE;
                case "A-" -> A_NEGATIVE;
                case "B+" -> B_POSITIVE;
                case "B-" -> B_NEGATIVE;
                case "AB+" -> AB_POSITIVE;
                case "AB-" -> AB_NEGATIVE;
                case "O+" -> O_POSITIVE;
                case "O-" -> O_NEGATIVE;
                default -> throw new IllegalArgumentException("Invalid blood type: " + value);
            };
        }
    }

    protected Patient() {}

    public Patient(
        LocalDate dateOfBirth, String email, String pesel, String passwordHash, BloodType bloodType, Person person, Person contactPerson
    ) {
        this._dateOfBirth = dateOfBirth;
        this._email = email.toLowerCase();
        this._pesel = pesel;
        this._passwordHash = passwordHash;
        this._bloodType = bloodType;
        this._person = person;
        this._contactPerson = contactPerson;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "patientId", nullable = false, updatable = false)
    public UUID getPatientId() { return this._patientId; }
    protected void setPatientId(UUID userId) { this._patientId = userId; }

    @Column(name = "dateOfBirth", nullable = false)
    public LocalDate getDateOfBirth() { return this._dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this._dateOfBirth = dateOfBirth; }

    @Column(name = "email", nullable = false, length = 100, unique = true)
    public String getEmail() { return this._email; }
    public void setEmail(String email) { this._email = email; }

    @Column(name = "pesel", nullable = false, length = 11, unique = true)
    public String getPesel() { return this._pesel; }
    public void setPesel(String pesel) { this._pesel = pesel; }

    @JsonIgnore
    @Column(name = "nfcTagHash", nullable = true, length = 255, unique = true)
    public String getNfcTagHash() { return this._nfcTagHash; }
    public void setNfcTagHash(String nfcTag) { this._nfcTagHash = nfcTag; }

    @JsonIgnore
    @Column(name = "nfcCodeHash", nullable = true, length = 255, unique = true)
    public String getNfcCodeHash() { return this._nfcCodeHash; }
    public void setNfcCodeHash(String medicalInfoCode) { this._nfcCodeHash = medicalInfoCode; }

    @JsonIgnore
    @Column(name = "passwordHash", nullable = false, length = 255)
    public String getPasswordHash() { return this._passwordHash; }
    public void setPasswordHash(String passwordHash) { this._passwordHash = passwordHash; }

    @Enumerated(EnumType.STRING)
    @Column(name = "bloodType", nullable = true, length = 3)
    public BloodType getBloodType() { return this._bloodType; }
    public void setBloodType(BloodType bloodType) { this._bloodType = bloodType; }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personId", nullable = false, unique = true)
    public Person getPerson() { return this._person; }
    public void setPerson(Person person) { this._person = person; }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactPersonId", nullable = true, unique = true)
    public Person getContactPerson() { return this._contactPerson; }
    public void setContactPerson(Person contactPerson) { this._contactPerson = contactPerson; }
}
