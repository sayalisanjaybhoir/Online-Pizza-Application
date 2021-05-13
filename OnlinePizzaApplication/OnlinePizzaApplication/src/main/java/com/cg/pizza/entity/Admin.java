package com.cg.pizza.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="adminTable")
public class Admin {
	@Id
	@GeneratedValue
	private int adminId;
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminUserName=" + adminUserName + ", adminPassword=" + adminPassword
				+ "]";
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	private String adminUserName;
	private String adminPassword;

}
