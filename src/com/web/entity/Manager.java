package com.web.entity;

import java.io.Serializable;

public class Manager implements Serializable{
	private String managerAccount;
	private String managerPassword;
	
	public String getManagerAccount() {
		return managerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
}
