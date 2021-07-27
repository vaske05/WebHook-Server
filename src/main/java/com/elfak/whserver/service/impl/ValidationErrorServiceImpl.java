package com.elfak.whserver.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.elfak.whserver.service.ValidationErrorService;

@Service
public class ValidationErrorServiceImpl implements ValidationErrorService {
	@Override
	public ResponseEntity<?> validateFields(BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			Map<String, String> errorMap = new HashMap<>();

			bindingResult.getFieldErrors()
				.forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
