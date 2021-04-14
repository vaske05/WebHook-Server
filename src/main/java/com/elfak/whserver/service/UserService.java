package com.elfak.whserver.service;

import com.elfak.whserver.model.User;

public interface UserService {

    User save(User user);

    User findByEmail(String email);

    User findById(Long id);
}
