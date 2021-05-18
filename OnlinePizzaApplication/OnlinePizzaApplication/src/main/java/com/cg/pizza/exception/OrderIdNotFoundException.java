package com.cg.pizza.exception;

import java.util.Date;

public class OrderIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;

	public OrderIdNotFoundException() {

	}

	public OrderIdNotFoundException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "OrderIdNotFoundException [timestamp=" + timestamp + ", message=" + message + "]";
	}
}
