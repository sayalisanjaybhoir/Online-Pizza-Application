package com.cg.pizza.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.pizza.exception.InvalidMinCostException;
import com.cg.pizza.exception.InvalidPizzaOperationException;
import com.cg.pizza.exception.InvalidPizzaTypeException;
import com.cg.pizza.exception.OrderIdNotFoundException;
import com.cg.pizza.exception.PizzaIdNotFoundException;

@ControllerAdvice
public class PizzaControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PizzaIdNotFoundException.class) // more exceptions
	public ResponseEntity<?> handleMissingStaffMember(PizzaIdNotFoundException me) {
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Pizza id entered is wrong");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage()); // check this line

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidMinCostException.class) // more exceptions
	public ResponseEntity<?> handleMissingStaffMember(InvalidMinCostException me) {
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Pizza not found in this range");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

		// More methods for all controllers
	}

	
