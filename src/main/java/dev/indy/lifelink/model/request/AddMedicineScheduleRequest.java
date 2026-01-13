package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddMedicineScheduleRequest(
    @NotNull(groups = OnCreate.class)
    Set<DayOfWeek> weekDays,

    @NotNull(groups = OnCreate.class)
    LocalTime executionTime,

    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 1000, message = "Schedule notes cannot exceed 1000 characters")
    String notes,

    @NotBlank(groups = OnCreate.class, message = "Dosage cannot be empty")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 50, message = "Dosage cannot exceed 50 characters")
    @Pattern(
        groups = {OnCreate.class, OnUpdate.class},
        regexp = Util.GENERIC_NAME_REGEXP,
        message = "Dosage can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces"
    )
    String dosage,

    @NotNull(groups = OnCreate.class, message = "Medicine ID cannot be empty")
    Long medicineId
) {}
