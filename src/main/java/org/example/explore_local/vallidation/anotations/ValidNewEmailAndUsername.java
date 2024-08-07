package org.example.explore_local.vallidation.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.explore_local.vallidation.validators.ValidNewEmailAndUsernameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidNewEmailAndUsernameValidator.class)
public @interface ValidNewEmailAndUsername {

    String email();

    String username();

    String id();
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
