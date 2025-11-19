package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medical_procedures")
@SuppressWarnings("AssociationNotMarkedInspection")
public class MedicalProcedure {
    private long _procedureId;
    private String _cptCode;
    private String _procedureDescription;
    private LocalDate _procedureDate;
    private Patient _patient;

    protected MedicalProcedure() {}

    public MedicalProcedure(String cptCode, String procedureDescription, LocalDate procedureDate, Patient patient) {
        this._cptCode = cptCode;
        this._procedureDescription = procedureDescription;
        this._procedureDate = procedureDate;
        this._patient = patient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procedureId", nullable = false, updatable = false)
    public long getProcedureId() { return this._procedureId; }
    protected void setProcedureId(long procedureId) { this._procedureId = procedureId; }

    @Column(name = "cptCode", nullable = false, length = 5)
    public String getCptCode() { return this._cptCode; }
    public void setCptCode(String cptCode) { this._cptCode = cptCode; }

    @Column(name = "procedureDescription", nullable = false, length = 1000)
    public String getProcedureDescription() { return this._procedureDescription; }
    public void setProcedureDescription(String procedureDescription) { this._procedureDescription = procedureDescription; }

    @Column(name = "procedureDate", nullable = false)
    public LocalDate getProcedureDate() { return this._procedureDate; }
    public void setProcedureDate(LocalDate procedureDate) { this._procedureDate = procedureDate; }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
