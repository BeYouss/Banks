package com.mybank.client;

import java.util.List;

import com.mybank.dao.*;
import com.mybank.models.Account;
import com.mybank.models.Customer;

public class App {

	private static CustomerDao customersRepository;
	private static AccountDao accountsRepository;

	public static void main(String[] args) {
		customersRepository = new CustomerDaoImpl();
		accountsRepository = new AccountDaoImpl();

		// get list of customers
		System.out.println("get list of customers");
		getCustomers();
		System.out.println("--------------------------------------------------");

		// get customer by id
		System.out.println("get customer# 2");
		Customer myCustomer = getCustomer(2);
		System.out.println(myCustomer);
		System.out.println("--------------------------------------------------");

		// get accounts of a given customer
		System.out.println("get accounts of customer# 2");
		System.out.println("accounts of customer" + myCustomer);
		getAccountsOf(myCustomer);
		System.out.println("--------------------------------------------------");
		
		// transfer money, atomic method using transactions
		System.out.println("Money transfer");
		System.out.println("before transfer:");
		Account acc2 = getAccount("123-1235");
		Account acc1 = getAccount("123-1234");
		System.out.println("Account 1: " + acc1.getBalance() + " And Account 2: " + acc2.getBalance());
		System.out.println("After transfer of 20.00 euros from account1 to account2");
		transferMoney(acc1, acc2, 20.00);
		acc2 = getAccount("123-1235");
		acc1 = getAccount("123-1234");
		System.out.println("Account 1: " + acc1.getBalance() + " And Account 2: " + acc2.getBalance());
	}

	private static void getAccountsOf(Customer myCustomer) {
		List<Account> accounts = accountsRepository.getAccounts(myCustomer);
		for (Account accnt : accounts) {
			System.out.println(accnt);
		}
	}

	private static void getCustomers() {
		List<Customer> myListOfCustomers = customersRepository.findAllCustomers();
		for (Customer customer : myListOfCustomers) {
			System.out.println(customer);
		}
	}

	private static boolean transferMoney(Account originAccount, Account destinationAccount, double transferAmount) {
		return accountsRepository.transferMoney(originAccount, destinationAccount, transferAmount);
	}

	private static Account getAccount(String accountNumber) {
		return accountsRepository.getAccount(accountNumber);
	}

	private static Customer getCustomer(int id) {
		Customer myCustomer = customersRepository.getCustomer(id);
		return myCustomer;
	}
}