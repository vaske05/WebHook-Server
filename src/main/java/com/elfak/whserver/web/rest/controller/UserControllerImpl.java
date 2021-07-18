package com.elfak.whserver.web.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.facade.model.response.UserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserFacade userFacade;

	@Override
	public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {

//		// Validate pass match
//		userValidator.validate(user, bindingResult);
//
//		ResponseEntity<?> errorMap = errorService.validateFields(bindingResult);
//		if(errorMap != null) {
//			return errorMap;
//		}

		UserResponse userResponse = userFacade.createUser(userRequest);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}
}
