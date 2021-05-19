package com.cg.pizza.exception;

import java.util.Date;

public class InvalidCoupanOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String coupan;
	public InvalidCoupanOperationException() {
		// TODO Auto-generated constructor stub
	}

public InvalidCoupanOperationException(String coupan) {
super(coupan);
}

@Override
public String toString() {
	return "InvalidCoupanOperationException [timestamp=" + timestamp + ", coupan=" + coupan + "]";
}
}
