package dev.indy.lifelink.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medicalCheckups")
public class MedicalCheckup {
    private long _checkupId;
    private String _checkupDetails;
    private LocalDate _checkupDate;

    protected MedicalCheckup() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkupId", nullable = false, updatable = false)
    public long getCheckupId() { return this._checkupId; }
    protected void setCheckupId(long checkupId) { this._checkupId = checkupId; }

    @Column(name = "checkupDetails", nullable = false, length = 1000)
    public String getCheckupDetails() { return this._checkupDetails; }
    public void setCheckupDetails(String checkupDetails) { this._checkupDetails = checkupDetails; }

    @Column(name = "checkupDate", nullable = false)
    public LocalDate getCheckupDate() { return this._checkupDate; }
    public void setCheckupDate(LocalDate checkupDate) { this._checkupDate = checkupDate; }
}
