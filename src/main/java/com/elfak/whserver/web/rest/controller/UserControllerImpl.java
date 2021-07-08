package com.elfak.whserver.web.rest.controller;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.facade.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserFacade userFacade;

	@Override
	public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
		userFacade.createUser(userRequest);

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
