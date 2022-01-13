package com.java.strategy4.uinontableperclass;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name="BillingDetails4")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class BillingDetails {

	@Id
	private int id;
	
	private String owner;
	private String number;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "BillingDetails{" +
				"id=" + id +
				", owner='" + owner + '\'' +
				", number='" + number + '\'' +
				'}';
	}
}
