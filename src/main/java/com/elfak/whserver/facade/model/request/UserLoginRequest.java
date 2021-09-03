package com.elfak.whserver.facade.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
	@NotBlank(message = "Email cannot be blank")
	private String email;
	@NotBlank(message = "Password cannot be blank")
	private String password;
}
