import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankTests {

	Bank bank;
	Customer custOne;
	Customer custTwo;

	Account account;
	
	@Before
	public void init(){
		 bank = new Bank();
		 Customer custOne = new Customer("Joe", "Moe");
		 Customer custTwo = new Customer("Youssef", "Boubekri");
		bank.createClient(custOne);
		bank.createClient(custTwo);
		
	}
	
	@Test
	public void searchCustomerByFirstAndLastName() {

		 custOne = bank.getCustomer("Youssef", "Boubekri");
		assertNotNull(custOne);
	}
	
	@Test
	public void AccountbalanceShouldBeEqualsToZero(){
		custOne = bank.getCustomer("Youssef", "Boubekri");
		account = bank.openAccount(custOne);
		double balance = account.getAccountBalance();
		
		assertEquals(0, balance, balance-1);
		
	}

}
