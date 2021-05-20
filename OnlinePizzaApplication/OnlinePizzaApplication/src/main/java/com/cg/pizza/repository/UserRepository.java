package com.cg.pizza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.pizza.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUserIdAndPasswordAndRole(int userId, String password, String role);

	public boolean existsByUserId(int userId);

	public User findByUserId(int userId);

	public User userName(String username);

	public User password(String password);

	Optional<User> findByRoleAndPassword(String role, String password);

}
