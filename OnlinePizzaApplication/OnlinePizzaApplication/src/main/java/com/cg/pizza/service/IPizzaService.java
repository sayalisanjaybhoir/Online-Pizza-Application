package com.cg.pizza.service;

import java.util.List;

import com.cg.pizza.entity.Pizza;
import com.cg.pizza.exception.InvalidMinCostException;
import com.cg.pizza.exception.PizzaIdNotFoundException;

public interface IPizzaService {
	Pizza addPizza(Pizza pizza);

	Pizza updatePizza(Pizza pizza);

	Pizza deletePizza(int pizzaId) throws PizzaIdNotFoundException;

	Pizza viewPizza(int pizzaId);

	List<Pizza> viewPizzaList();

	List<Pizza> viewPizzaList(double minCost, double maxCost) throws InvalidMinCostException;

	List<Pizza> viewPizzaList(String pizzaType);

}
