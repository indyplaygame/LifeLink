package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medicalCheckups")
@SuppressWarnings("AssociationNotMarkedInspection")
public class MedicalCheckup {
    private long _checkupId;
    private String _checkupDetails;
    private LocalDate _checkupDate;
    private Patient _patient;

    protected MedicalCheckup() {}

    public MedicalCheckup(String checkupDetails, LocalDate checkupDate, Patient patient) {
        this._checkupDetails = checkupDetails;
        this._checkupDate = checkupDate;
        this._patient = patient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkupId", nullable = false, updatable = false)
    public long getCheckupId() { return this._checkupId; }
    protected void setCheckupId(long checkupId) { this._checkupId = checkupId; }

    @Column(name = "checkupDetails", nullable = false, length = 2000)
    public String getCheckupDetails() { return this._checkupDetails; }
    public void setCheckupDetails(String checkupDetails) { this._checkupDetails = checkupDetails; }

    @Column(name = "checkupDate", nullable = false)
    public LocalDate getCheckupDate() { return this._checkupDate; }
    public void setCheckupDate(LocalDate checkupDate) { this._checkupDate = checkupDate; }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
