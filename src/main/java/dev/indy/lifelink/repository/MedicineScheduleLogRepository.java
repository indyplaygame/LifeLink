package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.MedicineSchedule;
import dev.indy.lifelink.model.MedicineScheduleLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MedicineScheduleLogRepository extends JpaRepository<MedicineScheduleLog, Long> {
    @Query("SELECT COUNT(l) > 0 FROM MedicineScheduleLog l WHERE l.schedule = :schedule AND l.date = :date")
    boolean existsMedicineScheduleLogByDate(MedicineSchedule schedule, LocalDate date);
}
