package com.elfak.whserver.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import com.elfak.whserver.service.dto.UserRegisterResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elfak.whserver.exceptions.EmailUniqueException;
import com.elfak.whserver.model.User;
import com.elfak.whserver.repository.UserRepository;
import com.elfak.whserver.service.UserService;
import com.elfak.whserver.service.dto.UserRegisterRequestDTO;
import com.elfak.whserver.service.mapper.UserServiceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserServiceMapper mapper;

	@Override
	@Transactional
	public UserRegisterResponseDTO createUser(UserRegisterRequestDTO userRegisterRequestDTO) {
		try {
			userRegisterRequestDTO.setSecretKey(generateUserSecretKey());
			userRegisterRequestDTO.setPassword(bCryptPasswordEncoder.encode(userRegisterRequestDTO.getPassword()));
			User user = mapper.userRegisterRequestDtoToUser(userRegisterRequestDTO);
			user = userRepository.save(user);
			return mapper.userToUserRegisterResponseDto(user);
		} catch (Exception e) {
			log.error("Error during USER SAVE " + e.getMessage());
			throw new EmailUniqueException("Email: " + userRegisterRequestDTO.getEmail() + " already exists!");
		}

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
