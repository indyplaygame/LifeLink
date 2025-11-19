package dev.indy.lifelink.model;

import jakarta.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medicalDiagnoses")
@SuppressWarnings("AssociationNotMarkedInspection")
public class MedicalDiagnosis {
    private long _diagnosisId;
    private String _icdCode;
    private String _description;
    private Patient _patient;

    protected MedicalDiagnosis() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosisId", nullable = false, updatable = false)
    public long getDiagnosisId() { return this._diagnosisId; }
    protected void setDiagnosisId(long diagnosisId) { this._diagnosisId = diagnosisId; }

    @Column(name = "icdCode", nullable = false, length = 10)
    public String getIcdCode() { return this._icdCode; }
    public void setIcdCode(String icdCode) { this._icdCode = icdCode; }

    @Column(name = "description", nullable = false, length = 1000)
    public String getDescription() { return this._description; }
    public void setDescription(String description) { this._description = description; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
