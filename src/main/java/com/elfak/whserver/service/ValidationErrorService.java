package com.elfak.whserver.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface ValidationErrorService {

	Optional<ResponseEntity<?>> validateFields(BindingResult bindingResult);
}
