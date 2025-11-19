package dev.indy.lifelink.validation.validators;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.constraints.ValidDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {
    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if(date == null) return true;

        return Util.parseDate(date) != null;
    }
}
