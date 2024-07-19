package org.example.explore_local.vallidation.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.explore_local.repository.UserRepository;
import org.example.explore_local.service.UserHelperService;
import org.example.explore_local.vallidation.anotations.ValidNewEmailAndUsername;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class ValidNewEmailAndUsernameValidator implements ConstraintValidator<ValidNewEmailAndUsername, Object> {

    private String email;
    private String username;
    private String message;
    private String id;
    private final UserRepository userRepository;
    private final UserHelperService userHelperService;

    public ValidNewEmailAndUsernameValidator(UserRepository userRepository, UserHelperService userHelperService) {
        this.userRepository = userRepository;
        this.userHelperService = userHelperService;
    }

    @Override
    public void initialize(ValidNewEmailAndUsername constraintAnnotation) {
        this.email = constraintAnnotation.email();
        this.username = constraintAnnotation.username();
        this.message = constraintAnnotation.message();
        this.id = constraintAnnotation.id();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object newEmail = beanWrapper.getPropertyValue(this.email);
        Object newUsername = beanWrapper.getPropertyValue(this.username);
        long id=userHelperService.getUser().getId();


        String currentEntityUsername = userRepository.findById(id).get().getUsername();

        String currentEmail = userRepository.findById(id).get().getEmail();

        assert newEmail != null;
        if (currentEmail.equals(newEmail.toString())) {
            assert newUsername != null;
            return isUsernameAvailable(context, newUsername, currentEntityUsername);
        } else {
            if (userRepository.findByEmail(newEmail.toString()).isEmpty()) {
                assert newUsername != null;
                return isUsernameAvailable(context, newUsername, currentEntityUsername);
            }
            this.message = "Email already exist!";
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(this.email)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

            return false;
        }
    }

    private boolean isUsernameAvailable(ConstraintValidatorContext context, Object newUsername, String currentEntityUsername) {
        if (currentEntityUsername.equals(newUsername.toString())) {
            return true;
        } else {
            if (userRepository.findByUsername(newUsername.toString()).isEmpty()) return true;

            this.message = "Username already exist!";
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(this.username)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

            return false;
        }
    }
}
