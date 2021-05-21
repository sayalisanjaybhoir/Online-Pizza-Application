package com.cg.pizza.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties({ "hibernateLazyInitilizer", "handler" })
@Entity
@Table(name = "UserTable_table")
@JsonInclude(Include.NON_NULL)
public class User implements Serializable {
	@Id
	@GeneratedValue
	private int userId;
	@Column(name = "Username", unique = true)
	private String userName;
	@Column(name = "Password")

	private String password;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private Customer customer;

	private static final long serialVersionUID = 1L;
//   Getter and Setters
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
//   To string Method
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", customer=" + customer
				+ "]";
	}
//  Constructor 
	public User() {
		super();
	}

	public User(String password) {
		super();
		this.password = password;

	}

	public User(@NotEmpty int userId, String password) {

		super();
		this.userId = userId;
		this.password = password;

	}

	public User(@NotEmpty int userId, String userName, String password) {

		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		//this.customer = customer;

	}
}
