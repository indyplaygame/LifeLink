package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medical_diagnoses")
@SuppressWarnings("AssociationNotMarkedInspection")
public class MedicalDiagnosis {
    private long _diagnosisId;
    private String _icdCode;
    private String _description;
    private LocalDate _diagnosisDate;
    private Patient _patient;

    protected MedicalDiagnosis() {}

    public MedicalDiagnosis(String icdCode, String description, LocalDate diagnosisDate, Patient patient) {
        this._icdCode = icdCode.toUpperCase();
        this._description = description;
        this._diagnosisDate = diagnosisDate;
        this._patient = patient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosisId", nullable = false, updatable = false)
    public long getDiagnosisId() { return this._diagnosisId; }
    protected void setDiagnosisId(long diagnosisId) { this._diagnosisId = diagnosisId; }

    @Column(name = "icdCode", nullable = false, length = 8)
    public String getIcdCode() { return this._icdCode; }
    public void setIcdCode(String icdCode) { this._icdCode = icdCode; }

    @Column(name = "description", nullable = false, length = 1000)
    public String getDescription() { return this._description; }
    public void setDescription(String description) { this._description = description; }

    @Column(name = "diagnosisDate", nullable = false)
    public LocalDate getDiagnosisDate() { return this._diagnosisDate; }
    public void setDiagnosisDate(LocalDate diagnosisDate) { this._diagnosisDate = diagnosisDate; }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
