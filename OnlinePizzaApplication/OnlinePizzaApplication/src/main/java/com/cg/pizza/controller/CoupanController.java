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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import java.util.*;

import javax.validation.Valid;

import com.cg.pizza.service.CoupanService;

import com.cg.pizza.entity.Coupan;
import com.cg.pizza.exception.CoupanIdNotFoundException;
import com.cg.pizza.exception.CoupanNameNotFoundException;
import com.cg.pizza.exception.InvalidCoupanOperationException;

@RestController
@RequestMapping("/coupan")
public class CoupanController {
      @Autowired
      private CoupanService coupanService;
      Logger logger = LoggerFactory.getLogger(CoupanController.class);
      
//1 Get all coupans  
      @GetMapping("/coupan")
      public ResponseEntity<List<Coupan>> viewCoupans() {

      	List<Coupan> viewCoupanList = coupanService.viewCoupans();
		// Creating an error response.
      	ResponseEntity<List<Coupan>> response = new ResponseEntity<>(viewCoupanList, HttpStatus.NOT_FOUND);
      	
      	// If coupanList is not empty it sets the list in response else by default
      	// error will be
      	// there in response.
      	if (!viewCoupanList.isEmpty()) {
      		response = new ResponseEntity<>(viewCoupanList, HttpStatus.OK);
      	}

      	return response;
      }
      
    //2 Get coupan by id     
      @GetMapping(value = "/{coupanId}")
  	public ResponseEntity<Object> viewCoupans(@Valid@PathVariable("coupanId") int coupanId) {

    	  Coupan coupan = coupanService.viewCoupans(coupanId);	
  		 if(coupanId<0 ) {
    		 logger.error("Coupan id cant be negative");
    		throw new CoupanIdNotFoundException( "Coupan Id is not negative");
    	 }  		 
//  		 else if  (!System.coupanName.RegularExpressions.Regex.IsMatch("^[0-9]", coupan))
//  
//  		 {
//  			throw new CoupanIdNotFoundException( "Coupan name  cannot be numeric");
//         }
//     }
  		
  		// Creating an error response.
  		//ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
  	//			.body("Coupan " + coupanId + " Not found");

  		// If coupan is not null it sets the coupan object in response else by default
  		// error
  		// will be there in response.
  		 

  		 else if (coupan == null) {
  			//response = new ResponseEntity<>(coupan, HttpStatus.OK);
  			logger.error(" Coupan Id Not Found Exception");
  			throw new CoupanIdNotFoundException("Coupan ID"  + coupanId +  "Not Found");
  		}

  		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupan" + coupanId + "Not Found");
  		return new ResponseEntity<>(coupan, HttpStatus.OK);
  	}

      
 //3 Get Coupan by name
      @GetMapping("/coupan/{coupanName}")
      public ResponseEntity<Object> viewCoupanList(@PathVariable("coupanName") String coupanName) {
  		logger.info("Inside view pizza by type method");

  		List<Coupan> bycoupanName = coupanService.viewCoupanList(coupanName);
  		
      if (bycoupanName.isEmpty() || bycoupanName==null) {
       throw new CoupanNameNotFoundException("Enter proper coupan name");
}
//      else if(!System.bycoupanName.RegularExpressions.Regex.IsMatch("^[0-9]", bycoupanName) {
//    	  throw new CoupanNameNotFoundException("Coupan Name cannot be an integer")
//      }
      return new ResponseEntity<>(bycoupanName, HttpStatus.OK); 
      }
      
//4 Delete the coupan by id      
     @DeleteMapping("/{coupanId}")
 	public ResponseEntity<Object> deleteCoupans(@PathVariable("coupanId") int coupanId) {
 		// If coupan is deleted it returns deleted coupan object else null
 		Coupan coupanPresent = coupanService.deleteCoupans(coupanId);	
  		 if(coupanId<0) {
    		 logger.error("Coupan id cant be negative");
    		throw new CoupanIdNotFoundException("Coupan Id is not negative");
    	 }
 		// Creating an success response.
// 		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
// 				.body("Coupan " + coupanId + " deleted");
 		// response is set to error if coupan is null.
  		 else if (coupanPresent == null) {
 			//response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupan " + coupanId + " Not found");
 			throw new CoupanIdNotFoundException("Coupan ID" + coupanId + "Not Found");
 		}
 		return ResponseEntity.status(HttpStatus.OK).body("Coupan " + coupanId + "Deleted");
 	}

     
     
     
//5 Update the coupan by id     
     @PutMapping("/{coupanId}")
     public ResponseEntity<Object> editCoupans(@PathVariable("coupanId") int coupanId,
 			@RequestBody Coupan coupan) {
 		coupan.setCoupanId(coupanId);
 		// If coupan is updated it returns updates coupan object else null
 		Coupan editCoupan = coupanService.editCoupans(coupan);
 		// response is set to error if coupan is null.
 		if (editCoupan==null) {
 			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupan " + coupanId + " Not found");
 			throw new CoupanIdNotFoundException("Coupan ID" + coupanId + "Not Found");
 		} else {
 			// response is set to updated message id in response header section.
 			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
 					.buildAndExpand(coupan.getCoupanId()).toUri();
 			return ResponseEntity.created(location).build();
 		}
 		
 	}

     
//6 Insert the coupan     
     @PostMapping
     public ResponseEntity<Object> addCoupans(@RequestBody Coupan coupan) {
    	 logger.debug("Coupan No is coming here"+coupan.getCoupanId());
    	
    	 if (coupan.getCoupanName().isEmpty() || coupan.getCoupanName()==null) {
    		 logger.error("Coupan Name is not here");
   
  			throw new InvalidCoupanOperationException(coupan + "Again enter the coupan Name");
  		}
    	 else  if(coupan.getCoupanId()<0) {
    		 logger.error("Coupan id is coming here");
    		 throw new CoupanIdNotFoundException("Coupan Id cannot be negative");
    	 }
    	 Coupan newCoupan = coupanService.addCoupans(coupan); 
 		
 		if (newCoupan==null) {
 			 
 			throw new InvalidCoupanOperationException(coupan + "Again enter the coupan");
 		}
 		
 		else{URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
 				.buildAndExpand(newCoupan.getCoupanId()).toUri();
 		return ResponseEntity.created(location).build();
 		}
 	}
 		
 	}
      





