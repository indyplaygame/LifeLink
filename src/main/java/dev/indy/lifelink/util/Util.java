package dev.indy.lifelink.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final String AUTH_HEADER_PREFIX = "Bearer ";

    public static final String GENERIC_NAME_REGEXP = "(?i)^[a-z0-9 ',-]+$";
    public static final String ALPHABETIC_REGEXP = "(?i)^[A-Z]+$";
    public static final String NFC_UID_REGEXP = "(?i)^(?:[a-f0-9]{2}[:-]?){4,6}[a-f0-9]{2}$";

    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    public static String capitalize(String str) {
        if(str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String retrieveToken(String authHeader) {
        return authHeader.substring(AUTH_HEADER_PREFIX.length()).trim();
    }
}
