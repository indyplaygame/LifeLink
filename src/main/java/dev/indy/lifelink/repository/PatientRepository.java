package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Patient findByPatientId(UUID patientId);

    Patient findByPesel(String pesel);
}
