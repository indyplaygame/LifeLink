package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.MedicalDiagnosis;
import dev.indy.lifelink.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalDiagnosisRepository extends JpaRepository<MedicalDiagnosis, Long> {
    MedicalDiagnosis findByDiagnosisId(Long id);
    Page<MedicalDiagnosis> findAllByPatient(Patient patient, Pageable pageable);
    List<MedicalDiagnosis> findAllByPatient(Patient patient);
}
