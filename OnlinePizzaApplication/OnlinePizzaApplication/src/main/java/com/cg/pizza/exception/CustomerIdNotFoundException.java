package com.cg.pizza.exception;

import java.sql.Date;

public class CustomerIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String customer;

	public CustomerIdNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CustomerIdNotFoundException(String customer) {
		super(customer);
	}

	@Override
	public String toString() {
		return "CustomerIdNotFoundException [timestamp=" + timestamp + ", customer=" + customer + "]";
	}

}
