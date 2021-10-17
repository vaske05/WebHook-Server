package com.elfak.whserver.exceptions;

import com.elfak.whserver.exceptions.response.EmailUniqueExceptionResponse;
import com.elfak.whserver.exceptions.response.UserNotFoundExceptionResponse;
import com.elfak.whserver.exceptions.response.WebHookNotFoundExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameUniqueException(EmailUniqueException exception) {
        EmailUniqueExceptionResponse exceptionResponse
                = new EmailUniqueExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        UserNotFoundExceptionResponse exceptionResponse
                = new UserNotFoundExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleWebHookNotFoundException(WebHookNotFoundException exception) {
        WebHookNotFoundExceptionResponse exceptionResponse
                = new WebHookNotFoundExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
