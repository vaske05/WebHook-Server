package com.elfak.whserver.service.impl;

import com.elfak.whserver.service.ValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ValidationErrorServiceImpl implements ValidationErrorService {

    @Override
    public Optional<ResponseEntity<?>> validateFields(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            Map<String, String> errorMap = new HashMap<>();

            bindingResult.getFieldErrors()
                    .forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

            return Optional.of(new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST));
        }
        return Optional.empty();
    }
}
