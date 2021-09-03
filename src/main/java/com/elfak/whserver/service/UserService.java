package com.elfak.whserver.service;

import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.UserRegisterRequestDTO;
import com.elfak.whserver.service.dto.UserRegisterResponseDTO;

public interface UserService {

    UserRegisterResponseDTO createUser(UserRegisterRequestDTO userRegisterRequestDTO);

    User findByEmail(String email);

    User findById(Long id);

    User update(User user);
}
