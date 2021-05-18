package com.cg.pizza.exception;

import java.util.Date;

public class InvalidSizeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;

	public InvalidSizeException() 
	{

	}

	public InvalidSizeException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "SizeInvalidException [timestamp=" + timestamp + ", message=" + message + "]";
	}
}

