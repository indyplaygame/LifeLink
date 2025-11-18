package dev.indy.lifelink.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "vaccinations")
@SuppressWarnings("AssociationNotMarkedInspection")
public class Vaccination {
    private long _vaccinationId;
    private int _doseNumber;
    private LocalDate _vaccinationDate;
    private String _notes;
    private Vaccine _vaccine;
    private Patient _patient;

    protected Vaccination() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccinationId", nullable = false, updatable = false)
    public long getVaccinationId() { return this._vaccinationId; }
    protected void setVaccinationId(long vaccinationId) { this._vaccinationId = vaccinationId; }

    @Column(name = "doseNumber", nullable = false)
    public int getDoseNumber() { return this._doseNumber; }
    public void setDoseNumber(int doseNumber) { this._doseNumber = doseNumber; }

    @Column(name = "vaccinationDate", nullable = false)
    public LocalDate getVaccinationDate() { return this._vaccinationDate; }
    public void setVaccinationDate(LocalDate vaccinationDate) { this._vaccinationDate = vaccinationDate; }

    @Column(name = "notes", nullable = true, length = 1000)
    public String getNotes() { return this._notes; }
    public void setNotes(String notes) { this._notes = notes; }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccineId", nullable = false)
    public Vaccine getVaccine() { return this._vaccine; }
    public void setVaccine(Vaccine vaccine) { this._vaccine = vaccine; }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }
}
