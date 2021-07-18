package com.elfak.whserver.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elfak.whserver.model.User;
import com.elfak.whserver.repository.UserRepository;
import com.elfak.whserver.service.UserService;
import com.elfak.whserver.service.dto.UserRequestDTO;
import com.elfak.whserver.service.dto.UserResponseDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        //try {
        userRequestDTO.setSecretKey(generateUserSecretKey());
        userRequestDTO.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        // return userRepository.save(user); TODO: Use mapper here
        return null;
//        } catch (Exception e) {
//            throw new Exception("message");
//        }

    }

    @Override
    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    private String generateUserSecretKey() {
        return Base64.getEncoder()
                .encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}