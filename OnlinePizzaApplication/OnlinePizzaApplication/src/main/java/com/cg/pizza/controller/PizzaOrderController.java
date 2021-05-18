package com.cg.pizza.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.cg.pizza.entity.PizzaOrder;
import com.cg.pizza.exception.OrderIdNotFoundException;
import com.cg.pizza.service.PizzaOrderSevice;


@RestController
@RequestMapping("/pizza")
public class PizzaOrderController {

	@Autowired
	private PizzaOrderSevice pizzaorderservice;

	
	// 1.Get All pizzaList
	
	@GetMapping
	public ResponseEntity<List<PizzaOrder>> getPizzaOrder() {

		List<PizzaOrder> pizzaOrderList = pizzaorderservice.viewOrderList();
		// Creating an error response.
		ResponseEntity<List<PizzaOrder>> response = new ResponseEntity<>(pizzaOrderList, HttpStatus.NOT_FOUND);

		
		if (!pizzaOrderList.isEmpty()) {
			response = new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
		}

		return response;
	}
	
	
	/*
	@GetMapping(value = "/{pizzaId}")
	public ResponseEntity<Object> getPizzaById (@PathVariable("pizzaId") int pizzaId)
	{
		PizzaOrder pizzaOrderList = pizzaorderservice.viewOrderList(pizzaId);
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Message " + pizzaId + " Not found");
		if (pizzaOrderList != null)
		{
			response= new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
		}
		return response;
	
	}
	*/
	
	//2.Get Pizza By Booking id
	
	@GetMapping(value = "/{pizzaBookOrderId}")
	public ResponseEntity<Object> getPizzaByOrderId (@PathVariable("pizzaBookOrderId") int pizzaOrderId)
	{
		PizzaOrder pizzaOrderList = pizzaorderservice.viewPizzaOrder(pizzaOrderId);
		//ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
		//		.body("Pizza with " + pizzaOrderId + " Not found");
		if (pizzaOrderList == null)
		{
			//response= new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
			throw new OrderIdNotFoundException(pizzaOrderId+" not found");
		}
		return new ResponseEntity<>(pizzaOrderList,HttpStatus.OK);
	
	}
	
	// get Pizza By Date
	@GetMapping(value = "/date/{pizzaOrderDate}")
	public ResponseEntity<Object> getPizzaByDate (@PathVariable("pizzaOrderDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate orderDate)
	{
		List<PizzaOrder> pizzaOrderList = pizzaorderservice.viewOrderByDate(orderDate);
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Pizaa with " + orderDate + " Not found");
		if (pizzaOrderList != null)
		{
			response= new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
		}
		return response;
	
	}
	
	
	//3 Delete Pizza By Booking ID
	
	@DeleteMapping(value= "/{bookId}")
	public ResponseEntity<Object> deletePizzaOrder(@PathVariable("bookId") int bookingId)
	{
		PizzaOrder pizzaOrderList = pizzaorderservice.cancelPizzaOrder(bookingId);
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Pizza " + bookingId + " Found");
		if (pizzaOrderList == null)
		{
			//response= new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
			throw new OrderIdNotFoundException(bookingId +" not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Pizza with pizza id  " + bookingId + " deleted");
	
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<Object> updatePizzaOrder(@PathVariable("bookId") int bookingId,@RequestBody PizzaOrder pizzaOrder) 
	{
		
		pizzaOrder.setBookingOrderId(bookingId);
		// If message is updated it returns updates message object else null
		PizzaOrder updatePizzaOrder = pizzaorderservice.updatePizzaOrder(pizzaOrder);
		// response is set to error if message is null.
		if (updatePizzaOrder == null) 
		{
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pizza with  " + bookingId + " Not found");
			throw new OrderIdNotFoundException(bookingId +" not found");		
			}
		else {
			// response is set to updated message id in response header section.
//			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//					.buildAndExpand(updatePizzaOrder.getBookingOrderId()).toUri();
//			return ResponseEntity.created(location).build();
			return ResponseEntity.status(HttpStatus.OK).body("Pizza with pizza id  " + bookingId + " added");
			
		}
	}
	
	
	
	// 4 Add the pizza order
	
	@PostMapping
	public ResponseEntity<Object> addPizzaOrder(@RequestBody PizzaOrder pizzaOrder) 
	{
		//System.out.println("Enterd in post method");
		PizzaOrder pizzaOrderList = pizzaorderservice.bookPizzaOrder(pizzaOrder);
		//System.out.println(pizzaOrderList);
		if (pizzaOrderList == null)

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		// response is set to inserted message id in response header section.
		//System.out.println(pizzaOrderList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand((pizzaOrderList).getBookingOrderId()).toUri();
		return ResponseEntity.created(location).build();
	}

	

}
