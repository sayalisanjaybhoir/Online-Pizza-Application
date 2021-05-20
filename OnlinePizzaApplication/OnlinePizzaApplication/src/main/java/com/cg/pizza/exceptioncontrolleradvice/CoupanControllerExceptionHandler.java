package com.cg.pizza.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.pizza.exception.CoupanNameNotFoundException;
import com.cg.pizza.exception.CoupanIdNotFoundException;
import com.cg.pizza.exception.InvalidCoupanOperationException;

@ControllerAdvice
public class CoupanControllerExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(CoupanIdNotFoundException.class) // more exceptions
	public ResponseEntity<?> handleCoupanId(CoupanIdNotFoundException me) {
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Wrong coupan id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidCoupanOperationException.class)
	public ResponseEntity<?> handleInvalidCoupan(InvalidCoupanOperationException mid) {
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Enter proper coupan details");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", mid.getMessage());

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CoupanNameNotFoundException.class)
	public ResponseEntity<?> handleInvalidCoupanName(CoupanNameNotFoundException mi) {
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Coupan Name not found");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", mi.getMessage());

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
}
