package com.cg.pizza.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.pizza.entity.Pizza;
import com.cg.pizza.repository.PizzaRepository;

@Service
public class PizzaService implements IPizzaService {
	@Autowired
	private PizzaRepository pizzaRepository;
	Logger logger = LoggerFactory.getLogger(PizzaService.class);

	public List<Pizza> viewPizzaList() {
		logger.info("Inside View  all Pizza method");

		List<Pizza> pizzaSerList = pizzaRepository.findAll();
		return pizzaSerList;
	}

	public Pizza viewPizza(int pizzaId) {
		logger.info("Inside view by Pizza id method");

		Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
		return (pizza.isPresent()) ? pizza.get() : null;

	}

	public Pizza deletePizza(int pizzaId) {
		logger.info("Inside Delete by Pizza Id method");

		Pizza pizza = viewPizza(pizzaId);
		if (pizza != null)
			pizzaRepository.deleteById(pizzaId);
		return pizza;

	}

	public Pizza addPizza(Pizza pizza) {
		logger.info("Inside add pizza method");

		Pizza pizzaExist = viewPizza(pizza.getPizzaId());
		if (pizzaExist == null) {
			pizza = pizzaRepository.save(pizza);
		}
		return pizza;

	}

	public Pizza updatePizza(Pizza pizza) {

		logger.info("Inside update method");

		Pizza pizzaExist = viewPizza(pizza.getPizzaId());
		if (pizzaExist != null) {
			pizza = pizzaRepository.save(pizza);
		}
		return pizza;
	}

	public List<Pizza> viewPizzaList(double minCost, double maxCost) {

		logger.info("Inside view by cost method");

		List<Pizza> objList = pizzaRepository.findBypizzaCostBetween(minCost, maxCost);
		return objList;

	}

	public List<Pizza> viewPizzaList(String pizzaType) {
		logger.info("Inside view pizza by type method");

		List<Pizza> pizzaByType = pizzaRepository.findBypizzaType(pizzaType);

		return pizzaByType;

	}
}
