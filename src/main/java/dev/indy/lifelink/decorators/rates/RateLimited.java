package dev.indy.lifelink.decorators.rates;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimited {
    int value() default 10;
    int seconds() default 60;
}
