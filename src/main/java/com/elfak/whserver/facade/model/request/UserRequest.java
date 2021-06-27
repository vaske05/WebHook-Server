package com.elfak.whserver.facade.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	@NotNull
	private String fullName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
}
