package dev.indy.lifelink.validation.validators;

import dev.indy.lifelink.validation.constraints.ValidPesel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPeselValidator implements ConstraintValidator<ValidPesel, String> {
    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {
        if(pesel == null || pesel.length() != 11 || !pesel.matches("\\d{11}")) return false;

        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        char[] digits = pesel.substring(0, 11).toCharArray();

        int sum = 0;
        for(int i = 0; i < weights.length; i++)
            sum += ((digits[i] - '0') * weights[i]) % 10;

        int checksum = (10 - (sum % 10)) % 10;
        return checksum == digits[10] - '0';
    }
}
