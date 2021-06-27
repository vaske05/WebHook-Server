package com.elfak.whserver.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	@NotNull
	private String fullName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@JsonIgnore
	private String secretKey;
}
