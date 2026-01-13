package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medicines")
@SuppressWarnings("AssociationNotMarkedInspection")
public class Medicine {
    private long _medicineId;
    private String _medicineName;
    private String _notes;
    private Patient _patient;

    protected Medicine() {}

    public Medicine(String medicineName, String notes, Patient patient) {
        this._medicineName = medicineName;
        this._notes = notes;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
