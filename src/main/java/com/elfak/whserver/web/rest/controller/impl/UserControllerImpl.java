package com.elfak.whserver.web.rest.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.model.request.UserLoginRequest;
import com.elfak.whserver.facade.model.request.UserRegistrationRequest;
import com.elfak.whserver.web.rest.controller.UserController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserFacade userFacade;

	@Override
	public ResponseEntity<?> createUser(UserRegistrationRequest userRegistrationRequest, BindingResult bindingResult) {
		return userFacade.createUser(userRegistrationRequest, bindingResult);
	}

	@Override
	public ResponseEntity<?> loginUser(UserLoginRequest userLoginRequest, BindingResult bindingResult) {
		return userFacade.loginUser(userLoginRequest, bindingResult);
	}
}
