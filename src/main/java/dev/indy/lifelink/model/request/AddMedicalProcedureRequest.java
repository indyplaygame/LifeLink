package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddMedicalProcedureRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "CPT code cannot be empty")
    @Pattern(regexp = "(?i)^[0-9]{4}[0-9a-z]$", message = "CPT code must be a 5-character alphanumeric code")
    @Length(min = 5, max = 5, message = "CPT code must be exactly 5 characters long")
    String cptCode,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Procedure description cannot be empty")
    @Length(max = 1000, message = "Procedure description cannot exceed 1000 characters")
    String procedureDescription,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Procedure date cannot be empty")
    @Pattern(regexp = Util.DATE_REGEXP, message = "Procedure date must be in the format DD/MM/YYYY")
    String procedureDate
) {}
