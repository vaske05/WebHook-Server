package com.elfak.whserver.web.rest.controller;

import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.facade.model.response.UserResponse;
import com.elfak.whserver.web.WebConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = WebConstant.USER_BASE_URL, produces = APPLICATION_JSON_VALUE)
public interface UserController {

    @PostMapping(value = "/register")
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest);
}
