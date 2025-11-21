package dev.indy.lifelink.service;

import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.model.response.PageResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PatientService {
    private final AuthService _authService;
    private final AllergyService _allergyService;
    private final ChronicDiseaseService _chronicDiseaseService;
    private final MedicalCheckupService _medicalCheckupService;
    private final MedicalDiagnosisService _medicalDiagnosisService;
    private final MedicalProcedureService _medicalProcedureService;
    private final MedicineService _medicineService;
    private final VaccinationService _vaccinationService;

    public PatientService(
        AuthService authService,
        AllergyService allergyService,
        ChronicDiseaseService chronicDiseaseService,
        MedicalCheckupService medicalCheckupService,
        MedicalDiagnosisService medicalDiagnosisService,
        MedicalProcedureService medicalProcedureService,
        MedicineService medicineService,
        VaccinationService vaccinationService
    ) {
        this._authService = authService;
        this._allergyService = allergyService;
        this._chronicDiseaseService = chronicDiseaseService;
        this._medicalCheckupService = medicalCheckupService;
        this._medicalDiagnosisService = medicalDiagnosisService;
        this._medicalProcedureService = medicalProcedureService;
        this._medicineService = medicineService;
        this._vaccinationService = vaccinationService;
    }

    public Map<String, ?> getPatientCard(String nfcUid) {
        Patient patient = this._authService.getPatientByNfcTag(nfcUid);

        return Map.of(
            "patient", patient,
            "card", Map.of(
                "allergies", this._allergyService.listPatientAllergies(patient),
                "chronicDiseases", this._chronicDiseaseService.listPatientChronicDiseases(patient),
                "medicalCheckups", this._medicalCheckupService.listPatientMedicalCheckups(patient),
                "medicalDiagnoses", this._medicalDiagnosisService.listPatientMedicalDiagnoses(patient),
                "medicalProcedures", this._medicalProcedureService.listPatientMedicalProcedures(patient),
                "medicines", this._medicineService.listPatientMedicines(patient),
                "vaccinations", this._vaccinationService.listPatientVaccinations(patient)
            )
        );
    }
}
