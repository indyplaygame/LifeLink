package dev.indy.lifelink.model.request;

import dev.indy.lifelink.validation.constraints.ValidDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddVaccinationRequest(
    @NotNull(groups = OnCreate.class, message = "Dose number cannot be empty")
    @Min(groups = {OnCreate.class, OnUpdate.class}, value = 1, message = "Dose number must be a positive integer")
    Integer doseNumber,

    @NotBlank(groups = OnCreate.class, message = "Vaccination date cannot be empty")
    @ValidDate(groups = {OnCreate.class, OnUpdate.class}, message = "Diagnosis date must be in the format DD-MM-YYYY")
    String vaccinationDate,

    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 1000, message = "Notes cannot exceed 1000 characters")
    String notes,

    @NotNull(groups = OnCreate.class, message = "Vaccine ID cannot be empty")
    Long vaccineId
) {}
