package com.cg.pizza.service;
import com.cg.pizza.entity.Pizza;
import com.cg.pizza.exception.PizzaIdNotFoundException;

import java.util.List;

public interface IPizzaService {
	Pizza addPizza(Pizza pizza);

	Pizza updatePizza(Pizza pizza);

	Pizza deletePizza(int pizzaId) throws PizzaIdNotFoundException;

	Pizza viewPizza(int pizzaId);

	List<Pizza> viewPizzaList();

	//List<Pizza> viewPizzaList(double minCost, double maxCost)throws InvalidMinCostException;

}
