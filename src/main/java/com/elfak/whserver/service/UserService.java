package com.elfak.whserver.service;

import com.elfak.whserver.model.User;

public interface UserService {
    User saveUser(User user);

    User findUserByEmail(String email);
}
