package com.elfak.whserver.service;

import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.UserRequestDTO;
import com.elfak.whserver.service.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    User findByEmail(String email);

    User findById(Long id);

    User update(User user);
}
