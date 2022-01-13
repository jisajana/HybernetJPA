package com.java.strategy4.uinontableperclass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="BankAccount4")
public class BankAccount extends BillingDetails {

	private String bankName;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
