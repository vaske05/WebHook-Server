package com.elfak.whserver.service;

import com.elfak.whserver.model.User;
import com.elfak.whserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
