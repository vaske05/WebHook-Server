package com.elfak.whserver.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
	// @NotBlank(message = "Email cannot be blank")
	private String email;
	// @NotBlank(message = "Password cannot be blank")
	private String password;
}
