package dev.indy.lifelink.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class WeekDaysConverter implements AttributeConverter<Set<DayOfWeek>, String> {
    private static final String SEPARATOR = ";";

    @Override
    public String convertToDatabaseColumn(Set<DayOfWeek> dayOfWeeks) {
        return String.join(SEPARATOR, dayOfWeeks.stream()
            .map(Enum::name)
            .map(String::toUpperCase)
            .toArray(String[]::new)
        );
    }

    @Override
    public Set<DayOfWeek> convertToEntityAttribute(String str) {
        return str.isEmpty() ? new HashSet<>() : Stream.of(str.split(SEPARATOR))
            .map(String::toUpperCase)
            .map(DayOfWeek::valueOf)
            .collect(Collectors.toSet());
    }
}
