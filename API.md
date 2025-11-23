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
- **[Pagination](#pagination)**
- **[Authentication](#authentication)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Register](#register)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Login](#login)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Logout](#logout)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Generate Token](#generate-token)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Session Status](#session-status)**
  - **[<code style="color: rgb(182, 168, 225)">PATCH</code> Register NFC Tag](#register-nfc-tag)**
  - **[<code style="color: rgb(182, 168, 225)">PATCH</code> Deregister NFC Tag](#deregister-nfc-tag)**
- **[Allergies](#allergies)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Add](#add)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get](#get)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> List](#list)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update)**
  - **[<code style="color: rgb(234, 154, 142)">DELETE</code> Delete](#delete)**
- **[Chronic Diseases](#chronic-diseases)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Add](#add-1)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get](#get-1)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> List](#list-1)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update-1)**
  - **[<code style="color: rgb(234, 154, 142)">DELETE</code> Delete](#delete-1)**
- **[Medical Checkups](#medical-checkups)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Add](#add-2)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get](#get-2)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> List](#list-2)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update-2)**
  - **[<code style="color: rgb(234, 154, 142)">DELETE</code> Delete](#delete-2)**
- **[Medical Diagnoses](#medical-diagnoses)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Add](#add-3)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get](#get-3)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> List](#list-3)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update-3)**
  - **[<code style="color: rgb(234, 154, 142)">DELETE</code> Delete](#delete-3)**
- **[Medical Procedures](#medical-procedures)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Add](#add-4)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get](#get-4)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> List](#list-4)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update-4)**
  - **[<code style="color: rgb(234, 154, 142)">DELETE</code> Delete](#delete-4)**
- **[Medicines](#medicines)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Add](#add-5)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get](#get-5)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> List](#list-5)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update-5)**
  - **[<code style="color: rgb(234, 154, 142)">DELETE</code> Delete](#delete-5)**
- **[Vaccinations](#vaccinations)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Add](#add-6)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get](#get-6)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> List](#list-6)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update-6)**
  - **[<code style="color: rgb(234, 154, 142)">DELETE</code> Delete](#delete-6)**
- **[Vaccines](#vaccines)**
  - **[<code style="color: rgb(250, 224, 124)">POST</code> Add](#add-7)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get](#get-7)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> List](#list-7)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update-7)**
  - **[<code style="color: rgb(234, 154, 142)">DELETE</code> Delete](#delete-7)**
- **[Patients](#patients)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get Medical Card](#get-medical-card)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Get Details](#get-details)**
  - **[<code style="color: rgb(103, 174, 246)">PUT</code> Update](#update)**
- **[Other Endpoints](#other-endpoints)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Ping](#ping)**
  - **[<code style="color: rgb(95, 221, 154)">GET</code> Health Check](#health-check)**

# Model
Specifies the JSON representation of the application models used in the API.

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
  "name": "String"
}
```


# Pagination
Standard pagination parameters for list endpoints.
- `page`: Integer (optional, default: 0) - The page number to retrieve (0-indexed).
- `size`: Integer (optional, default: 10) - The number of items per page.
- `sort`: String (optional, default: "id,asc") - The sorting criteria in the format: `property,(asc|desc)`. Multiple sort criteria can be provided.


# Authentication
Endpoints for user authentication and authorization.

## Register
**URL:** `/auth/register`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Not required<br>
**Content-Type:** `application/json`<br>
**Description:** Register a new user.<br>

### **Request Body:**
```json
{
  "dateOfBirth": "String (DD-MM-YYYY)",
  "email": "String",
  "pesel": "String",
  "bloodType": "Enum (A+, A-, B+, B-, AB+, AB-, O+, O-)",
  "password": "String",
  "person": {
    "firstName": "String",
    "middleName": "String",
    "lastName": "String",
    "phoneNumber": "String",
    "gender": "Enum (MALE, FEMALE)",
    "address": {
      "country": "String (optional, default: POLAND)",
      "postalCode": "String",
      "city": "String",
      "street": "String",
      "buildingNumber": "String"
    }
  },
  "emergencyContact": {
    "firstName": "String",
    "middleName": "String",
    "lastName": "String",
    "phoneNumber": "String",
    "gender": "Enum (MALE, FEMALE)",
    "address": {
      "country": "String (optional, default: POLAND)",
      "postalCode": "String",
      "city": "String",
      "street": "String",
      "buildingNumber": "String"
    }
  }
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: User successfully registered.<br>
```json
{
  "message": "User created successfully"
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "dateOfBirth": "String[]",
    "email": "String[]",
    "pesel": "String[]",
    "password": "String[]",
    "bloodType": "String[]",
    "person.firstName": "String[]",
    "person.middleName": "String[]",
    "person.lastName": "String[]",
    "person.phoneNumber": "String[]",
    "person.gender": "String[]",
    "person.address.postalCode": "String[]",
    "person.address.city": "String[]",
    "person.address.street": "String[]",
    "person.address.buildingNumber": "String[]",
    "emergencyContact.firstName": "String[]",
    "emergencyContact.middleName": "String[]",
    "emergencyContact.lastName": "String[]",
    "emergencyContact.phoneNumber": "String[]",
    "emergencyContact.gender": "String[]",
    "emergencyContact.address.postalCode": "String[]",
    "emergencyContact.address.city": "String[]",
    "emergencyContact.address.street": "String[]",
    "emergencyContact.address.buildingNumber": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">409 Conflict</code><br>
**Description**: Patient with the given PESEL already exists.<br>

```json
{
  "details": "Patient with the given PESEL already exists."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">409 Conflict</code><br>
**Description**: An user is already logged in this session.<br>

```json
{
  "details": "An user is already logged in this session."
}
```
<br>

## Login
**URL:** `/auth/login`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Not required<br>
**Content-Type:** `application/json`<br>
**Description:** Authenticate a user.<br>

### **Request Body:**
```json
{
  "pesel": "String",
  "password": "String"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Successful authentication.<br>
```json
{
  "message": "Logged in successfully"
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "pesel": "String[]",
    "password": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid PESEL or password provided.<br>

```json
{
  "details": "Invalid authentication credentials provided."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">409 Conflict</code><br>
**Description**: An user is already logged in this session.<br>

```json
{
  "details": "An user is already logged in this session."
}
```
<br>

## Logout
**URL:** `/auth/logout`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Not required<br>
**Content-Type:** `application/json`<br>
**Description:** Log out of the current session.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Logged out successfully.<br>
```json
{
  "message": "Logged out successfully"
}
```
<br>

## Generate Token
**URL:** `/auth/generate-token`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Not required<br>
**Content-Type:** `application/json`<br>
**Description:** Generate token for medical card retrieval.<br>

### **Request Body:**
```json
{
  "nfcTagUid": "String",
  "nfcCode": "String"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Token generated successfully.<br>
```json
{
  "token": "String"
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "nfcTagUid": "String[]",
    "nfcCode": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid NFC code provided.<br>

```json
{
  "details": "Invalid authentication credentials provided."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: No patient found for the provided NFC tag.<br>

```json
{
  "details": "No patient found for the provided NFC tag."
}
```
<br>

## Session Status
**URL:** `/auth/session-status`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Not required<br>
**Content-Type:** `application/json`<br>
**Description:** Check the status of the current session.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Logged out successfully.<br>
```json
{
  "active": "Boolean"
}
```
<br>

## Register NFC Tag
**URL:** `/auth/nfc/register`<br>
**Method:** <code style="color: rgb(182, 168, 225)">PATCH</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Register NFC tag to the patient.<br>

### **Request Body:**
```json
{
  "nfcTagUid": "String",
  "nfcCode": "String"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: NFC tag registered successfully.<br>
```json
{
  "message": "NFC tag registered successfully."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "nfcTagUid": "String[]",
    "nfcCode": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Deregister NFC Tag
**URL:** `/auth/nfc/deregister`<br>
**Method:** <code style="color: rgb(182, 168, 225)">PATCH</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Deregister NFC tag from the patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: NFC tag registered successfully.<br>
```json
{
  "message": "NFC tag deregistered successfully."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "nfcTagUid": "String[]",
    "nfcCode": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: No NFC tag is registered for the active patient.<br>

```json
{
  "details": "No NFC tag is registered for the active patient."
}
```
<br>


# Allergies
Endpoints for managing patient allergies.

## Add
**URL:** `/allergies/add`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Add a new allergy to the patient's profile.<br>

### **Request Body:**
```json
{
  "name": "String",
  "description": "String (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: Allergy added successfully.<br>
**Body**: `Allergy`
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "name": "String[]",
    "description": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Get
**URL:** `/allergies/{id}`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve an allergy by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Allergy retrieved successfully.<br>
**Body**: `Allergy`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this allergy.<br>

```json
{
  "details": "This Allergy does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Allergy with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Allergy with ID {id}"
}
```
<br>

## List
**URL:** `/allergies/list`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required<br>
**Paginated:** Yes (Default size: 20, Max size: 50)<br>
**Content-Type:** None<br>
**Description:** Retrieve a paginated list of allergies for the authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Allergies retrieved successfully.<br>
**Body**: `Page[Allergy]`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/allergies/{id}/update`<br>
**Method:** <code style="color: rgb(103, 174, 246)">PUT</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update an existing allergy by its `id`.<br>

### **Request Body:**
```json
{
  "name": "String (optional)",
  "description": "String (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Allergy updated successfully.<br>
**Body**: `Allergy`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "name": "String[]",
    "description": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this allergy.<br>

```json
{
  "details": "This Allergy does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Allergy with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Allergy with ID {id}"
}
```
<br>

## Delete
**URL:** `/allergies/{id}/delete`<br>
**Method:** <code style="color: rgb(234, 154, 142)">DELETE</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Delete an existing allergy by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">204 No Content</code><br>
**Description**: Allergy deleted successfully.<br>
**Body**: None<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this allergy.<br>

```json
{
  "details": "This Allergy does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Allergy with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Allergy with ID {id}"
}
```
<br>


# Chronic Diseases
Endpoints for managing patient chronic diseases.

## Add
**URL:** `/diseases/add`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Add a new chronic disease to the patient's profile.<br>

### **Request Body:**
```json
{
  "name": "String",
  "notes": "String (optional)",
  "diagnosisDate": "String (DD-MM-YYYY)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: Chronic disease added successfully.<br>
**Body**: `ChronicDisease`
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "name": "String[]",
    "notes": "String[]",
    "diagnosisDate": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Get
**URL:** `/diseases/{id}`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve a chronic disease by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Chronic disease retrieved successfully.<br>
**Body**: `ChronicDisease`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this chronic disease.<br>

```json
{
  "details": "This ChronicDisease does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: ChronicDisease with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find ChronicDisease with ID {id}"
}
```
<br>

## List
**URL:** `/diseases/list`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required<br>
**Paginated:** Yes (Default size: 20, Max size: 50)<br>
**Content-Type:** None<br>
**Description:** Retrieve a paginated list of chronic diseases for the authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Chronic diseases retrieved successfully.<br>
**Body**: `Page[ChronicDisease]`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/diseases/{id}/update`<br>
**Method:** <code style="color: rgb(103, 174, 246)">PUT</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update an existing chronic disease by its `id`.<br>

### **Request Body:**
```json
{
  "name": "String (optional)",
  "description": "String (optional)",
  "diagnosisDate": "String (DD-MM-YYYY) (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Chronic disease updated successfully.<br>
**Body**: `ChronicDisease`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "name": "String[]",
    "description": "String[]",
    "diagnosisDate": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this chronic disease.<br>

```json
{
  "details": "This ChronicDisease does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Chronic disease with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find ChronicDisease with ID {id}"
}
```
<br>

## Delete
**URL:** `/diseases/{id}/delete`<br>
**Method:** <code style="color: rgb(234, 154, 142)">DELETE</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Delete an existing chronic disease by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">204 No Content</code><br>
**Description**: Allergy deleted successfully.<br>
**Body**: None<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this chronic disease.<br>

```json
{
  "details": "This ChronicDisease does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Chronic disease with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find ChronicDisease with ID {id}"
}
```
<br>


# Medical Checkups
Endpoints for managing patient medical checkups.

## Add
**URL:** `/checkups/add`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Add a new medical checkup to the patient's profile.<br>

### **Request Body:**
```json
{
  "details": "String",
  "date": "String (DD-MM-YYYY)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: Medical checkup added successfully.<br>
**Body**: `MedicalCheckup`
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "details": "String[]",
    "date": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Get
**URL:** `/checkups/{id}`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve a medical checkup by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical checkup retrieved successfully.<br>
**Body**: `MedicalCheckup`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical checkup.<br>

```json
{
  "details": "This MedicalCheckup does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: MedicalCheckup with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalCheckup with ID {id}"
}
```
<br>

## List
**URL:** `/checkups/list`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required<br>
**Paginated:** Yes (Default size: 20, Max size: 50)<br>
**Content-Type:** None<br>
**Description:** Retrieve a paginated list of medical checkups for the authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical checkups retrieved successfully.<br>
**Body**: `Page[MedicalCheckup]`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/checkups/{id}/update`<br>
**Method:** <code style="color: rgb(103, 174, 246)">PUT</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update an existing medical checkup by its `id`.<br>

### **Request Body:**
```json
{
  "details": "String (optional)",
  "date": "String (DD-MM-YYYY) (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical checkup updated successfully.<br>
**Body**: `MedicalCheckup`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "details": "String[]",
    "date": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical checkup.<br>

```json
{
  "details": "This MedicalCheckup does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medical checkup with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalCheckup with ID {id}"
}
```
<br>

## Delete
**URL:** `/checkups/{id}/delete`<br>
**Method:** <code style="color: rgb(234, 154, 142)">DELETE</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Delete an existing medical checkup by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">204 No Content</code><br>
**Description**: Medical checkup deleted successfully.<br>
**Body**: None<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical checkup.<br>

```json
{
  "details": "This MedicalCheckup does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medical checkup with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalCheckup with ID {id}"
}
```
<br>


# Medical Diagnoses
Endpoints for managing patient medical diagnoses.

## Add
**URL:** `/diagnoses/add`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Add a new medical diagnosis to the patient's profile.<br>

### **Request Body:**
```json
{
  "icdCode": "String",
  "description": "String",
  "date": "String (DD-MM-YYYY)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: Medical diagnosis added successfully.<br>
**Body**: `MedicalDiagnosis`
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "icdCode": "String[]",
    "description": "String[]",
    "date": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Get
**URL:** `/diagnoses/{id}`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve a medical diagnosis by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical checkup retrieved successfully.<br>
**Body**: `MedicalDiagnosis`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical diagnosis.<br>

```json
{
  "details": "This MedicalDiagnosis does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medical diagnosis with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalDiagnosis with ID {id}"
}
```
<br>

## List
**URL:** `/diagnoses/list`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required<br>
**Paginated:** Yes (Default size: 20, Max size: 50)<br>
**Content-Type:** None<br>
**Description:** Retrieve a paginated list of medical diagnoses for the authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical diagnoses retrieved successfully.<br>
**Body**: `Page[MedicalDiagnosis]`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/diagnose/{id}/update`<br>
**Method:** <code style="color: rgb(103, 174, 246)">PUT</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update an existing medical diagnosis by its `id`.<br>

### **Request Body:**
```json
{
  "icdCode": "String (optional)",
  "description": "String (optional)",
  "date": "String (DD-MM-YYYY) (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical diagnosis updated successfully.<br>
**Body**: `MedicalDiagnosis`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "icdCode": "String[]",
    "description": "String[]",
    "date": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical diagnosis.<br>

```json
{
  "details": "This MedicalDiagnosis does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medical diagnosis with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalDiagnosis with ID {id}"
}
```
<br>

## Delete
**URL:** `/diagnoses/{id}/delete`<br>
**Method:** <code style="color: rgb(234, 154, 142)">DELETE</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Delete an existing medical diagnosis by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">204 No Content</code><br>
**Description**: Medical diagnosis deleted successfully.<br>
**Body**: None<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical diagnosis.<br>

```json
{
  "details": "This MedicalDiagnosis does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medical diagnosis with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalDiagnosis with ID {id}"
}
```
<br>


# Medical Procedures
Endpoints for managing patient medical procedures.

## Add
**URL:** `/procedures/add`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Add a new medical procedure to the patient's profile.<br>

### **Request Body:**
```json
{
  "cptCode": "String",
  "description": "String",
  "date": "String (DD-MM-YYYY)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: Medical procedure added successfully.<br>
**Body**: `MedicalProcedure`
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "cptCode": "String[]",
    "description": "String[]",
    "date": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Get
**URL:** `/procedures/{id}`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve a medical procedure by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical procedure retrieved successfully.<br>
**Body**: `MedicalProcedure`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical procedure.<br>

```json
{
  "details": "This MedicalProcedure does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medical procedure with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalProcedure with ID {id}"
}
```
<br>

## List
**URL:** `/procedures/list`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required<br>
**Paginated:** Yes (Default size: 20, Max size: 50)<br>
**Content-Type:** None<br>
**Description:** Retrieve a paginated list of medical procedures for the authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical procedures retrieved successfully.<br>
**Body**: `Page[MedicalProcedure]`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/procedures/{id}/update`<br>
**Method:** <code style="color: rgb(103, 174, 246)">PUT</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update an existing medical procedure by its `id`.<br>

### **Request Body:**
```json
{
  "cptCode": "String (optional)",
  "description": "String (optional)",
  "date": "String (DD-MM-YYYY) (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medical procedure updated successfully.<br>
**Body**: `MedicalProcedure`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "cptCode": "String[]",
    "description": "String[]",
    "date": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical procedure.<br>

```json
{
  "details": "This MedicalProcedure does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medical procedure with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalProcedure with ID {id}"
}
```
<br>

## Delete
**URL:** `/procedures/{id}/delete`<br>
**Method:** <code style="color: rgb(234, 154, 142)">DELETE</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Delete an existing medical procedure by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">204 No Content</code><br>
**Description**: Medical procedure deleted successfully.<br>
**Body**: None<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medical procedure.<br>

```json
{
  "details": "This MedicalProcedure does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medical procedure with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find MedicalProcedure with ID {id}"
}
```
<br>


# Medicines
Endpoints for managing patient medicines.

## Add
**URL:** `/medicines/add`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Add a new medicine to the patient's profile.<br>

### **Request Body:**
```json
{
  "name": "String",
  "notes": "String (optional)",
  "dosage": "String",
  "frequency": "String",
  "startDate": "String (DD-MM-YYYY)",
  "endDate": "String (DD-MM-YYYY) (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: Medicine added successfully.<br>
**Body**: `Medicine`
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "name": "String[]",
    "notes": "String[]",
    "dosage": "String[]",
    "frequency": "String[]",
    "startDate": "String[]",
    "endDate": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Get
**URL:** `/medicines/{id}`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve a medicine by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medicine retrieved successfully.<br>
**Body**: `Medicine`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medicine.<br>

```json
{
  "details": "This Medicine does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medicine with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Medicine with ID {id}"
}
```
<br>

## List
**URL:** `/medicines/list`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required<br>
**Paginated:** Yes (Default size: 20, Max size: 50)<br>
**Content-Type:** None<br>
**Description:** Retrieve a paginated list of medicines for the authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medicines retrieved successfully.<br>
**Body**: `Page[Medicine]`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/medicines/{id}/update`<br>
**Method:** <code style="color: rgb(103, 174, 246)">PUT</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update an existing medicine by its `id`.<br>

### **Request Body:**
```json
{
  "name": "String (optional)",
  "notes": "String (optional)",
  "dosage": "String (optional)",
  "frequency": "String (optional)",
  "startDate": "String (DD-MM-YYYY) (optional)",
  "endDate": "String (DD-MM-YYYY) (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Medicine updated successfully.<br>
**Body**: `Medicine`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "name": "String[]",
    "notes": "String[]",
    "dosage": "String[]",
    "frequency": "String[]",
    "startDate": "String[]",
    "endDate": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medicine.<br>

```json
{
  "details": "This Medicine does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medicine with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Medicine with ID {id}"
}
```
<br>

## Delete
**URL:** `/medicines/{id}/delete`<br>
**Method:** <code style="color: rgb(234, 154, 142)">DELETE</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Delete an existing medicine by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">204 No Content</code><br>
**Description**: Medicine deleted successfully.<br>
**Body**: None<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this medicine.<br>

```json
{
  "details": "This Medicine does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Medicine with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Medicine with ID {id}"
}
```
<br>


# Vaccinations
Endpoints for managing patient vaccinations.

## Add
**URL:** `/vaccinations/add`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Add a new vaccination to the patient's profile.<br>

### **Request Body:**
```json
{
  "doseNumber": "Integer",
  "startDate": "String (DD-MM-YYYY)",
  "notes": "String (optional)",
  "vaccineId": "Integer"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: Vaccination added successfully.<br>
**Body**: `Vaccination`
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "doseNumber": "String[]",
    "startDate": "String[]",
    "notes": "String[]",
    "vaccineId": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access vaccine with the specified `vaccineId`.<br>

```json
{
  "details": "This Vaccine does not belong to the authenticated patient."
}
```
<br>

## Get
**URL:** `/vaccinations/{id}`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve a vaccination by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Vaccination retrieved successfully.<br>
**Body**: `Vaccination`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this vaccination.<br>

```json
{
  "details": "This Vaccination does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Vaccination with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Vaccination with ID {id}"
}
```
<br>

## List
**URL:** `/vaccinations/list`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required<br>
**Paginated:** Yes (Default size: 20, Max size: 50)<br>
**Content-Type:** None<br>
**Description:** Retrieve a paginated list of vaccinations for the authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Vaccinations retrieved successfully.<br>
**Body**: `Page[Vaccination]`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/vaccinations/{id}/update`<br>
**Method:** <code style="color: rgb(103, 174, 246)">PUT</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update an existing vaccination by its `id`.<br>

### **Request Body:**
```json
{
  "doseNumber": "Integer (optional)",
  "startDate": "String (DD-MM-YYYY) (optional)",
  "notes": "String (optional)",
  "vaccineId": "Integer (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Vaccination updated successfully.<br>
**Body**: `Vaccination`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "doseNumber": "String[]",
    "startDate": "String[]",
    "notes": "String[]",
    "vaccineId": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this vaccination.<br>

```json
{
  "details": "This Vaccination does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access vaccine with the specified `vaccineId`.<br>

```json
{
  "details": "This Vaccine does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Vaccination with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Vaccination with ID {id}"
}
```
<br>

## Delete
**URL:** `/vaccinations/{id}/delete`<br>
**Method:** <code style="color: rgb(234, 154, 142)">DELETE</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Delete an existing vaccination by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">204 No Content</code><br>
**Description**: Vaccination deleted successfully.<br>
**Body**: None<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this vaccination.<br>

```json
{
  "details": "This Vaccination does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Vaccination with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Vaccination with ID {id}"
}
```
<br>


# Vaccines
Endpoints for managing patient vaccines.

## Add
**URL:** `/vaccines/add`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Add a new vaccine to the patient's profile.<br>

### **Request Body:**
```json
{
  "name": "String"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">201 Created</code><br>
**Description**: Vaccine added successfully.<br>
**Body**: `Vaccine`
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "name": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Get
**URL:** `/vaccines/{id}`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve a vaccine by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Vaccine retrieved successfully.<br>
**Body**: `Vaccine`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this vaccine.<br>

```json
{
  "details": "This Vaccine does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Vaccine with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Vaccine with ID {id}"
}
```
<br>

## List
**URL:** `/vaccines/list`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required<br>
**Paginated:** Yes (Default size: 20, Max size: 50)<br>
**Content-Type:** None<br>
**Description:** Retrieve a paginated list of vaccines for the authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Vaccines retrieved successfully.<br>
**Body**: `Page[Vaccine]`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/vaccines/{id}/update`<br>
**Method:** <code style="color: rgb(103, 174, 246)">PUT</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update an existing vaccine by its `id`.<br>

### **Request Body:**
```json
{
  "name": "String (optional)"
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Vaccine updated successfully.<br>
**Body**: `Vaccine`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "name": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this vaccine.<br>

```json
{
  "details": "This Vaccine does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Vaccine with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Vaccine with ID {id}"
}
```
<br>

## Delete
**URL:** `/vaccines/{id}/delete`<br>
**Method:** <code style="color: rgb(234, 154, 142)">DELETE</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Delete an existing vaccine by its `id`.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">204 No Content</code><br>
**Description**: Vaccine deleted successfully.<br>
**Body**: None<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or expired session.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">403 Forbidden</code><br>
**Description**: The authenticated user does not have permission to access this vaccine.<br>

```json
{
  "details": "This Vaccine does not belong to the authenticated patient."
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">404 Not Found</code><br>
**Description**: Vaccine with the specified `id` does not exist.<br>

```json
{
  "details": "Could not find Vaccine with ID {id}"
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">409 Conflict</code><br>
**Description**: The vaccine cannot be updated because it has related vaccination records.<br>

```json
{
  "details": "Entity of type Vaccine has related data of type Vaccination."
}
```
<br>


# Patients
Endpoints for managing patient profiles.

## Get Medical Card
**URL:** `/patients/card`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (JWT Token, see **[Generate Token](#generate-token)**)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve a medical card of currently authenticated patient.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Patient medical card retrieved successfully.<br>

```json
{
  "patient": "Patient",
  "card": {
    "allergies": "List[Allergy]",
    "chronicDiseases": "List[ChronicDisease]",
    "medicalCheckups": "List[MedicalCheckup]",
    "medicalDiagnoses": "List[MedicalDiagnosis]",
    "medicalProcedures": "List[MedicalProcedure]",
    "medicines": "List[Medicine]",
    "vaccinations": "List[Vaccination]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or missing JWT token.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Get Details
**URL:** `/patients/details`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Required (Session)**)<br>
**Content-Type:** `application/json`<br>
**Description:** Retrieve the authenticated patient's details.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Patient medical card retrieved successfully.<br>

```json
{
  "patient": "Patient",
  "card": {
    "allergies": "List[Allergy]",
    "chronicDiseases": "List[ChronicDisease]",
    "medicalCheckups": "List[MedicalCheckup]",
    "medicalDiagnoses": "List[MedicalDiagnosis]",
    "medicalProcedures": "List[MedicalProcedure]",
    "medicines": "List[Medicine]",
    "vaccinations": "List[Vaccination]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">401 Unauthorized</code><br>
**Description**: Invalid or missing JWT token.<br>

```json
{
  "details": "This endpoint requires authentication."
}
```
<br>

## Update
**URL:** `/patients/update`<br>
**Method:** <code style="color: rgb(250, 224, 124)">POST</code><br>
**Authentication:** Required (Session)<br>
**Content-Type:** `application/json`<br>
**Description:** Update the authenticated patient's profile.<br>

### **Request Body:**
```json
{
  "dateOfBirth": "String (DD-MM-YYYY) (optional)",
  "email": "String (optional)",
  "pesel": "String (optional)",
  "bloodType": "Enum (A+, A-, B+, B-, AB+, AB-, O+, O-) (optional)",
  "person": {
    "firstName": "String (optional)",
    "middleName": "String (optional)",
    "lastName": "String (optional)",
    "phoneNumber": "String (optional)",
    "gender": "Enum (MALE, FEMALE) (optional)",
    "address": {
      "country": "String (optional, default: POLAND)",
      "postalCode": "String (optional)",
      "city": "String (optional)",
      "street": "String (optional)",
      "buildingNumber": "String (optional)"
    }
  },
  "emergencyContact": {
    "firstName": "String (optional)",
    "middleName": "String (optional)",
    "lastName": "String (optional)",
    "phoneNumber": "String (optional)",
    "gender": "Enum (MALE, FEMALE) (optional)",
    "address": {
      "country": "String (optional, default: POLAND)",
      "postalCode": "String (optional)",
      "city": "String (optional)",
      "street": "String (optional)",
      "buildingNumber": "String (optional)"
    }
  }
}
```

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: Patient profile updated successfully.<br>
**Body**: `Patient`<br>
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">400 Bad Request</code><br>
**Description**: Invalid request body format or missing required fields.<br>

```json
{
  "details": {
    "dateOfBirth": "String[]",
    "email": "String[]",
    "pesel": "String[]",
    "bloodType": "String[]",
    "person.firstName": "String[]",
    "person.middleName": "String[]",
    "person.lastName": "String[]",
    "person.phoneNumber": "String[]",
    "person.gender": "String[]",
    "person.address.postalCode": "String[]",
    "person.address.city": "String[]",
    "person.address.street": "String[]",
    "person.address.buildingNumber": "String[]",
    "emergencyContact.firstName": "String[]",
    "emergencyContact.middleName": "String[]",
    "emergencyContact.lastName": "String[]",
    "emergencyContact.phoneNumber": "String[]",
    "emergencyContact.gender": "String[]",
    "emergencyContact.address.postalCode": "String[]",
    "emergencyContact.address.city": "String[]",
    "emergencyContact.address.street": "String[]",
    "emergencyContact.address.buildingNumber": "String[]"
  }
}
```
<br>

**Status**: <code style="color: rgb(222, 154, 142); background-color: rgb(89, 27, 8)">409 Conflict</code><br>
**Description**: Patient with the given PESEL already exists.<br>

```json
{
  "details": "Patient with the given PESEL already exists."
}
```
<br>


# Other Endpoints
Endpoints for miscellaneous operations.

## Ping
**URL:** `/ping`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Not Required<br>
**Paginated:** No<br>
**Rate Limited:** Yes (20 requests/min)<br>
**Content-Type:** None<br>
**Description:** Simple ping endpoint to check if the API is reachable.<br>
### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: API is reachable.<br>

```
Pong!
```

## Health Check
**URL:** `/health`<br>
**Method:** <code style="color: rgb(95, 221, 154)">GET</code><br>
**Authentication:** Not Required<br>
**Paginated:** No<br>
**Rate Limited:** Yes (10 requests/min)<br>
**Content-Type:** None<br>
**Description:** Check the health status of the API.<br>

### **Request Body:**
None

### **Response:**<br>
**Status**: <code style="color: rgb(107, 208, 98); background-color: rgb(1, 54, 20)">200 OK</code><br>
**Description**: API is healthy.<br>

```json
{
  "status": "OK",
  "timestamp": "String (ISO 8601 format)",
  "services": {
    "database": "Enum (UP, DOWN)"
  }
}
```