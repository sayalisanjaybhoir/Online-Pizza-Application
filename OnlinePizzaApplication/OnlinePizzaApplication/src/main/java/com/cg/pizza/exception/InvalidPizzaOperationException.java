package com.cg.pizza.exception;

import java.util.Date;

public class InvalidPizzaOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String coupan;

	public InvalidPizzaOperationException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidPizzaOperationException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "InvalidPizzaOperationException [timestamp=" + timestamp + ", coupan=" + coupan + "]";
	}

}
