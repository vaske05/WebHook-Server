package com.elfak.whserver.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.elfak.whserver.facade.model.request.UserLoginRequest;
import com.elfak.whserver.facade.model.request.UserRegistrationRequest;

public interface UserFacade {

	ResponseEntity<?> createUser(UserRegistrationRequest userRegistrationRequest, BindingResult bindingResult);

	ResponseEntity<?> loginUser(UserLoginRequest userLoginRequest, BindingResult bindingResult);
}
