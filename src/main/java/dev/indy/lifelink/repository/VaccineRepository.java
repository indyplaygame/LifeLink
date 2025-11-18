package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {}
