package org.example.explore_local.vallidation.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.explore_local.vallidation.validators.UniqueBusinessNameValidator;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { UniqueBusinessNameValidator.class })
public @interface UniqueBusinessName {

    String message() default "There is already registered Business with the same Business name";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
