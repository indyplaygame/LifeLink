package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    Vaccine findByVaccineId(long id);
    Page<Vaccine> findAllByPatient(Patient patient, Pageable pageable);
}
