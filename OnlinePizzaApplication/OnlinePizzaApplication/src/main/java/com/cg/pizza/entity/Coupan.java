package com.cg.pizza.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name="coupandetails")
public class Coupan {
      @Id
      @GeneratedValue
      private int coupanId;
      @Column(name=" coupanName")
      private String coupanName;
      @Column(name=" coupanDescription")
      private String coupanDescription;
      @Column(name=" coupanPizzaId")
      private int coupanPizzaId;
      
      @ManyToMany
      @JoinTable(name = "Coupan_PizzaOrder", joinColumns = { @JoinColumn(name = "coupanId") }, inverseJoinColumns = {
  			@JoinColumn(name = "bookingOrderId") })
    // PizzaOrder pizzaorder;
      Set<PizzaOrder> pizzaOrderSet = new HashSet<PizzaOrder>();
      
      public Coupan() {}
//Getter and Setter Methods     
	public int getCoupanId() {
		return coupanId;
	}
	public void setCoupanId(int coupanId) {
		this.coupanId = coupanId;
	}
	public String getCoupanName() {
		return coupanName;
	}
	public Set<PizzaOrder> getPizzaSet() {
		return pizzaOrderSet;
	}
	public void setPizzaSet(Set<PizzaOrder> pizzaSet) {
		this.pizzaOrderSet = pizzaSet;
	}
	public void setCoupanName(String coupanName) {
		this.coupanName = coupanName;
	}
	public String getCoupanDescription() {
		return coupanDescription;
	}
	public void setCoupanDescription(String coupanDescription) {
		this.coupanDescription = coupanDescription;
	}
	public int getCoupanPizzaId() {
		return coupanPizzaId;
	}
	public void setCoupanPizzaId(int coupanPizzaId) {
		this.coupanPizzaId = coupanPizzaId;
	}
	@Override
	public String toString() {
		return "Coupan [coupanId=" + coupanId + ", coupanName=" + coupanName + ", coupanDescription="
				+ coupanDescription + ", coupanPizzaId=" + coupanPizzaId + ", pizzaOrderSet=" + pizzaOrderSet + "]";
	}
	
	
      
      
      
}
