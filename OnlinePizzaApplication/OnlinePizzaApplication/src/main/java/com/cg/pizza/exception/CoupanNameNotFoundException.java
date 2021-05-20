package com.cg.pizza.exception;

import java.util.Date;

public class CoupanNameNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String coupan;
public CoupanNameNotFoundException() {
	// TODO Auto-generated constructor stub
}
public CoupanNameNotFoundException(String coupan) {
	super(coupan);
}
@Override
public String toString() {
	return "CoupanNameNotFoundException [timestamp=" + timestamp + ", coupan=" + coupan + "]";
}
}