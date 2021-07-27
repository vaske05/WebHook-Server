package com.elfak.whserver.facade;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.elfak.whserver.facade.model.request.UserRequest;

public interface UserFacade {

	ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult);
}
