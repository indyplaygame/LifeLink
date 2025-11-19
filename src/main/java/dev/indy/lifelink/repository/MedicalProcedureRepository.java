package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.MedicalProcedure;
import dev.indy.lifelink.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, Long> {
    MedicalProcedure findByProcedureId(Long id);
    Page<MedicalProcedure> findAllByPatient(Patient patient, Pageable pageable);
}
