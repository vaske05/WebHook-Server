package com.elfak.whserver.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.elfak.whserver.facade.model.request.UserRequest;

public interface UserFacade {

	ResponseEntity<?> createUser(UserRequest userRequest, BindingResult bindingResult);
}
