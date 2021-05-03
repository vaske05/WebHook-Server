package com.elfak.whserver.service;

import com.elfak.whserver.model.User;
import com.elfak.whserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(s).orElseThrow(); // TODO: user not found exception
    }

    @Transactional
    public User loadUserById(Long id) {
        return userRepository.findById(id).orElseThrow(); // TODO: user not found exception
    }
}
