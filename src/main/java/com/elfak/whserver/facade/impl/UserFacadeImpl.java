package com.elfak.whserver.facade.impl;

import com.elfak.whserver.facade.model.request.UserRegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.mapper.UserFacadeMapper;
import com.elfak.whserver.service.UserService;
import com.elfak.whserver.service.ValidationErrorService;
import com.elfak.whserver.service.dto.UserRegisterRequestDTO;
import com.elfak.whserver.validator.UserValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

	private final UserService userService;
	private final UserFacadeMapper mapper;
	private final UserValidator userValidator;
	private final ValidationErrorService errorService;

	@Override
	public ResponseEntity<?> createUser(UserRegisterRequest userRegisterRequest, BindingResult bindingResult) {

		// Validate pass match
		userValidator.validate(userRegisterRequest, bindingResult);

		ResponseEntity<?> errorMap = errorService.validateFields(bindingResult);
		if (errorMap != null) {
			return errorMap;
		}

		UserRegisterRequestDTO userRegisterRequestDTO = mapper.userRegisterRequestToDto(userRegisterRequest);
		return new ResponseEntity<>(mapper
			.userRegisterDtoToResponse(userService.createUser(userRegisterRequestDTO)), HttpStatus.CREATED);
	}
}
