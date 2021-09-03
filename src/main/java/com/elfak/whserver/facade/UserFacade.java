package com.elfak.whserver.facade;

import com.elfak.whserver.facade.model.request.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface UserFacade {

	ResponseEntity<?> createUser(UserRegisterRequest userRegisterRequest, BindingResult bindingResult);
}
