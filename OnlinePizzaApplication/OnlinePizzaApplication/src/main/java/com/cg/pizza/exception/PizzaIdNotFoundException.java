package com.cg.pizza.exception;

import java.util.Date;



public class PizzaIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	public String pizzaId;

	public PizzaIdNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public PizzaIdNotFoundException(String pizzaId) {
		super(pizzaId);
	this.pizzaId=pizzaId;
	
	}

	@Override
	public String toString() {
		return "PizzaIdNotFoundException [timestamp=" + timestamp + ", message=" + pizzaId + "]";
	}
	
}
