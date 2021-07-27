package com.elfak.whserver.web.rest.controller.impl;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.web.rest.controller.UserController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserFacade userFacade;

	@Override
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {

		return userFacade.createUser(userRequest, bindingResult);
	}
}
