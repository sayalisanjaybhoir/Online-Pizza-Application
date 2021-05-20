package com.cg.pizza.exception;

public class InvalidDataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidDataException() {
		this.message = "";
	}

	public InvalidDataException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "User not found" + this.message;
	}
}