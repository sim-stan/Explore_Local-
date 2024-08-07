package org.example.explore_local.vallidation.anotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.explore_local.vallidation.validators.FileValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface FileAnnotation {

    long size() default 5 * 1024 * 1024;

    String[] contentTypes();

    String message() default "{user.password-match}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
