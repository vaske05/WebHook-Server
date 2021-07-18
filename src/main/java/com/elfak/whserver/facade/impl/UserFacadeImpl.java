package com.elfak.whserver.facade.impl;

import org.springframework.stereotype.Service;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.mapper.UserFacadeMapper;
import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.facade.model.response.UserResponse;
import com.elfak.whserver.service.UserService;
import com.elfak.whserver.service.dto.UserRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

	private final UserService userService;
	private final UserFacadeMapper mapper;

	@Override
	public UserResponse createUser(UserRequest userRequest) {

		UserRequestDTO userRequestDTO = mapper.userRequestToDto(userRequest);

		return mapper.userDtoToResponse(userService.createUser(userRequestDTO));
	}
}
