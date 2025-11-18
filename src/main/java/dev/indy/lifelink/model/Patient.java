package dev.indy.lifelink.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "patients")
@SuppressWarnings("AssociationNotMarkedInspection")
public class Patient {
    private UUID _patientId;
    private LocalDate _birthDate;
    private String _email;
    private String _pesel;
    private String _nfcTagHash;
    private String _nfcCodeHash;
    private String _passwordHash;
    private Person _person;
    private Person _contactPerson;

    protected Patient() {}

    public Patient(
        LocalDate birthDate, String email, String pesel, String passwordHash, Person person, Person contactPerson
    ) {
        this._birthDate = birthDate;
        this._email = email;
        this._pesel = pesel;
        this._passwordHash = passwordHash;
        this._person = person;
        this._contactPerson = contactPerson;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "patientId", nullable = false, updatable = false)
    public UUID getUserId() { return this._patientId; }
    protected void setUserId(UUID userId) { this._patientId = userId; }

    @Column(name = "birthDate", nullable = false)
    public LocalDate getBirthDate() { return this._birthDate; }
    public void setBirthDate(LocalDate birthDate) { this._birthDate = birthDate; }

    @Column(name = "email", nullable = false, length = 100, unique = true)
    public String getEmail() { return this._email; }
    public void setEmail(String email) { this._email = email; }

    @Column(name = "pesel", nullable = false, length = 11, unique = true)
    public String getPesel() { return this._pesel; }
    public void setPesel(String pesel) { this._pesel = pesel; }

    @Column(name = "nfcTagHash", nullable = true, length = 255, unique = true)
    public String getNfcTagHash() { return this._nfcTagHash; }
    public void setNfcTagHash(String nfcTag) { this._nfcTagHash = nfcTag; }

    @Column(name = "nfcCodeHash", nullable = true, length = 255, unique = true)
    public String getNfcCodeHash() { return this._nfcCodeHash; }
    public void setNfcCodeHash(String medicalInfoCode) { this._nfcCodeHash = medicalInfoCode; }

    @Column(name = "passwordHash", nullable = false, length = 255)
    public String getPasswordHash() { return this._passwordHash; }
    public void setPasswordHash(String passwordHash) { this._passwordHash = passwordHash; }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personId", nullable = false, unique = true)
    public Person getPerson() { return this._person; }
    public void setPerson(Person person) { this._person = person; }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactPersonId", nullable = true, unique = true)
    public Person getContactPerson() { return this._contactPerson; }
    public void setContactPerson(Person contactPerson) { this._contactPerson = contactPerson; }
}
