package com.elfak.whserver.exceptions.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundExceptionResponse {

    private String message;

    public UserNotFoundExceptionResponse(String exceptionResponse) {
        this.message = exceptionResponse;
    }
}
