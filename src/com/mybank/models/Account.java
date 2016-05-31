package com.mybank.models;

public class Account {

	private double balance;
	private String accountNumber;

	public Account() {

	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String toString() {
		return "Account#: " + this.accountNumber + ", Balance: " + this.balance;
	}

}
