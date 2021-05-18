package com.cg.pizza.exception;

import java.util.Date;

public class InvalidMinCostException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;

	
	public InvalidMinCostException() {
		// TODO Auto-generated constructor stub
	}
	
	public InvalidMinCostException(String message) {
		super(message);
	}
	@Override
		public String toString() {
			return "InvalidMinCostException [timestamp=" + timestamp + ", message=" + message + "]";
		}
	
}
