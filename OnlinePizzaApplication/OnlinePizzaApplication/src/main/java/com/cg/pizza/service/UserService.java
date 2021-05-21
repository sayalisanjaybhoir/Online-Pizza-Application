package com.cg.pizza.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.pizza.entity.User;
import com.cg.pizza.exception.InvalidDataException;
import com.cg.pizza.exception.UserExistsException;
import com.cg.pizza.exception.UserNotFoundException;
import com.cg.pizza.repository.UserRepository;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User addNewUser(User user) {

		logger.info("Inside addNewAdmin method");
		User userdb = userRepository.userName(user.getUserName());

		if (userdb != null || userRepository.existsByUserId(user.getUserId())) {
			logger.error("Admin already exists");
			throw new UserExistsException("User Id or User Name exists already");
		} else {
			User userObj = userRepository.save(user);
			logger.info("user added");
			return userObj;
		}

	}

	@Override
	public User signOut(User user) {

		return user;

	}

	@Override
	public User userLogin(String userName, String password) {

		User user = userRepository.userName(userName);
		logger.info(user + "USer");
		if (user != null) {

			if (user.getPassword().equals(password)) {
				logger.debug("user login");
				return user;
			} else {

				logger.info("Invalid Password");
				throw new InvalidDataException("Invalid Password");
			}
		}

		else {
			logger.info("Invalid user name");
			throw new InvalidDataException("Invalid USername");

		}
	}

	@Override
	public List<User> getAll() {
		List<User> userList = userRepository.findAll();
		if (userList.isEmpty()) {
			logger.error("UserNotFoundException in getAllUser method");
			throw new UserNotFoundException("No User found");
		} else {
			return userList;
		}
	}

}
