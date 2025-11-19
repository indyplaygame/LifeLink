package dev.indy.lifelink.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final String DATE_REGEXP = "^\\d{2}-\\d{2}-\\d{4}$";
    public static final String GENERIC_NAME_REGEXP = "^[a-zA-Z0-9 ',-]+$";
    public static final String ALPHABETIC_REGEXP = "^[a-zA-Z]+$";

    public static LocalDate parseDate(String date) {
        return date != null && date.matches(DATE_REGEXP) ? LocalDate.parse(date, DATE_FORMATTER) : null;
    }
}
