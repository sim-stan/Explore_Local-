package org.example.explore_local.vallidation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.explore_local.service.BusinessService;
import org.example.explore_local.vallidation.anotations.UniqueBusinessName;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class UniqueBusinessNameValidator implements ConstraintValidator<UniqueBusinessName, String> {

    private final BusinessService businessService;
    private String message;


    public UniqueBusinessNameValidator(BusinessService businessService) {
        this.businessService = businessService;
    }


    @Override
    public void initialize(UniqueBusinessName constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {

            return true;

        } else {

            final boolean isUnique = businessService.isUniqueName(value);

            if (!isUnique) replaceDefaultConstraintViolation(context, this.message);

            return isUnique;
        }
    }

    private void replaceDefaultConstraintViolation(ConstraintValidatorContext context, String message) {

        context
                .unwrap(HibernateConstraintValidatorContext.class)
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}

