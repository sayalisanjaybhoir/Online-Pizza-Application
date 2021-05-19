package com.cg.pizza.entity;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonIgnoreProperties({"hibernateLazyInitilizer","handler"})
@Entity
@Table(name= "UserTable")
@JsonInclude(Include.NON_NULL)
public class User implements Serializable {
	@Id
	@NotEmpty
	@Size(min=3, max=6, message = "Admin Id should be between 3 to 6 characters")
	@GeneratedValue
	private int userId;
	@Column(name = "Username")
	private String userName;
	@Column(name="Password")
	@NotEmpty(message = "Password must not be empty")
	@Size(min=8, max=20, message="Password should be between 2 and 8 characters")
	@Pattern(regexp="^(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,20}", message="Password must contain atleast one upper case, one lower case a digit and a special character")
	private String password;
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	private Customer customer;
	@NotEmpty(message = "Role must not be empty")
	private String role;
	@Min(value = 0)
	private int attempts;
	private static final long serialVersionUID = 1L;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", customer=" + customer
				+ ", role=" + role + ", attempts=" + attempts + "]";
	}
	public User() {
		super();
	}
	
	public User(String password, @NotEmpty(message = "Role must not be empty") String role) {
		super();
		this.password = password;
		this.role = role;
	}
		
	public User(@NotEmpty int userId, String password, @NotEmpty(message = "Role must not be empty") String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	public User(@NotEmpty int userId, String password, @NotEmpty(message = "Role must not be empty") String role,
		int attempts) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
		this.attempts = attempts;
		}

	
	public User(@NotEmpty int userId, String userName, String password, Customer customer,
			@NotEmpty(message = "Role must not be empty") String role, int attempts) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.customer = customer;
		this.role = role;
		this.attempts = attempts;
	}
}