package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {}
