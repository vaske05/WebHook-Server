package com.elfak.whserver.facade.impl;

import org.springframework.stereotype.Service;

import com.elfak.whserver.facade.UserFacade;
import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.facade.model.response.UserResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

	//private final UserService userService;

	@Override
	public UserResponse createUser(UserRequest userRequest) {
		// userService.createUser() TODO: use mapper here
		return null;
	}
}
