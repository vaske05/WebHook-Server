package com.elfak.whserver.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ValidationErrorService {

	ResponseEntity<?> validateFields(BindingResult bindingResult);
}
