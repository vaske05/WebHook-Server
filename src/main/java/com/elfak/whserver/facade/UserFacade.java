package com.elfak.whserver.facade;

import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.facade.model.response.UserResponse;

public interface UserFacade {
	UserResponse createUser(UserRequest userRequest);
}
