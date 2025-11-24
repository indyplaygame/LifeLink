package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record NfcTagRequest(
    @NotBlank(message = "NFC tag UID cannot be empty.")
    @Length(min = 11, max = 20, message = "NFC tag UID must be between 11 and 20 characters long.")
    @Pattern(regexp = Util.NFC_UID_REGEXP, message = "NFC tag UID must be a valid hexadecimal string (with optional colons or hyphens).")
    String nfcTagUid,

    @NotBlank(message = "NFC code cannot be empty.")
    @Length(min = 6, max = 6, message = "NFC code must be exactly 6 characters long.")
    @Pattern(regexp = "^[0-9]{6}$", message = "NFC code must consist of exactly 6 digits.")
    String nfcCode
) {}
