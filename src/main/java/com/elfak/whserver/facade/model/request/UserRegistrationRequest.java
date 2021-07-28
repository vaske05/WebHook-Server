package com.elfak.whserver.facade.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
	@NotBlank(message = "Please enter your full name")
	private String fullName;
	@Email(message = "Email is not in valid format")
	@NotBlank(message = "Please enter your email")
	private String email;
	@NotBlank(message = "Password field is required")
	private String password;
	@NotBlank
	private String confirmPassword;
}
