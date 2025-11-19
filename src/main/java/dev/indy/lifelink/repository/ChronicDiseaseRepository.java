package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.ChronicDisease;
import dev.indy.lifelink.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChronicDiseaseRepository extends JpaRepository<ChronicDisease, Long> {
    ChronicDisease findByDiseaseId(long id);
    Page<ChronicDisease> findAllByPatient(Patient patient, Pageable pageable);
}
