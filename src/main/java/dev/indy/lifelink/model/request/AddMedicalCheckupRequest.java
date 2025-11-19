package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddMedicalCheckupRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Checkup details cannot be empty")
    @Length(max = 2000, message = "Checkup details cannot exceed 2000 characters")
    String details,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Checkup date cannot be empty")
    @Pattern(regexp = Util.DATE_REGEXP, message = "Checkup date must be in the format DD-MM-YYYY")
    String date
) {}
