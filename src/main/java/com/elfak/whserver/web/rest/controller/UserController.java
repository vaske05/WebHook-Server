package com.elfak.whserver.web.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elfak.whserver.facade.model.request.UserLoginRequest;
import com.elfak.whserver.facade.model.request.UserRegistrationRequest;
import com.elfak.whserver.web.WebConstant;

@RequestMapping(value = WebConstant.USER_BASE_URL, produces = APPLICATION_JSON_VALUE)
public interface UserController {

    @PostMapping(value = "/register")
    ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest,
        BindingResult bindingResult);

    @PostMapping(value = "/login")
    ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest, BindingResult bindingResult);
}
