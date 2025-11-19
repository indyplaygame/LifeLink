package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "chronicDiseases")
@SuppressWarnings("AssociationNotMarkedInspection")
public class ChronicDisease {
    private long _diseaseId;
    private String _name;
    private String _notes;
    private LocalDate _diagnosisDate;
    private Patient _patient;

    protected ChronicDisease() {}

    public ChronicDisease(String name, String notes, LocalDate diagnosisDate, Patient patient) {
        this.setName(name);
        this.setNotes(notes);
        this.setDiagnosisDate(diagnosisDate);
        this.setPatient(patient);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diseaseId", nullable = false, updatable = false)
    public long getDiseaseId() { return this._diseaseId; }
    protected void setDiseaseId(long diseaseId) { this._diseaseId = diseaseId; }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() { return this._name; }
    public void setName(String name) { this._name = name; }

    @Column(name = "notes", nullable = true, length = 2000)
    public String getNotes() { return this._notes; }
    public void setNotes(String notes) { this._notes = notes; }

    @Column(name = "diagnosisDate", nullable = false)
    public LocalDate getDiagnosisDate() { return this._diagnosisDate; }
    public void setDiagnosisDate(LocalDate diagnosisDate) { this._diagnosisDate = diagnosisDate; }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
