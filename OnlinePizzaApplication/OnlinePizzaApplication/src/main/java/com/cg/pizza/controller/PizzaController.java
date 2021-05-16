package com.cg.pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cg.pizza.service.PizzaService;

import com.cg.pizza.entity.Pizza;
import com.cg.pizza.exception.PizzaIdNotFoundException;

import java.net.URI;
import java.util.List;
@RestController


@RequestMapping
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	@GetMapping("/pizza")
	public ResponseEntity<List<Pizza>> getPizzaId() {

		List<Pizza> pizzaList = pizzaService.viewPizzaList();
		// Creating an error response.
		ResponseEntity<List<Pizza>> response = new ResponseEntity<>(pizzaList, HttpStatus.NOT_FOUND);

		// If messageList is not empty it sets the list in response else by default
		// error will be
		// there in response.
		if (!pizzaList.isEmpty()) {
			response = new ResponseEntity<>(pizzaList, HttpStatus.OK);
		}

		return response;
	}
	
	@GetMapping(value = "/{pizzaId}")
	public ResponseEntity<Object> viewPizza(@PathVariable("pizzaId") int pizzaId) {

		Pizza pizza = pizzaService.viewPizza(pizzaId);
		
		if (pizza == null) {
					
	 throw new PizzaIdNotFoundException(pizzaId + " This id is not available");

		}
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{pizzaId}")
	public ResponseEntity<Object> deletePizza(@PathVariable("pizzaId") int pizzaId) {
		
		Pizza pizzaPresent = pizzaService.deletePizza(pizzaId);
		
		if (pizzaPresent == null) {
			
			throw new PizzaIdNotFoundException(pizzaId + " This pizza id is not available to delete ");
		}
		else
		
		return ResponseEntity.status(HttpStatus.OK).body("pizza " + pizzaId + " deleted");
	
}
	
	@PostMapping
	public ResponseEntity<Object> addPizza(@RequestBody Pizza pizza) {
		// If message is inserted it returns inserted message object else null
		Pizza newPizza = pizzaService.addPizza(pizza);
		// response is set to error if message is null.
		if (newPizza== null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		// response is set to inserted message id in response header section.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newPizza.getPizzaId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	 @PutMapping("/{pizzaId}")
     public ResponseEntity<Object> editPizza(@PathVariable("pizzaId") int pizzaId,
 			@RequestBody Pizza pizza) {
 		pizza.setPizzaId(pizzaId);
 		// If message is updated it returns updates message object else null
 		Pizza editPizza = pizzaService.updatePizza(pizza);
 		// response is set to error if message is null.
 		if (editPizza == null) {
 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pizza " + pizzaId + " Not found");
 		} else {
 			// response is set to updated message id in response header section.
 			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
 					.buildAndExpand(pizza.getPizzaId()).toUri();
 			return ResponseEntity.created(location).build();
 		}
 	}
	
	
	
	
	}
	
	
	
	

