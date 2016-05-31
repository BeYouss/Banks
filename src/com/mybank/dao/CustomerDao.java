package com.mybank.dao;

import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.Customer;

public interface CustomerDao {
	public List<Customer> findAllCustomers();

	public List<Account> getAccount(Customer customer);

	public Customer getCustomer(int id);
}
