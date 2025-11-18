package dev.indy.lifelink.validation.constraints;

import dev.indy.lifelink.validation.validators.ValidPeselValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPeselValidator.class)
public @interface ValidPesel {
    String message() default "Invalid PESEL number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
