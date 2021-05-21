package com.cg.pizza.service;

import java.util.List;

import com.cg.pizza.entity.User;

public interface IUserService {

	public User addNewUser(User user);

	public User signOut(User user);

	public List<User> getAll();

	public User userLogin(String role, String password);

}
