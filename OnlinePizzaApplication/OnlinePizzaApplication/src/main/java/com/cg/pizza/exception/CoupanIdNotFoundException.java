package com.cg.pizza.exception;

import java.util.Date;

public class CoupanIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;
public CoupanIdNotFoundException() {
	// TODO Auto-generated constructor stub
}
public CoupanIdNotFoundException(String message) {
	super(message);
}
@Override
public String toString() {
	return "CoupanIdNotFoundException [timestamp=" + timestamp + ", message=" + message + "]";
}
}