package com.elfak.whserver.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.elfak.whserver.facade.model.request.UserRequest;


@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) { // Validating confirm password and password length

        UserRequest user = (UserRequest) object;
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", "Password must be at least 6 characters");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Password must match");
        }

    }
}
