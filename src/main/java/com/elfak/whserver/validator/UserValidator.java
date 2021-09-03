package com.elfak.whserver.validator;

import com.elfak.whserver.facade.model.request.UserRegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) { // Validating confirm password and password length

        UserRegisterRequest user = (UserRegisterRequest) object;
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", "Password must be at least 6 characters");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Password must match");
        }

    }
}
