package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.MedicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, Long> {}
