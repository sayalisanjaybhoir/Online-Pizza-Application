package com.cg.pizza.service;

import java.util.List;
import java.util.Optional;

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

		if (userRepository.existsByUserId(user.getUserId())) {
		logger.error("Admin already exists");
		throw new UserExistsException("User Id exists already"); 
	} else {
		User userObj = userRepository.save(user);
		logger.info("user added");
		return userObj;
	}
		
}
	
	
	
	
	
	

	@Override
	public User signIn(User user)  {
		logger.info("Inside signIn method");
		int id = user.getUserId();
		String password = user.getPassword();
		String role = user.getRole();
		User userData = userRepository.findByUserIdAndPasswordAndRole(id, password, role);
		if (userData == null) {
			logger.error("UserNotFoundException in userSignIn method");
			throw new UserNotFoundException("No user present"); 
		} else {

			return userData;
		}
	}


	@Override
	public User signOut(User user) {
		
		return null;
	}



	@Override
	public User userLogin(String role, String password) {
		
		Optional<User> opuserEntity = userRepository.findByRoleAndPassword(role, password);
		if(opuserEntity.isPresent())
		{
			User userEntity = opuserEntity.get();
			logger.debug("user login");
		    int n=userEntity.getAttempts();
		    if(n<3) {
		    	if(userEntity.getPassword().equals(password)){
		    		return new User(userEntity.getUserId(), userEntity.getRole(), userEntity.getPassword(), userEntity.getAttempts());
                                            
				}
		    	else {
						userEntity.setAttempts(n+1);
						userEntity=userRepository.save(userEntity);
						logger.info("Invalid Password");
						throw new InvalidDataException("Invalid Password");
					}
			}
		    else {
				logger.info("Reached maximum limit");
				throw new InvalidDataException("Reached maximum limit");
			}			
		}
		else
		{
			logger.info("Login Failed");
		throw new InvalidDataException("Login Failed");

		}
		
	}

	@Override
	public List<User> getAll() {
		List<User> userList = userRepository.findAll();
		if (userList.isEmpty()) {
			logger.error("UserNotFoundException in getAllUser method");
			throw new UserNotFoundException("No User found"); 
		}
		else {
			return userList;
		}
}

	@Override
	public void forgotPassword(int userId) {
		
		
	}
	
}


	
	