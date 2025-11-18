package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.MedicalDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDiagnosisRepository extends JpaRepository<MedicalDiagnosis, Long> {}
