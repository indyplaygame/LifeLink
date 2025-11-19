package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddAllergyRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Allergy name cannot be empty")
    @Pattern(regexp = Util.GENERIC_NAME_REGEXP, message = "Allergy name can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces")
    @Length(max = 100, message = "Allergy name cannot exceed 100 characters")
    String name,

    @Length(max = 1000, message = "Allergy description cannot exceed 1000 characters")
    String description
) {}
