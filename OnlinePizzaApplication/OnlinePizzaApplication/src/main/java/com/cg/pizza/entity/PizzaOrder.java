package com.cg.pizza.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitilizer", "handler" })
@Entity
@Table(name = "Pizza_Order_Table")

public class PizzaOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "booking_id")
	private int bookingOrderId;
	@Column(name = "order_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate orderDate;
	@Column(name = "transc_mode")
	private String transactionMode;
	@Column(name = "pizza_quantity")
	private int quantity;
	@NotBlank(message = "Enter the value in string format from small , medium,large")
	@Column(name = "pizza_size")
	private String size;

	@Column(name = "total_pizza_cost")

	private double totalCost;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "Pizza_Order", joinColumns = { @JoinColumn(name = "bookingOrderId") }, inverseJoinColumns = {
			@JoinColumn(name = "pizzaId") })
	Set<Pizza> pizzaSet = new HashSet<Pizza>();

	@Embedded
	private Order order;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "Coupan_PizzaOrder", joinColumns = {
			@JoinColumn(name = "bookingOrderId") }, inverseJoinColumns = { @JoinColumn(name = "coupanId") })
	Set<Coupan> coupanSet = new HashSet<Coupan>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "Customer_Pizza_Order", joinColumns = {
			@JoinColumn(name = "bookingOrderId") }, inverseJoinColumns = { @JoinColumn(name = "customerId") })
	Set<Customer> customerSet = new HashSet<Customer>();

	public PizzaOrder() {

	}

	public PizzaOrder(int bookingOrderId, LocalDate orderDate, String transactionMode, int quantity,
			@NotBlank(message = "Enter the value in string format from small , medium,large") String size,
			double totalCost) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.orderDate = orderDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.size = size;
		this.totalCost = totalCost;
	}

	public PizzaOrder(int bookingOrderId, LocalDate orderDate, String transactionMode, int quantity, String size,
			double totalCost, Set<Pizza> pizzaSet, Order order, Set<Coupan> coupanSet, Set<Customer> customerSet) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.orderDate = orderDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.size = size;
		this.totalCost = totalCost;
		this.pizzaSet = pizzaSet;
		this.order = order;
		this.coupanSet = coupanSet;
		this.customerSet = customerSet;
	}

	public int getBookingOrderId() {
		return bookingOrderId;
	}

	public void setBookingOrderId(int bookingOrderId) {
		this.bookingOrderId = bookingOrderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Set<Pizza> getPizzaSet() {
		return pizzaSet;
	}

	public void setPizzaSet(Set<Pizza> pizzaSet) {
		this.pizzaSet = pizzaSet;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Set<Coupan> getCoupanSet() {
		return coupanSet;
	}

	public void setCoupanSet(Set<Coupan> coupanSet) {
		this.coupanSet = coupanSet;
	}

	public Set<Customer> getCustomerSet() {
		return customerSet;
	}

	public void setCustomerSet(Set<Customer> customerSet) {
		this.customerSet = customerSet;
	}

	@Override
	public String toString() {
		return "PizzaOrder [bookingOrderId=" + bookingOrderId + ", orderDate=" + orderDate + ", transactionMode="
				+ transactionMode + ", quantity=" + quantity + ", size=" + size + ", totalCost=" + totalCost
				+ ", pizzaSet=" + pizzaSet + ", order=" + order + ", coupanSet=" + coupanSet + ", customerSet="
				+ customerSet + "]";
	}

}

