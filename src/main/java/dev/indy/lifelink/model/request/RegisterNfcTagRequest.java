package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.ValidationGroups;
import dev.indy.lifelink.validation.constraints.ValidPesel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record RegisterNfcTagRequest(
    @NotBlank(message = "NFC tag UID cannot be empty.")
    @Length(min = 11, max = 20, message = "NFC tag UID must be between 11 and 20 characters long.")
    @Pattern(regexp = Util.NFC_UID_REGEXP, message = "NFC tag UID must be a valid hexadecimal string (with optional colons or hyphens).")
    String nfcTagUid,

    @NotBlank(message = "PESEL cannot be empty.")
    @ValidPesel(message = "PESEL must be a valid 11-digit number conforming to the PESEL format.")
    String pesel,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Password cannot be empty")
    @Length(groups = ValidationGroups.OnCreate.class, min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(
        groups = ValidationGroups.OnCreate.class,
        regexp = "(?i)^[a-z0-9!@#$%^&*\\-_]+$",
        message = "Password can only contain alphanumeric characters and special characters (!@#$%^&*-_)"
    )
    String password
) {}
