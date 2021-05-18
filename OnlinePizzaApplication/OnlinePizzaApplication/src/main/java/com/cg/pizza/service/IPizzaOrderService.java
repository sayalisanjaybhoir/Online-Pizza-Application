package com.cg.pizza.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.pizza.entity.PizzaOrder;

public interface IPizzaOrderService 
{

	//1
	public PizzaOrder bookPizzaOrder(PizzaOrder pizzaOrder);
	//2
	public PizzaOrder updatePizzaOrder(PizzaOrder pizzaOrder);
	//3
	public PizzaOrder cancelPizzaOrder(int bookingOrderId);
	//4
	public PizzaOrder viewPizzaOrder(int bookingOrderId);
	//5 
	public List<PizzaOrder> viewOrderList();
	//6 view List By Date
	public List<PizzaOrder> viewOrderByDate(LocalDate date);
	//7 calculate
	public List<PizzaOrder> calculateTotal(String size , int quantity );
}
