package com.elfak.whserver.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class JWTLoginSuccessResponseDTO {
    private boolean success;
    private String token;
}
