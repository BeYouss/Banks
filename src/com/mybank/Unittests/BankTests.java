package com.mybank.Unittests;

import static org.junit.Assert.*;

import com.mybank.dao.AccountDao;
import com.mybank.dao.AccountDaoImpl;
import com.mybank.dao.CustomerDao;
import com.mybank.dao.CustomerDaoImpl;
import com.mybank.exceptions.InvalidAccountNumberFormatException;
import com.mybank.exceptions.NotEnoughFundsException;
import com.mybank.models.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankTests {

	CustomerDao customerDao;
	AccountDao accountDao;
	Customer customer = null;

	
	
	Account anotherAccount,account=null;

	@Before
	public void init(){
		 customerDao = new CustomerDaoImpl();
		 accountDao = new AccountDaoImpl();
	}
	
	@Test
	public void customerExist() {
		customer = customerDao.getCustomer(2);
		assertNotNull(customer);
	}
	
	@Test
	public void checkFirstAndLastName() {
		customer = customerDao.getCustomer(2);
		assertNotNull(!customer.getFirstName().isEmpty());
	}
	
	//Successful transfer
	@Test 
	public void successfulTransfer() {
		account = accountDao.getAccount("123-1234");
		anotherAccount = accountDao.getAccount("123-1235");
		boolean result = accountDao.transferMoney(account, anotherAccount, 1.00);
		assertTrue(result);
	}
	
	//NotEnoughFundsException
	@Test(expected = NotEnoughFundsException.class) 
	public void notEnoughMoneyToMakeATransfer() {
		account = accountDao.getAccount("123-1234");
		anotherAccount = accountDao.getAccount("122-1235");
		accountDao.transferMoney(account, anotherAccount, 99999.99);
	}
	
	//Invalid format exception
		@Test(expected = InvalidAccountNumberFormatException.class) 
		public void InvalidAccountFormat() {
			account = accountDao.getAccount("1");
			//assertNull(account);
		}
		
	
}
