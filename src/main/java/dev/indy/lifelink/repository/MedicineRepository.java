package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.ChronicDisease;
import dev.indy.lifelink.model.Medicine;
import dev.indy.lifelink.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByMedicineId(long id);
    Page<Medicine> findAllByPatient(Patient patient, Pageable pageable);
}
