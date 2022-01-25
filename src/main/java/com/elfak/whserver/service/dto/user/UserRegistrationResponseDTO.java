package com.elfak.whserver.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponseDTO {
	@NotNull
	private String userId;
	@NotNull
	private String fullName;
	@NotNull
	private String email;
	@NotNull
	private String secretKey;
}
