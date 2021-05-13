package com.cg.pizza.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="pizzatable")
public class Pizza {
	@Id
	@GeneratedValue
	private int pizzaId;
	@GeneratedValue
	private String pizzaType;
	@GeneratedValue
	private String pizzaName;
	@GeneratedValue
	private String pizzaDescription;
	@GeneratedValue
	private double pizzaCost;
	@GeneratedValue
	private double pizzaCostAfterCoupan;
	
	
	@ManyToMany
	@JoinTable(name = "Pizza_Order", joinColumns = { @JoinColumn(name = "pizzaId") }, inverseJoinColumns = {
			 @JoinColumn(name = "bookingOrderId") })
	//private PizzaOrder pizzaorder;
	Set<PizzaOrder> pizzaSet = new HashSet<PizzaOrder>();
	public Set<PizzaOrder> getPizzaSet() {
		return pizzaSet;
	}
	public void setPizzaSet(Set<PizzaOrder> pizzaSet) {
		this.pizzaSet = pizzaSet;
	}
	public int getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}
	public String getPizzaType() {
		return pizzaType;
	}
	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getPizzaDescription() {
		return pizzaDescription;
	}
	public void setPizzaDescription(String pizzaDescription) {
		this.pizzaDescription = pizzaDescription;
	}
	public double getPizzaCost() {
		return pizzaCost;
	}
	public void setPizzaCost(double pizzaCost) {
		this.pizzaCost = pizzaCost;
	}
	public double getPizzaCostAfterCoupan() {
		return pizzaCostAfterCoupan;
	}
	public void setPizzaCostAfterCoupan(double pizzaCostAfterCoupan) {
		this.pizzaCostAfterCoupan = pizzaCostAfterCoupan;
	}

}
