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

import java.net.URI;
import java.util.*;
import com.cg.pizza.service.CoupanService;
import com.cg.pizza.entity.Coupan;

@RestController
@RequestMapping
public class CoupanController {
      @Autowired
      private CoupanService coupanService;
      
      @GetMapping("/coupan")
      public ResponseEntity<List<Coupan>> viewCoupan() {

      	List<Coupan> viewCoupanList = coupanService.viewCoupan();
		// Creating an error response.
      	ResponseEntity<List<Coupan>> response = new ResponseEntity<>(viewCoupanList, HttpStatus.NOT_FOUND);

      	// If messageList is not empty it sets the list in response else by default
      	// error will be
      	// there in response.
      	if (!viewCoupanList.isEmpty()) {
      		response = new ResponseEntity<>(viewCoupanList, HttpStatus.OK);
      	}

      	return response;
      }
      
     @DeleteMapping("/{coupanId}")
 	public ResponseEntity<Object> deleteCoupan(@PathVariable("coupanId") int coupanId) {
 		// If message is deleted it returns deleted message object else null
 		Coupan coupanPresent = coupanService.deleteCoupan(coupanId);
 		// Creating an success response.
 		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
 				.body("Message " + coupanId + " deleted");
 		// response is set to error if message is null.
 		if (coupanPresent == null) {
 			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupan " + coupanId + " Not found");
 		}
 		return response;
 	}
     
     @PutMapping("/{coupanId}")
     public ResponseEntity<Object> editCoupan(@PathVariable("coupanId") int coupanId,
 			@RequestBody Coupan coupan) {
 		coupan.setCoupanId(coupanId);
 		// If message is updated it returns updates message object else null
 		Coupan editCoupan = coupanService.editCoupan(coupan);
 		// response is set to error if message is null.
 		if (editCoupan == null) {
 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupan " + coupanId + " Not found");
 		} else {
 			// response is set to updated message id in response header section.
 			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
 					.buildAndExpand(coupan.getCoupanId()).toUri();
 			return ResponseEntity.created(location).build();
 		}
 	}

     
     
     @PostMapping
     public ResponseEntity<Object> addCoupan(@RequestBody Coupan coupan) {
 		// If message is inserted it returns inserted message object else null
 		Coupan newCoupan = coupanService.insertCoupan(coupan);
 		// response is set to error if message is null.
 		if (newCoupan == null)
 			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
 		// response is set to inserted message id in response header section.
 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
 				.buildAndExpand(newCoupan.getCoupanId()).toUri();
 		return ResponseEntity.created(location).build();
 	}
      

}
