package com.cg.pizza.repository;

import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.cg.pizza.entity.Pizza;

@Repository

public interface PizzaRepository extends JpaRepository<Pizza, Integer>  {
   
	//@Query(value="select e from Pizza as e where e.pizzaCost BETWEEN minimum AND maximum")
	//findByRollNumberBetween

	public List<Pizza> findBypizzaCostBetween(double minCost,double maxCost);  
	
	public List<Pizza> findBypizzaType(String pizzaType);
	
	
	
}


