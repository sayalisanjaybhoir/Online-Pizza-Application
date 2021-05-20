package com.cg.pizza.exceptioncontrolleradvice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.pizza.exception.InvalidDataException;
@ControllerAdvice
public class InvalidDataExceptionController {
	   @ExceptionHandler(value = InvalidDataException.class)
	   public ResponseEntity<Object> exception(InvalidDataException exception) {
	      return new ResponseEntity<>("Login Failed! - Enter Correct Details", HttpStatus.NOT_FOUND);
	   }
	   @ExceptionHandler(value = ConstraintViolationException.class)
	   public ResponseEntity<Object> exception(ConstraintViolationException exception) {
	      return new ResponseEntity<>("User name already exists!!", HttpStatus.NOT_FOUND);
	   }

}

