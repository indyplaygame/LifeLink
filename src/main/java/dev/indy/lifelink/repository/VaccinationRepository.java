package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.Vaccination;
import dev.indy.lifelink.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
    Vaccination findByVaccinationId(long id);
    Page<Vaccination> findAllByPatient(Patient patient, Pageable pageable);
    List<Vaccination> findAllByPatient(Patient patient);

    @Query("SELECT COUNT(v) > 0 FROM Vaccination v WHERE v.vaccine = :vaccine")
    boolean existsByVaccine(Vaccine vaccine);
}
