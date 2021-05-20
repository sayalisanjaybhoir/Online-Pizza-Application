package com.cg.pizza.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitilizer", "handler" })
@Entity
@Table(name = "pizza_table")
public class Pizza implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "pizza_id")
	private int pizzaId;
	
	@Column(name = "pizza_type")
	private String pizzaType;
	@Column(name = "pizza_name")
	private String pizzaName;
	@Column(name = "pizza_description")
	private String pizzaDescription;
	@Column(name = "pizza_cost")
	private double pizzaCost;
	@Column(name = "pizza_caftercoupon")
	private double pizzaCostAfterCoupan;

	public Pizza() {
		super();

	}

	public Pizza(int pizzaId, String pizzaType, String pizzaName, String pizzaDescription, double pizzaCost,
			double pizzaCostAfterCoupan, Set<PizzaOrder> pizzaOrderSet) {
		super();
		this.pizzaId = pizzaId;
		this.pizzaType = pizzaType;
		this.pizzaName = pizzaName;
		this.pizzaDescription = pizzaDescription;
		this.pizzaCost = pizzaCost;
		this.pizzaCostAfterCoupan = pizzaCostAfterCoupan;
		this.pizzaOrderSet = pizzaOrderSet;
	}

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "Pizza_Order", joinColumns = { @JoinColumn(name = "pizzaId") }, inverseJoinColumns = {
			@JoinColumn(name = "bookingOrderId") })
	
	Set<PizzaOrder> pizzaOrderSet = new HashSet<PizzaOrder>();

	public Set<PizzaOrder> getPizzaSet() {
		return pizzaOrderSet;
	}

	public void setPizzaSet(Set<PizzaOrder> pizzaSet) {
		this.pizzaOrderSet = pizzaSet;
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

	@Override
	public String toString() {
		return "Pizza [pizzaId=" + pizzaId + ", pizzaType=" + pizzaType + ", pizzaName=" + pizzaName
				+ ", pizzaDescription=" + pizzaDescription + ", pizzaCost=" + pizzaCost + ", pizzaCostAfterCoupan="
				+ pizzaCostAfterCoupan + ", pizzaSet=" + pizzaOrderSet + "]";
	}
}
