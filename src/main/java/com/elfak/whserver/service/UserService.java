package com.elfak.whserver.service;

import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.JWTLoginSuccessResponseDTO;
import com.elfak.whserver.service.dto.UserLoginRequestDTO;
import com.elfak.whserver.service.dto.UserRegistrationRequestDTO;
import com.elfak.whserver.service.dto.UserRegistrationResponseDTO;

public interface UserService {

    UserRegistrationResponseDTO createUser(UserRegistrationRequestDTO userRegistrationRequestDTO);

    JWTLoginSuccessResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO);

    User findByEmail(String email);

    User findById(Long id);

    User update(User user);
}
