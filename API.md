# Table of Contents
- **[Model](#model)**
  - **[Page](#paget)**
  - **[Address](#address)**
  - **[Allergy](#allergy)**
  - **[ChronicDisease](#chronicdisease)**
  - **[MedicalCheckup](#medicalcheckup)**
  - **[MedicalDiagnosis](#medicaldiagnosis)**
  - **[MedicalProcedure](#medicalprocedure)**
  - **[Medicine](#medicine)**
  - **[Patient](#patient)**
  - **[Person](#person)**
  - **[Vaccination](#vaccination)**
  - **[Vaccine](#vaccine)**


# Model
Represents the JSON representation of the application models used in the API.

## Page[T]
Defines the structure of a paginated response of ```T``` objects.
```json
{
  "items": "List[T]",
  "page": "Integer",
  "size": "Integer",
  "totalPages": "Integer",
  "totalItems": "Long",
  "hasPrevious": "Boolean",
  "hasNext": "Boolean"
}
```

## Address
Defines the structure of an address object.
```json
{
  "country" : "String",
  "postalCode": "String",
  "city": "String",
  "street": "String",
  "buildingNumber": "String"
}
```

## Allergy
Defines the structure of an allergy object.
```json
{
  "allergyId": "Long",
  "name": "String",
  "description": "String (optional)"
}
```

## ChronicDisease
Defines the structure of a chronic disease object.
```json
{
  "diseaseId": "Long",
  "name": "String",
  "notes": "String (optional)",
  "diagnosisDate": "String (DD-MM-YYYY)"
}
```

## MedicalCheckup
Defines the structure of a medical checkup object.
```json
{
  "checkupId": "Long",
  "checkupDetails": "String",
  "checkupDate": "String (DD-MM-YYYY)"
}
```

## MedicalDiagnosis
Defines the structure of a medical diagnosis object.
```json
{
  "diagnosisId": "Long",
  "icdCode": "String",
  "description": "String",
  "diagnosisDate": "String (DD-MM-YYYY)"
}
```

## MedicalProcedure
Defines the structure of a medical procedure object.
```json
{
  "procedureId": "Long",
  "cptCode": "String",
  "procedureDescription": "String",
  "procedureDate": "String (DD-MM-YYYY)"
}
```

## Medicine
Defines the structure of a medicine object.
```json
{
  "medicineId": "Long",
  "medicineName": "String",
  "notes": "String (optional)",
  "dosage": "String",
  "frequency": "String",
  "startDate": "String (DD-MM-YYYY)",
  "endDate": "String (DD-MM-YYYY)"
}
```

## Patient
Defines the structure of a patient object.
```json
{
  "patientId": "UUID (String)",
  "dateOfBirth": "String (DD-MM-YYYY)",
  "email": "String",
  "pesel": "String",
  "bloodType": "Enum (A+, A-, B+, B-, AB+, AB-, O+, O-)",
  "person": "Person",
  "contactPerson": "Person (optional)"
}
```

## Person
Defines the structure of a person object.
```json
{
  "personId": "Long",
  "firstName": "String",
  "middleName": "String (optional)",
  "lastName": "String",
  "phoneNumber": "String",
  "gender": "Enum (MALE, FEMALE)",
  "address": "Address"
}
```

## Vaccination
Defines the structure of a vaccination object.
```json
{
  "vaccinationId": "Long",
  "doseNumber": "Integer",
  "vaccinationDate": "String (DD-MM-YYYY)",
  "notes": "String (optional)",
  "vaccine": "Vaccine"
}
```

## Vaccine
Defines the structure of a vaccine object.
```json
{
  "vaccineId": "Long",
  "vaccineName": "String"
}
```