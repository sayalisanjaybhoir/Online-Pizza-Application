package com.cg.pizza.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.pizza.exception.OrderIdNotFoundException;

@ControllerAdvice
public class PizzaOrderControllerExceptionHandler extends ResponseEntityExceptionHandler
{	 
		@ExceptionHandler(OrderIdNotFoundException.class) // more exceptions
		public ResponseEntity<?> orderIdNotFound(OrderIdNotFoundException id) {
			Map<String, Object> errorMessage = new LinkedHashMap<>();
			errorMessage.put("error", "Invalid pizza order id");
			errorMessage.put("timestamp", LocalDateTime.now());
			errorMessage.put("details", id.getMessage());

			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	}
	}

