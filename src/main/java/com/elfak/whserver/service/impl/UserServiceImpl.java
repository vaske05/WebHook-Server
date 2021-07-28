package com.elfak.whserver.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elfak.whserver.exceptions.EmailUniqueException;
import com.elfak.whserver.model.User;
import com.elfak.whserver.repository.UserRepository;
import com.elfak.whserver.service.UserService;
import com.elfak.whserver.service.dto.UserRegistrationRequestDTO;
import com.elfak.whserver.service.dto.UserRegistrationResponseDTO;
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
	public UserRegistrationResponseDTO createUser(UserRegistrationRequestDTO userRegistrationRequestDTO) {
		try {

			userRegistrationRequestDTO.setSecretKey(generateUserSecretKey());

			userRegistrationRequestDTO
				.setPassword(bCryptPasswordEncoder.encode(userRegistrationRequestDTO.getPassword()));

			User user = mapper.userRegistrationRequestDtoToUser(userRegistrationRequestDTO);

			user = userRepository.save(user);

			log.info("[ USER REGISTRATION SUCCESS ] : " + user.getEmail());

			return mapper.userToUserRegistrationResponseDto(user);
		} catch (Exception e) {
			log.error("[ ERROR DURING USER SAVE ] : " + e.getMessage());
			throw new EmailUniqueException("Email: " + userRegistrationRequestDTO.getEmail() + " already exists!");
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
