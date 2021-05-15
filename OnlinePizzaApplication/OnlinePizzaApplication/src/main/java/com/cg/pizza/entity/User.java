package com.cg.pizza.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "UserTable")
public class User {
	@Id
	@GeneratedValue
	private int userId;
	@Column(name = "Username")
	private String userName;
	@Column(name="Password")
	private String password;
	@OneToOne
	private Customer customer;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public User(int userId, String userName, String password, Customer customer) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.customer = customer;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", customer=" + customer
				+ "]";
	}
	
	
	
	
	
}