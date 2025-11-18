package dev.indy.lifelink.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
