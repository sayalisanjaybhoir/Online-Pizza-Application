package com.cg.pizza.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.pizza.exception.CoupanIdNotFoundException;

@ControllerAdvice
public class CoupanControllerExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(CoupanIdNotFoundException.class) // more exceptions
	public ResponseEntity<?> handleMissingStaffMember(CoupanIdNotFoundException me) {
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong message id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());

		return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
}
}
