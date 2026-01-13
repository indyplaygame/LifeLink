package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Medicine;
import dev.indy.lifelink.model.MedicineSchedule;
import dev.indy.lifelink.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;

public interface MedicineScheduleRepository extends JpaRepository<MedicineSchedule, Long> {
    MedicineSchedule findByScheduleId(long id);
    Page<MedicineSchedule> findAllByPatient(Patient patient, Pageable pageable);

    @Query("SELECT s FROM MedicineSchedule s WHERE s.patient = :patient AND s.executionTime BETWEEN :lower AND :upper")
    List<MedicineSchedule> findMedicineSchedulesBetween(Patient patient, LocalTime lower, LocalTime upper);

    @Query("SELECT COUNT(s) > 0 FROM MedicineSchedule s WHERE s.medicine = :medicine")
    boolean existsByMedicine(Medicine medicine);
}
