package com.mybank.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String firstName;
	private String lastName;
	private int id;
	
	public Customer() {

	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		 this.id =id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		return "Customer: " + this.firstName + ", " + this.lastName;
	}
}

//
// public Customer(String firstName, String lastName) {
// this.firstName = firstName;
// this.lastName = lastName;
// this.Accounts = new ArrayList<>();
// }
//
// public String getFirstName() {
// return this.firstName;
// }
//
// public String getLastName() {
// return this.lastName;
// }
//
// public String getFullName() {
// return this.getFirstName() + ", " + this.getLastName();
// }
//
// public List<Account> getAccounts() {
// return this.Accounts;
// }