package com.cg.pizza.exception;

import java.util.Date;

public class InvalidCoupanOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;
	public InvalidCoupanOperationException() {
		// TODO Auto-generated constructor stub
	}

public InvalidCoupanOperationException(String message) {
super(message);
}

@Override
public String toString() {
	return "InvalidCoupanOperationException [timestamp=" + timestamp + ", message=" + message + "]";
}
}