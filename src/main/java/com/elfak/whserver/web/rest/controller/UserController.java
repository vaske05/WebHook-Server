package com.elfak.whserver.web.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.facade.model.response.UserResponse;
import com.elfak.whserver.web.WebConstant;

@RequestMapping(value = WebConstant.USER_BASE_URL, produces = APPLICATION_JSON_VALUE)
public interface UserController {

	@PostMapping(value = "/users/register")
	ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest);
}
