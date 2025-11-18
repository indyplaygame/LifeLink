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
    private String _pesel;
    private String _nfcTag;
    private String _medicalInfoCode;
    private String _passwordHash;
    private Person _person;
    private Person _contactPerson;

    protected Patient() {}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "patientId", nullable = false, updatable = false)
    public UUID getUserId() { return this._patientId; }
    protected void setUserId(UUID userId) { this._patientId = userId; }

    @Column(name = "birthDate", nullable = false)
    public LocalDate getBirthDate() { return this._birthDate; }
    public void setBirthDate(LocalDate birthDate) { this._birthDate = birthDate; }

    @Column(name = "pesel", nullable = false, length = 11, unique = true)
    public String getPesel() { return this._pesel; }
    public void setPesel(String pesel) { this._pesel = pesel; }

    @Column(name = "nfcTag", nullable = true, length = 20, unique = true)
    public String getNfcTag() { return this._nfcTag; }
    public void setNfcTag(String nfcTag) { this._nfcTag = nfcTag; }

    @Column(name = "medicalInfoCode", nullable = false, length = 8, unique = true)
    public String getMedicalInfoCode() { return this._medicalInfoCode; }
    public void setMedicalInfoCode(String medicalInfoCode) { this._medicalInfoCode = medicalInfoCode; }

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
