package com.cg.pizza.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.pizza.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	//Customer addCustomer(Customer customer);

	
	
	
}
