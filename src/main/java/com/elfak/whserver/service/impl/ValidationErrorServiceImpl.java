package com.elfak.whserver.service.impl;

import com.elfak.whserver.service.ValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationErrorServiceImpl implements ValidationErrorService {

    @Override
    public ResponseEntity<?> validateFields(BindingResult bindingResult) { // TODO: Try to return optional here

        if (bindingResult.hasErrors()) {

            Map<String, String> errorMap = new HashMap<>();

            bindingResult.getFieldErrors()
                    .forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
