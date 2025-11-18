package dev.indy.lifelink.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medicalProcedures")
public class MedicalProcedure {
    private long _procedureId;
    private String _procedureCode;
    private String _procedureDescription;
    private LocalDate _procedureDate;

    protected MedicalProcedure() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procedureId", nullable = false, updatable = false)
    public long getProcedureId() { return this._procedureId; }
    protected void setProcedureId(long procedureId) { this._procedureId = procedureId; }

    @Column(name = "procedureCode", nullable = false, length = 50)
    public String getProcedureCode() { return this._procedureCode; }
    public void setProcedureCode(String procedureCode) { this._procedureCode = procedureCode; }

    @Column(name = "procedureDescription", nullable = false, length = 1000)
    public String getProcedureDescription() { return this._procedureDescription; }
    public void setProcedureDescription(String procedureDescription) { this._procedureDescription = procedureDescription; }

    @Column(name = "procedureDate", nullable = false)
    public LocalDate getProcedureDate() { return this._procedureDate; }
    public void setProcedureDate(LocalDate procedureDate) { this._procedureDate = procedureDate; }
}
