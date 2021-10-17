package com.elfak.whserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WebHookNotFoundException extends RuntimeException {

    public WebHookNotFoundException(String message) {
        super(message);
    }
}
