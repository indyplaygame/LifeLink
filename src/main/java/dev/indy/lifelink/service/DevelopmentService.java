package dev.indy.lifelink.service;

import dev.indy.lifelink.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev", "stage"})
@Service
public class DevelopmentService {
    private final AllergyRepository _allergyRepository;
    private final ChronicDiseaseRepository _chronicDiseaseRepository;
    private final MedicalCheckupRepository _medicalCheckupRepository;
    private final MedicalDiagnosisRepository _medicalDiagnosisRepository;
    private final MedicalProcedureRepository _medicalProcedureRepository;
    private final MedicineRepository _medicineRepository;
    private final PatientRepository _patientRepository;
    private final PersonRepository _personRepository;
    private final VaccinationRepository _vaccinationRepository;
    private final VaccineRepository _vaccineRepository;

    @Autowired
    public DevelopmentService(
        AllergyRepository allergyRepository,
        ChronicDiseaseRepository chronicDiseaseRepository,
        MedicalCheckupRepository medicalCheckupRepository,
        MedicalDiagnosisRepository medicalDiagnosisRepository,
        MedicalProcedureRepository medicalProcedureRepository,
        MedicineRepository medicineRepository,
        PatientRepository patientRepository,
        PersonRepository personRepository,
        VaccinationRepository vaccinationRepository,
        VaccineRepository vaccineRepository
    ) {
        this._allergyRepository = allergyRepository;
        this._chronicDiseaseRepository = chronicDiseaseRepository;
        this._medicalCheckupRepository = medicalCheckupRepository;
        this._medicalDiagnosisRepository = medicalDiagnosisRepository;
        this._medicalProcedureRepository = medicalProcedureRepository;
        this._medicineRepository = medicineRepository;
        this._patientRepository = patientRepository;
        this._personRepository = personRepository;
        this._vaccinationRepository = vaccinationRepository;
        this._vaccineRepository = vaccineRepository;
    }

    public void clearDatabase() {
        this._allergyRepository.deleteAll();
        this._chronicDiseaseRepository.deleteAll();
        this._medicalCheckupRepository.deleteAll();
        this._medicalDiagnosisRepository.deleteAll();
        this._medicalProcedureRepository.deleteAll();
        this._medicineRepository.deleteAll();
        this._vaccinationRepository.deleteAll();
        this._vaccineRepository.deleteAll();
        this._patientRepository.deleteAll();
        this._personRepository.deleteAll();
    }
}
