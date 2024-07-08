package org.example.explore_local.vallidation.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.explore_local.service.UserService;
import org.example.explore_local.vallidation.anotations.UniqueEmail;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private String message;
    private final UserService userService;

    public UniqueEmailValidator (UserService userService) {

        this.userService = userService;
    }

    @Override
    public void initialize (UniqueEmail constraintAnnotation) {

        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid (String value, ConstraintValidatorContext context) {

        if (value == null) {

            return true;

        } else {

            final boolean isUnique = userService.isUniqueEmail(value);

            if (!isUnique) replaceDefaultConstraintViolation(context, this.message);

            return isUnique;
        }
    }

    private void replaceDefaultConstraintViolation (ConstraintValidatorContext context, String message) {

        context
                .unwrap(HibernateConstraintValidatorContext.class)
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
