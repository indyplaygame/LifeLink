package dev.indy.lifelink.model.response;

import dev.indy.lifelink.model.MedicineSchedule;

import java.util.UUID;

public record ScheduledTaskResponse(
    UUID patientId,
    MedicineSchedule task
) {}
