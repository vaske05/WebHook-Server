package com.elfak.whserver.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebHookNotFoundExceptionResponse {

    private String message;

    public WebHookNotFoundExceptionResponse(String exceptionResponse) {
        this.message = exceptionResponse;
    }
}
