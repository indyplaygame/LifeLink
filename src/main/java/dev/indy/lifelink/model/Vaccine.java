package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "vaccines")
@SuppressWarnings("AssociationNotMarkedInspection")
public class Vaccine {
    private long _vaccineId;
    private String _name;
    private Patient _patient;

    protected Vaccine() {}

    public Vaccine(String name, Patient patient) {
        this._name = name;
        this._patient = patient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccineId", nullable = false, updatable = false)
    public long getVaccineId() { return this._vaccineId; }
    protected void setVaccineId(long vaccineId) { this._vaccineId = vaccineId; }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() { return this._name; }
    public void setName(String name) { this._name = name; }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
