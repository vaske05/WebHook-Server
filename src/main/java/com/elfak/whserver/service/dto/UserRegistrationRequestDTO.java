package com.elfak.whserver.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDTO {
	@NotBlank
	private String fullName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
	@JsonIgnore
	private String secretKey;
}
