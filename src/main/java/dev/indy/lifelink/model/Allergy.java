package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Access(AccessType.PROPERTY)
@Table(name = "allergies")
@SuppressWarnings("AssociationNotMarkedInspection")
public class Allergy {
    private long _allergyId;
    private String _name;
    private String _description;
    private Patient _patient;

    protected Allergy() {}

    public Allergy(String name, String description, Patient patient) {
        this._name = name;
        this._description = description;
        this._patient = patient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergyId", nullable = false, updatable = false)
    public long getAllergyId() { return this._allergyId; }
    protected void setAllergyId(long allergyId) { this._allergyId = allergyId; }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() { return this._name; }
    public void setName(String name) { this._name = name; }

    @Column(name = "description", nullable = true, length = 1000)
    public String getDescription() { return this._description; }
    public void setDescription(String description) { this._description = description; }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
