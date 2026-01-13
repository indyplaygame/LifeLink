package dev.indy.lifelink.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "medicine_logs")
@SuppressWarnings("AssociationNotMarkedInspection")
public class MedicineScheduleLog {
    private long _entryId;
    private LocalDate _date;
    private LocalTime _timestamp;
    private MedicineSchedule _schedule;

    protected MedicineScheduleLog() {}

    public MedicineScheduleLog(MedicineSchedule schedule) {
        this._schedule = schedule;
        this._date = LocalDate.now();
        this._timestamp = LocalTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryId", nullable = false, updatable = false)
    public long getEntryId() { return this._entryId; }
    protected void setEntryId(long entryId) { this._entryId = entryId; }

    @Column(name = "date", nullable = false)
    public LocalDate getDate() { return this._date; }
    public void setDate(LocalDate date) { this._date = date; }

    @Column(name = "timestamp", nullable = false)
    public LocalTime getTimestamp() { return this._timestamp; }
    public void setTimestamp(LocalTime timestamp) { this._timestamp = timestamp; }

    @ManyToOne
    @JoinColumn(name = "scheduleId", nullable = false)
    public MedicineSchedule getSchedule() { return this._schedule; }
    public void setSchedule(MedicineSchedule schedule) { this._schedule = schedule; }
}
