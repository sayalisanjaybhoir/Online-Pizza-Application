package com.cg.pizza.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.pizza.entity.PizzaOrder;

public interface IPizzaOrderService {

	public PizzaOrder bookPizzaOrder(PizzaOrder pizzaOrder);
	public PizzaOrder updatePizzaOrder(PizzaOrder pizzaOrder);
	public PizzaOrder cancelPizzaOrder(int bookingOrderId);
	public PizzaOrder viewPizzaOrder(int bookingOrderId);
	public List<PizzaOrder> viewOrderList();
	public List<PizzaOrder> viewOrderByDate(LocalDate date);
	public List<PizzaOrder> calculateTotal(int id, String size, int quantity);

}
