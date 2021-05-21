package com.cg.pizza.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.cg.pizza.entity.PizzaOrder;
import com.cg.pizza.exception.InvalidSizeException;
import com.cg.pizza.exception.OrderIdNotFoundException;
import com.cg.pizza.service.PizzaOrderService;

@RestController
@RequestMapping("/pizzaorder")
public class PizzaOrderController {
	Logger log = LoggerFactory.getLogger(PizzaOrderController.class);
	@Autowired
	private PizzaOrderService pizzaorderservice;

	// 1.Get All pizzaList

	@GetMapping
	public ResponseEntity<List<PizzaOrder>> getPizzaOrder() {
		log.debug("In getPizzaOrder Method");
		List<PizzaOrder> pizzaOrderList = pizzaorderservice.viewOrderList();
		ResponseEntity<List<PizzaOrder>> response = new ResponseEntity<>(pizzaOrderList, HttpStatus.NOT_FOUND);
		if (!pizzaOrderList.isEmpty()) {
			log.info("Inside View  all Pizza method");
			response = new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
		}
		return response;
	}

	// 2.Get Pizza By Booking id

	@GetMapping(value = "/{pizzaBookOrderId}")
	public ResponseEntity<Object> getPizzaByOrderId(@Valid @PathVariable("pizzaBookOrderId") int pizzaOrderId) {
		PizzaOrder pizzaOrderList = pizzaorderservice.viewPizzaOrder(pizzaOrderId);
		if (pizzaOrderList == null) {
			log.error("Pizza Order ID Not Found Exception");
			throw new OrderIdNotFoundException(pizzaOrderId + " not found");
		}
		log.info("Inside View  all Pizza method");
		return new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);

	}

	// 3 get Pizza By Date
	@GetMapping(value = "/date/{pizzaOrderDate}")
	public ResponseEntity<Object> getPizzaByDate(
			@PathVariable("pizzaOrderDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate) {
		List<PizzaOrder> pizzaOrderList = pizzaorderservice.viewOrderByDate(orderDate);
		if (pizzaOrderList.isEmpty()) {
			log.error("OrderDate not found");
			throw new OrderIdNotFoundException("OrderDate " + orderDate + " Not Found");
		} else
			log.info("Found order date");
		return new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);

	}

	// 4 Delete Pizza By Booking ID

	@DeleteMapping(value = "/{bookId}")
	public ResponseEntity<Object> deletePizzaOrder(@PathVariable("bookId") int bookingId) {

		PizzaOrder pizzaOrderList = pizzaorderservice.cancelPizzaOrder(bookingId);
		if (pizzaOrderList == null) {
			// response= new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
			log.error("Pizza Order ID Not Found Exception");
			throw new OrderIdNotFoundException(bookingId + " not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Pizza with pizza id  " + bookingId + " deleted");

	}

	// 5
	@PutMapping("/{bookId}")
	public ResponseEntity<Object> updatePizzaOrder(@PathVariable("bookId") int bookingId,
			@RequestBody PizzaOrder pizzaOrder) {

		PizzaOrder updatePizzaOrder = pizzaorderservice.updatePizzaOrder(pizzaOrder);

		if (updatePizzaOrder.getTotalCost() < 0 || updatePizzaOrder.getQuantity() < 0
				|| updatePizzaOrder.getSize().isEmpty()) {
			log.info("Object is null");
			throw new OrderIdNotFoundException(bookingId + " not found");
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(pizzaOrder.getBookingOrderId()).toUri();
			return ResponseEntity.created(location).build();

		}
	}

	// 6 Add the pizza order

	@PostMapping
	public ResponseEntity<Object> addPizzaOrder(@RequestBody PizzaOrder pizzaOrder) {

		if (pizzaOrder.getOrderDate().isAfter(LocalDate.now()))
			throw new OrderIdNotFoundException("Enter the correct date");
		else if (pizzaOrder.getBookingOrderId() < 0)
			throw new OrderIdNotFoundException("Enter the proper booking id");
		else if (pizzaOrder.getSize().isEmpty())
			throw new OrderIdNotFoundException("Enter the correct size in string format");
		else if (pizzaOrder.getQuantity() < 0)
			throw new OrderIdNotFoundException("Enter the correct quantity");

		else
			pizzaorderservice.bookPizzaOrder(pizzaOrder);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pizzaOrder.getBookingOrderId()).toUri();
		return ResponseEntity.created(location).build();
	}

	// 7 Calculate the total cost
	@PatchMapping("/cost/{pizzaId}/{Pizzasize}/{Pizzaquantity}")
	public ResponseEntity<Object> getPizzaCost(@PathVariable("pizzaId") int pizzaId,
			@PathVariable("Pizzasize") String size, @PathVariable("Pizzaquantity") int quantity) {

		if (!(size.equals("small") || size.equals("medium") || size.equals("large"))) {
			throw new InvalidSizeException(size + " is Invalid .Please enter correct data");
		}
		if (quantity < 0) {
			log.error("Quantity should be greater than zero");
		}
		List<PizzaOrder> pizzaOrderList = pizzaorderservice.calculateTotal(pizzaId, size, quantity);

		if (pizzaOrderList.isEmpty()) {
			log.info("No Pizza Found");
			throw new OrderIdNotFoundException(pizzaId + " not found");
		}
		return new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
	}

}

