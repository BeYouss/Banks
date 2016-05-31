package com.mybank.models;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<Account> Accounts;
	private List<Customer> Customers;

	public Bank() {
		this.Accounts = new ArrayList<Account>();
		this.Customers = new ArrayList<Customer>();
	}

	public String toString() {
		return "Bank: ";
	}
}
