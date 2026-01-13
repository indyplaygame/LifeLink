package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.indy.lifelink.persistence.converter.WeekDaysConverter;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "schedules")
@SuppressWarnings("AssociationNotMarkedInspection")
public class MedicineSchedule {
    private long _scheduleId;
    private Set<DayOfWeek> _weekDays;
    private LocalTime _executionTime;
    private String _notes;
    private String _dosage;
    private Patient _patient;
    private Medicine _medicine;
    private List<MedicineScheduleLog> _logs;

    protected MedicineSchedule() {}

    public MedicineSchedule(Patient patient, Medicine medicine, Set<DayOfWeek> weekDays, LocalTime executionTime, String notes, String dosage) {
        this._patient = patient;
        this._medicine = medicine;
        this._weekDays = weekDays;
        this._executionTime = executionTime;
        this._notes = notes;
        this._dosage = dosage;
        this._logs = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleId", nullable = false, updatable = false)
    public long getScheduleId() { return this._scheduleId; }
    protected void setScheduleId(long scheduleId) { this._scheduleId = scheduleId; }

    @Convert(converter = WeekDaysConverter.class)
    @Column(name = "weekDays", nullable = false, length = 56)
    public Set<DayOfWeek> getWeekDays() { return this._weekDays; }
    public void setWeekDays(Set<DayOfWeek> weekDays) { this._weekDays = weekDays; }

    @Column(name = "executionTime", nullable = false)
    public LocalTime getExecutionTime() { return this._executionTime; }
    public void setExecutionTime(LocalTime time) { this._executionTime = time; }

    @Column(name = "notes", nullable = true, length = 1000)
    public String getNotes() { return this._notes; }
    public void setNotes(String notes) { this._notes = notes; }

    @Column(name = "dosage", nullable = false, length = 50)
    public String getDosage() { return this._dosage; }
    public void setDosage(String dosage) { this._dosage = dosage; }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    public Patient getPatient() { return this._patient; }
    public void setPatient(Patient patient) { this._patient = patient; }

    @ManyToOne
    @JoinColumn(name = "medicineId", nullable = false)
    public Medicine getMedicine() { return this._medicine; }
    public void setMedicine(Medicine medicine) { this._medicine = medicine; }

    @JsonIgnore
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE, orphanRemoval = true)
    public List<MedicineScheduleLog> getLogs() { return this._logs; }
    public void setLogs(List<MedicineScheduleLog> logs) { this._logs = logs; }
}
