package com.elfak.whserver.service;

import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.UserRegistrationRequestDTO;
import com.elfak.whserver.service.dto.UserRegistrationResponseDTO;

public interface UserService {

    UserRegistrationResponseDTO createUser(UserRegistrationRequestDTO userRegistrationRequestDTO);

    User findByEmail(String email);

    User findById(Long id);

    User update(User user);
}
