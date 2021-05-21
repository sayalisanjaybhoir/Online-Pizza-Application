package com.cg.pizza.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.cg.pizza.entity.Pizza;
import com.cg.pizza.exception.InvalidMinCostException;
import com.cg.pizza.exception.InvalidPizzaOperationException;
import com.cg.pizza.exception.InvalidPizzaTypeException;
import com.cg.pizza.exception.PizzaIdNotFoundException;
import com.cg.pizza.service.PizzaService;

@RestController
@RequestMapping("pizzahome")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	Logger logger = LoggerFactory.getLogger(PizzaController.class); // also in service

	@GetMapping("/pizza")
	public ResponseEntity<List<Pizza>> getPizzaList() {

		logger.info("Get all pizza list");
		List<Pizza> pizzaList = pizzaService.viewPizzaList();
		ResponseEntity<List<Pizza>> response = new ResponseEntity<>(pizzaList, HttpStatus.NOT_FOUND);
		if (!pizzaList.isEmpty()) {
			response = new ResponseEntity<>(pizzaList, HttpStatus.OK);
		}
		return response;
	}

	@GetMapping(value = "/{pizzaId}")
	public ResponseEntity<Object> viewPizzaById(@PathVariable("pizzaId") int pizzaId) {
		logger.info("Search by Pizza Id");
		Pizza pizza = pizzaService.viewPizza(pizzaId);
		if (pizza == null) {
			logger.error("Pizza ID Not Found Exception");
			throw new PizzaIdNotFoundException(pizzaId + " This id is not available");
		}
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{pizzaId}")
	public ResponseEntity<Object> deletePizza(@PathVariable("pizzaId") int pizzaId) {
		logger.info("Delete by Pizza Id");

		Pizza pizzaPresent = pizzaService.deletePizza(pizzaId);

		if (pizzaPresent == null) {
			logger.error("Pizza ID Not Found to be deleted");
			throw new PizzaIdNotFoundException(pizzaId + " This pizza id is not available to delete ");
		} else

			return ResponseEntity.status(HttpStatus.OK).body("pizza " + pizzaId + " deleted");
	}

	@PostMapping
	public ResponseEntity<Object> addPizza(@RequestBody Pizza pizza) {
		logger.info("Inside Post add method");
		if ((pizza.getPizzaName().isEmpty() || pizza.getPizzaDescription().isEmpty()) || (pizza.getPizzaCost() < 1 ||  pizza.getPizzaCostAfterCoupan() < 1)) 
		{
			logger.error("entered values are null or incorrect");
			throw new InvalidPizzaOperationException(" Enter correct Pizza details");
			
		}			
			else  if(pizza.getPizzaId()<0) {
	    		 logger.error("Coupan id is negative");
	    		 throw new PizzaIdNotFoundException("Pizza Id cannot be negative");
		}
		 else  {
			pizzaService.addPizza(pizza);
			return ResponseEntity.status(HttpStatus.OK).body("pizza added");
		}
	}

	@PutMapping("/{pizzaId}")
	public ResponseEntity<Object> editPizza(@PathVariable("pizzaId") int pizzaId, @RequestBody Pizza pizza) {
		logger.info("Inside Put method of Pizza");
		if ((pizza.getPizzaName().isEmpty() || pizza.getPizzaDescription().isEmpty()) || (pizza.getPizzaCost() < 1 ||  pizza.getPizzaCostAfterCoupan() < 1)) 
		{
			logger.error("entered values are null or incorrect");
			throw new InvalidPizzaOperationException(" Enter correct Pizza details"); }
			
		else  if(pizza.getPizzaId()<0) {
   		 logger.error("Coupan id is negative");
   		 throw new PizzaIdNotFoundException("Pizza Id cannot be negative"); }

		 else {
						
			pizza.setPizzaId(pizzaId);
			pizzaService.updatePizza(pizza);
		 }
		return ResponseEntity.status(HttpStatus.OK).body("Pizza");
		}
	

	@GetMapping("/between/{minimumcost}/{maximumcost}")

	public List<Pizza> findBypizzaCostBetween(@PathVariable("minimumcost") double minCost,
			@PathVariable("maximumcost") double maxCost) {
		List<Pizza> pizList = pizzaService.viewPizzaList(minCost, maxCost);
		logger.info("Inside find between min & max costmethod");

		if (minCost < 0 || maxCost < 1 || maxCost < minCost) {
			logger.error("Invalid range");

			throw new InvalidMinCostException("Please check the cost given ");
		} else
           
			return pizList;
	}

	@GetMapping("/ByPizzType/{type}")
	public List<Pizza> findBypizzaType(@PathVariable("type") String pizzaType) {
		logger.info("Inside view pizza by type method");

	if((("veg").equalsIgnoreCase(pizzaType) || pizzaType.equalsIgnoreCase("non-veg")))
	{
				
		List<Pizza> pizTypeList = pizzaService.viewPizzaList(pizzaType);
		return pizTypeList;
	}
		else { 			
			
			logger.error("enter correect pizza type");
			throw new InvalidPizzaTypeException("Pizza type can only be veg or non-veg ");
		}
	
	
	}
	
}
