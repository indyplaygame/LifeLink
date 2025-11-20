package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Allergy;
import dev.indy.lifelink.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    Allergy findByAllergyId(long id);
    Page<Allergy> findAllByPatient(Patient patient, Pageable pageable);
    List<Allergy> findAllByPatient(Patient patient);
}
