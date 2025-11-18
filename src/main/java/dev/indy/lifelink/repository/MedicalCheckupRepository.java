package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.MedicalCheckup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCheckupRepository extends JpaRepository<MedicalCheckup, Long> {}
