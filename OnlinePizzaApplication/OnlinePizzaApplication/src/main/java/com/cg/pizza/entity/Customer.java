package com.cg.pizza.entity;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
	@Id
	@GeneratedValue
	private int customerId;
	@Column(name ="customerName")
	private String customerName;
	@Column(name ="customerMobile")
	private Long customerMobile;
	@Column(name ="customerEmail")
	private String customerEmail;
	@Column(name ="customerAddress")
	private String customerAddress;
	@Column(name ="userName")
	private String userName;
	@Column(name ="password")
	private String password;
	
	@OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Set<PizzaOrder> getPizzaOrderSet() {
		return pizzaOrderSet;
	}



	public void setPizzaOrderSet(Set<PizzaOrder> pizzaOrderSet) {
		this.pizzaOrderSet = pizzaOrderSet;
	}



public Customer() {}


	
	



	public Customer(int customerId, String customerName, Long customerMobile, String customerEmail, String customerAddress,
		String userName, String password, User user, Set<PizzaOrder> pizzaOrderSet) {
	super();
	this.customerId = customerId;
	this.customerName = customerName;
	this.customerMobile = customerMobile;
	this.customerEmail = customerEmail;
	this.customerAddress = customerAddress;
	this.userName = userName;
	this.password = password;
	this.user = user;
	this.pizzaOrderSet = pizzaOrderSet;
}







	@ManyToMany
	@JoinTable(name = "Customer_Pizza_Order", joinColumns = { @JoinColumn(name = "customerId") }, inverseJoinColumns = {
			 @JoinColumn(name = "bookingOrderId") })
	//private PizzaOrder pizzaorder;
	Set<PizzaOrder> pizzaOrderSet = new HashSet<PizzaOrder>();
	
	
	
	
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
		return pizzaOrderSet;
	}

	public void setPizzaSet(Set<PizzaOrder> pizzaSet) {
		this.pizzaOrderSet = pizzaSet;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerMobile="
				+ customerMobile + ", customerEmail=" + customerEmail + ", customerAddress=" + customerAddress
				+ ", userName=" + userName + ", password=" + password + ", user=" + user + ", pizzaOrderSet="
				+ pizzaOrderSet + "]";
	}
}
