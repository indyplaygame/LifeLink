package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddVaccinationRequest(
    @NotNull(groups = ValidationGroups.OnCreate.class, message = "Dose number cannot be empty")
    @Min(value = 1, message = "Dose number must be a positive integer")
    Integer doseNumber,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Vaccination date cannot be empty")
    @Pattern(regexp = Util.DATE_REGEXP, message = "Vaccination date must be in the format DD/MM/YYYY")
    String vaccinationDate,

    @Length(max = 1000, message = "Notes cannot exceed 1000 characters")
    String notes,

    @NotNull(groups = ValidationGroups.OnCreate.class, message = "Vaccine ID cannot be empty")
    Long vaccineId
) {}
