package com.elfak.whserver.web.rest.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.model.request.UserRegisterRequest;
import com.elfak.whserver.web.rest.controller.UserController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserFacade userFacade;

	@Override
	public ResponseEntity<?> createUser(UserRegisterRequest userRegisterRequest, BindingResult bindingResult) {

		return userFacade.createUser(userRegisterRequest, bindingResult);
	}
}
