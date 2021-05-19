package com.cg.pizza.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cg.pizza.entity.PizzaOrder;
import com.cg.pizza.repository.PizzaOrderRepository;

@Service

public class PizzaOrderService implements IPizzaOrderService
{

	
	String small,medium ,large ;


	@Autowired
	private PizzaOrderRepository pizzaOrderRepository;

	//Get All
	public List<PizzaOrder> viewOrderList()
	{
		List<PizzaOrder> pizzaOrder = pizzaOrderRepository.findAll();
		return pizzaOrder;
	}

	//Get By Id
	public PizzaOrder viewPizzaOrder(int id)
	{
		Optional<PizzaOrder> pizzaOrderList = pizzaOrderRepository.findById(id);
		return (pizzaOrderList.isPresent()) ? pizzaOrderList.get() : null;
	}

	@Override
	public List<PizzaOrder> viewOrderByDate(LocalDate date)
	{
		
		List <PizzaOrder> pizzaOrderList = pizzaOrderRepository.orderDate(date);
		return pizzaOrderList;
		
		
	}
	
	@Override
	public PizzaOrder cancelPizzaOrder(int bookId) 
	{
		PizzaOrder pizzadelete = viewPizzaOrder(bookId);
		if (pizzadelete != null)
			pizzaOrderRepository.deleteById(bookId);
		return pizzadelete;
		
		
	}

	@Override
	public PizzaOrder bookPizzaOrder(PizzaOrder pizzaOrder)
	{
		PizzaOrder pizzaList = viewPizzaOrder(pizzaOrder.getBookingOrderId());
		if (pizzaList==null)
		{
			pizzaOrder = pizzaOrderRepository.save(pizzaOrder);
		}
		return pizzaOrder;
	}

	@Override
	public PizzaOrder updatePizzaOrder(PizzaOrder pizzaOrder)
	{
		PizzaOrder pizzafound = viewPizzaOrder(pizzaOrder.getBookingOrderId());
		if (pizzafound != null) {
			pizzaOrder = pizzaOrderRepository.save(pizzaOrder);
		}
		return pizzaOrder;
	}
	
/*
	@Override
	public List<PizzaOrder> calculateTotal(String size, int quantity) 
	{
		List<PizzaOrder> pizzaObj = null ;
		int totalCost = 0;
		if(size.equals("small") )
		{
			totalCost = 100 * quantity ;
			
		}
		else if(size.equals("medium"))
		{
			totalCost = 150* quantity;
		}
		else if(size.equals("large"))
		{
			totalCost = 200 * quantity ;
		}
		System.out.println(" Total cost is "+totalCost);
		return pizzaObj;
	}


*/

	
	
	
}
