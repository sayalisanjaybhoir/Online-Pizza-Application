package com.cg.pizza.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pizza.entity.User;
import com.cg.pizza.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/addNewUser")
	public ResponseEntity<String> addNewUser(@Valid @RequestBody User user, HttpServletRequest request) {
		logger.info("Inside addNewUser method");

		User userData = userService.addNewUser(user);
		if (userData != null)
			return new ResponseEntity<>("Added successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("User not added", HttpStatus.OK);
	}

	@GetMapping("signin/{userName}/{password}")
	public ResponseEntity<User> userLogin(@PathVariable(value = "userName") String userName,
			@PathVariable(value = "password") String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = userService.userLogin(userName, password);
		if (user != null) {
			session.setAttribute("UserExists", user);

			logger.info("Login successfully!" + user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			logger.info("Login Failed!!!");

			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/signOut")
	public ResponseEntity<String> signOut(@Valid @RequestBody User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("UserExists");
		session.invalidate();
		logger.info("Inside userSignOut method");
		User userData = userService.signOut(user);

		return new ResponseEntity<String>("Sign Out succesfull, Bye user: " + userData.getUserId(), HttpStatus.OK);
	}

}
