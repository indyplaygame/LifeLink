package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {}
