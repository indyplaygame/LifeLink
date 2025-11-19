package dev.indy.lifelink.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record CreateAddressRequest (
    @JsonProperty(defaultValue = "POLAND")
    String country,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Postal code cannot be empty")
    @Pattern(regexp = "(?i)^([0-9A-Z]+-?[0-9A-Z]){5,9}$", message = "Postal code format is invalid")
    @Length(max = 9, message = "Postal code cannot exceed 9 characters")
    String postalCode,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "City cannot be empty")
    @Length(max = 100, message = "City cannot exceed 100 characters")
    String city,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Street cannot be empty")
    @Length(max = 100, message = "Street cannot exceed 100 characters")
    String street,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Building number cannot be empty")
    @Pattern(regexp = "(?i)^[0-9]+[A-Z]?(/[0-9]+[A-Z]?)?]$", message = "Building number format is invalid")
    @Length(max = 10, message = "Building number cannot exceed 10 characters")
    String buildingNumber
) {
    public CreateAddressRequest {
        if(country == null || country.isEmpty()) country = "POLAND";
    }
}
