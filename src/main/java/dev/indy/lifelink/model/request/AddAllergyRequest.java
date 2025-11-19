package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddAllergyRequest(
    @NotBlank(groups = OnCreate.class, message = "Allergy name cannot be empty")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = Util.GENERIC_NAME_REGEXP, message = "Allergy name can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 100, message = "Allergy name cannot exceed 100 characters")
    String name,

    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 1000, message = "Allergy description cannot exceed 1000 characters")
    String description
) {}
