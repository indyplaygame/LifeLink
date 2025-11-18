package dev.indy.lifelink.model;

import jakarta.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "vaccines")
public class Vaccine {
    private long _vaccineId;
    private String _name;

    protected Vaccine() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccineId", nullable = false, updatable = false)
    public long getVaccineId() { return this._vaccineId; }
    protected void setVaccineId(long vaccineId) { this._vaccineId = vaccineId; }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() { return this._name; }
    public void setName(String name) { this._name = name; }
}
