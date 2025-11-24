package dev.indy.lifelink.model.request;

import dev.indy.lifelink.validation.constraints.ValidDate;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddMedicalCheckupRequest(
    @NotBlank(groups = OnCreate.class, message = "Checkup details cannot be empty")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 2000, message = "Checkup details cannot exceed 2000 characters")
    String details,

    @NotBlank(groups = OnCreate.class, message = "Checkup date cannot be empty")
    @ValidDate(groups = {OnCreate.class, OnUpdate.class}, message = "Checkup date must be in the format DD-MM-YYYY")
    String date
) {}
