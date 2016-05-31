package com.mybank.dao;

import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.Customer;

public interface AccountDao {
	public List<Account> findAllAccounts();

	public List<Account> getAccounts(Customer customer);

	public Account getAccount(String accountNumber);

	public boolean transferMoney(Account originAccount, Account destinationAccount, double transferAmount);
}