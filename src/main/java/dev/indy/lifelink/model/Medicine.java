package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medicines")
@SuppressWarnings("AssociationNotMarkedInspection")
public class Medicine {
    private long _medicineId;
    private String _medicineName;
    private String _notes;
    private String _dosage;
    private String _frequency;
    private LocalDate _startDate;
    private LocalDate _endDate;
    private Patient _patient;

    protected Medicine() {}

    public Medicine(String medicineName, String notes, String dosage, String frequency, LocalDate startDate, LocalDate endDate, Patient patient) {
        this._medicineName = medicineName;
        this._notes = notes;
        this._dosage = dosage;
        this._frequency = frequency;
        this._startDate = startDate;
        this._endDate = endDate;
        this._patient = patient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicineId", nullable = false, updatable = false)
    public long getMedicineId() { return this._medicineId; }
    protected void setMedicineId(long medicineId) { this._medicineId = medicineId; }

    @Column(name = "medicineName", nullable = false, length = 100)
    public String getMedicineName() { return this._medicineName; }
    public void setMedicineName(String medicineName) { this._medicineName = medicineName; }

    @Column(name = "notes", nullable = true, length = 1000)
    public String getNotes() { return this._notes; }
    public void setNotes(String notes) { this._notes = notes; }

    @Column(name = "dosage", nullable = false, length = 50)
    public String getDosage() { return this._dosage; }
    public void setDosage(String dosage) { this._dosage = dosage; }

    @Column(name = "frequency", nullable = false, length = 50)
    public String getFrequency() { return this._frequency; }
    public void setFrequency(String frequency) { this._frequency = frequency; }

    @Column(name = "startDate", nullable = false)
    public LocalDate getStartDate() { return this._startDate; }
    public void setStartDate(LocalDate startDate) { this._startDate = startDate; }

    @Column(name = "endDate", nullable = true)
    public LocalDate getEndDate() { return this._endDate; }
    public void setEndDate(LocalDate endDate) { this._endDate = endDate; }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
