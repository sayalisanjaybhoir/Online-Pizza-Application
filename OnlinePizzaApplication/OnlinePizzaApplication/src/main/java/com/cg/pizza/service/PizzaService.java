package com.cg.pizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.pizza.entity.Pizza;
import com.cg.pizza.repository.PizzaRepository;
@Service
public class PizzaService implements IPizzaService {
	@Autowired
  private PizzaRepository pizzaRepository;
	
	
	public List<Pizza> viewPizzaList() {
		// TODO Auto-generated method stub
		List<Pizza> pizzaSerList = pizzaRepository.findAll();
		return pizzaSerList;
	}
	
	
	public Pizza viewPizza(int pizzaId) {
	 
	 Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
		return (pizza.isPresent()) ? pizza.get() : null;
	 
	 
 }
	
	public Pizza deletePizza(int pizzaId)	{
	
		Pizza pizza = viewPizza(pizzaId);
		if (pizza != null)
			pizzaRepository.deleteById(pizzaId);
		return pizza;
		
}

	public Pizza addPizza(Pizza pizza) {
		
		Pizza pizzaexist = viewPizza(pizza.getPizzaId());
		if (pizzaexist == null) {
			pizza = pizzaRepository.save(pizza);
		}
		return pizza;
		
	}


	public Pizza updatePizza(Pizza pizza) {
		
		
		Pizza pizzaexist = viewPizza(pizza.getPizzaId());
		if (pizzaexist != null) {
			pizza = pizzaRepository.save(pizza);
		}
		return pizza;
	}
	
	
}
	
