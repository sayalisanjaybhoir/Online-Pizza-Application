package com.cg.pizza.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.pizza.entity.PizzaOrder;

@Repository
public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Integer> {
	
	public List<PizzaOrder> orderDate(LocalDate orderDate);

}

