package com.elfak.whserver.facade.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.mapper.UserFacadeMapper;
import com.elfak.whserver.facade.model.request.UserRegistrationRequest;
import com.elfak.whserver.service.UserService;
import com.elfak.whserver.service.ValidationErrorService;
import com.elfak.whserver.service.dto.UserRegistrationRequestDTO;
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
	public ResponseEntity<?> createUser(UserRegistrationRequest userRegistrationRequest, BindingResult bindingResult) {

		// Validate pass match
		userValidator.validate(userRegistrationRequest, bindingResult);

		ResponseEntity<?> errorMap = errorService.validateFields(bindingResult);
		if (errorMap != null) {
			return errorMap;
		}

		UserRegistrationRequestDTO userRegistrationRequestDTO = mapper
			.userRegistrationRequestToDto(userRegistrationRequest);
		return new ResponseEntity<>(mapper
			.userRegistrationDtoToResponse(userService.createUser(userRegistrationRequestDTO)), HttpStatus.CREATED);
	}
}
