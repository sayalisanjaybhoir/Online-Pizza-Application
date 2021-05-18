package com.cg.pizza.repository;



import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.pizza.entity.PizzaOrder;


@Repository
public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Integer>
{
	//@Query("select p from PizzaOrder as p where p.orderDate =:date ")
	public List<PizzaOrder> orderDate(LocalDate orderDate);
	
	

}
