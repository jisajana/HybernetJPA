package com.java.strategy1.tableperhierarchy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="CreditCard1")
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {

	String type;
	String expiryMonth;
	String expiryYear;
	
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
