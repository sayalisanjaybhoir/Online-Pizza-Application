package com.cg.pizza.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
	private int customerId;
	private String customerName;
	private Long customerMobile;
	private String customerEmail;
	private String customerAddress;
	private String userName;
	private String password;


	
	@ManyToMany
	@JoinTable(name = "Customer_Pizza_Order", joinColumns = { @JoinColumn(name = "customerId") }, inverseJoinColumns = {
			 @JoinColumn(name = "bookingOrderId") })
	//private PizzaOrder pizzaorder;
	Set<PizzaOrder> pizzaSet = new HashSet<PizzaOrder>();
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(Long customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<PizzaOrder> getPizzaSet() {
		return pizzaSet;
	}

	public void setPizzaSet(Set<PizzaOrder> pizzaSet) {
		this.pizzaSet = pizzaSet;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
