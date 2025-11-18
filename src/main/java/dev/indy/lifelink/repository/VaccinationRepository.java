package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {}
