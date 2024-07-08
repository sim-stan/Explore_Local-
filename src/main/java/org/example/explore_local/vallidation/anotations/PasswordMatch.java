package org.example.explore_local.vallidation.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.explore_local.vallidation.validators.PasswordMatchValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { PasswordMatchValidator.class })
public @interface PasswordMatch {

    String message() default "{user.password-match}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
