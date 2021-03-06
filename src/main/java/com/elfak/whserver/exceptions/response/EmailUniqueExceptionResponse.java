package com.elfak.whserver.exceptions.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailUniqueExceptionResponse {

    private String email;

    public EmailUniqueExceptionResponse(String exceptionResponse) {
        this.email = exceptionResponse;
    }
}
