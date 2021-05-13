package com.cg.pizza.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Order
{
	@Id
	@GeneratedValue
	private int orderId;
	private String orderType;
	private String orderDescription;
	private int orderCustomerId;
	
	
	
	public Order() {
		super();
	}



	public Order(int orderId, String orderType, String orderDescription, int orderCustomerId) {
		super();
		this.orderId = orderId;
		this.orderType = orderType;
		this.orderDescription = orderDescription;
		this.orderCustomerId = orderCustomerId;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public String getOrderType() {
		return orderType;
	}



	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}



	public String getOrderDescription() {
		return orderDescription;
	}



	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}



	public int getOrderCustomerId() {
		return orderCustomerId;
	}



	public void setOrderCustomerId(int orderCustomerId) {
		this.orderCustomerId = orderCustomerId;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderType=" + orderType + ", orderDescription=" + orderDescription
				+ ", orderCustomerId=" + orderCustomerId + "]";
	}
	
	
	
}
