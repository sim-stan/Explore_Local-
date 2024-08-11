package org.example.explore_local.vallidation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.explore_local.model.dtos.UserRegisterBindingModel;
import org.example.explore_local.vallidation.anotations.PasswordMatch;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegisterBindingModel> {

    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegisterBindingModel userRegisterBindingModel, ConstraintValidatorContext context) {

        final String password = userRegisterBindingModel.getPassword();
        final String confirmPassword = userRegisterBindingModel.getConfirmPassword();

        if (password == null && confirmPassword == null) {

            return true;
        }


        boolean passwordMatch =password !=null && password.equals(confirmPassword);

        if (!passwordMatch) {

            HibernateConstraintValidatorContext hibernateContext =
                    context.unwrap(HibernateConstraintValidatorContext.class);

            hibernateContext
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return passwordMatch;
    }

}
