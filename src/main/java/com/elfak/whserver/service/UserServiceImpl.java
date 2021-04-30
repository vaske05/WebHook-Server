package com.elfak.whserver.service;

import com.elfak.whserver.model.User;
import com.elfak.whserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User save(User user) {
        //try {
        user.setSecretKey(generateUserSecretKey());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
