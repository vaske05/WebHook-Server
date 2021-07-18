package com.elfak.whserver.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailUniqueExceptionResponse {

    private String emailAlreadyExist;

    public EmailUniqueExceptionResponse(String exceptionResponse) {
        this.emailAlreadyExist = exceptionResponse;
    }
}
