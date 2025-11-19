package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.MedicalCheckup;
import dev.indy.lifelink.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCheckupRepository extends JpaRepository<MedicalCheckup, Long> {
    MedicalCheckup findByCheckupId(long id);
    Page<MedicalCheckup> findAllByPatient(Patient patient, Pageable pageable);
}
