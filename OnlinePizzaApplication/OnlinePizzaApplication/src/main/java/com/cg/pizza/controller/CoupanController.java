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
import com.cg.pizza.exception.CoupanIdNotFoundException;
import com.cg.pizza.exception.InvalidCoupanOperationException;

@RestController
@RequestMapping("/coupan")
public class CoupanController {
      @Autowired
      private CoupanService coupanService;
      
      @GetMapping("/coupan")
      public ResponseEntity<List<Coupan>> viewCoupans() {

      	List<Coupan> viewCoupanList = coupanService.viewCoupans();
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
      
      
      @GetMapping(value = "/{coupanId}")
  	public ResponseEntity<Object> viewCoupans(@PathVariable("coupanId") int coupanId) {

  		Coupan coupan = coupanService.viewCoupans(coupanId);
  		// Creating an error response.
  		//ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
  	//			.body("Coupan " + coupanId + " Not found");

  		// If message is not null it sets the message object in response else by default
  		// error
  		// will be there in response.
  		if (coupan == null) {
  			//response = new ResponseEntity<>(coupan, HttpStatus.OK);
  			throw new CoupanIdNotFoundException("Coupan ID" + coupanId + "Not Found");
  		}

  		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupan" + coupanId + "Not Found");
  		return new ResponseEntity<>(coupan, HttpStatus.OK);
  	}

      
     @DeleteMapping("/{coupanId}")
 	public ResponseEntity<Object> deleteCoupans(@PathVariable("coupanId") int coupanId) {
 		// If message is deleted it returns deleted message object else null
 		Coupan coupanPresent = coupanService.deleteCoupans(coupanId);
 		// Creating an success response.
 		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
 				.body("Coupan " + coupanId + " deleted");
 		// response is set to error if message is null.
 		if (coupanPresent == null) {
 			//response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupan " + coupanId + " Not found");
 			throw new CoupanIdNotFoundException("Coupan ID" + coupanId + "Not Found");
 		}
 		return ResponseEntity.status(HttpStatus.OK).body("Coupan " + coupanId + "Deleted");
 	}
     
     @PutMapping("/{coupanId}")
     public ResponseEntity<Object> editCoupans(@PathVariable("coupanId") int coupanId,
 			@RequestBody Coupan coupan) {
 		coupan.setCoupanId(coupanId);
 		// If message is updated it returns updates message object else null
 		Coupan editCoupan = coupanService.editCoupans(coupan);
 		// response is set to error if message is null.
 		if (editCoupan == null) {
 			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupan " + coupanId + " Not found");
 			throw new CoupanIdNotFoundException("Coupan ID" + coupanId + "Not Found");
 		} else {
 			// response is set to updated message id in response header section.
 			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
 					.buildAndExpand(coupan.getCoupanId()).toUri();
 			return ResponseEntity.created(location).build();
 		}
 		
 	}

     
     
     @PostMapping
     public ResponseEntity<Object> addCoupans(@RequestBody Coupan coupan) {
    	 
    	 if (coupan.getCoupanName().isEmpty() || coupan.getCoupanName()==null) {
    		 
  			throw new InvalidCoupanOperationException(coupan + "Again enter the coupan");
  		}
 		Coupan newCoupan = coupanService.addCoupans(coupan);
 		
 		if (coupan==null) {
 			 
 			throw new InvalidCoupanOperationException(coupan + "Again enter the coupan");
 		}
 		
 		else{URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
 				.buildAndExpand(newCoupan.getCoupanId()).toUri();
 		return ResponseEntity.created(location).build();
 		}
 	}
 		
 	}
