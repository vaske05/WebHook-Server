package com.elfak.whserver.facade.model.response;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {
	@NotNull
	private String userId;
	@NotNull
	private String fullName;
	@NotNull
	private String email;
	@NotNull
	private String secretKey;
}
