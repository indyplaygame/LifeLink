package dev.indy.lifelink.util;

import dev.indy.lifelink.model.Person;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

public class Util {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final String AUTH_HEADER_PREFIX = "Bearer ";

    public static final String GENERIC_NAME_REGEXP = "(?i)^[a-z0-9 ',-]+$";
    public static final String ALPHABETIC_REGEXP = "(?i)^[A-Z]+$";
    public static final String NFC_UID_REGEXP = "(?i)^(?:[a-f0-9]{2}[:-]?){3,6}[a-f0-9]{2}$";

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

    public static String timeToCron(LocalTime time, Set<DayOfWeek> weekDays) {
        return String.format("0 %d %d * * %s", time.getMinute(), time.getHour(), Util.weekDaysToCron(weekDays));
    }

    public static String weekDaysToCron(Set<DayOfWeek> days) {
        return days.stream().map(d -> d.name().substring(0, 3)).collect(Collectors.joining(","));
    }

    public static String getFullName(Person person) {
        return "%s%s %s".formatted(
            person.getFirstName(),
            person.getMiddleName() != null ? " %s".formatted(person.getMiddleName()) : "",
            person.getLastName()
        );
    }
}
