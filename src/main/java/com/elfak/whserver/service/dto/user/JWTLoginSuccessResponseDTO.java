package com.elfak.whserver.service.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JWTLoginSuccessResponseDTO {
    private boolean success;
    private String token;
    private String secretKey;

    public JWTLoginSuccessResponseDTO(boolean success, String token) {
        this.success = success;
        this.token = token;
    }
}
