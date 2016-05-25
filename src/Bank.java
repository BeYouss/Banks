import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<Account> Accounts;
	private List<Customer> Customers;

	
	
	public Bank() {
		this.Accounts = new ArrayList<Account>();
		this.Customers = new ArrayList<Customer>();
	}

	public List<Customer> getCustomers() {
		return this.Customers;
	}

	public List<Account> getAccounts() {
		return this.Accounts;
	}

	public Customer getCustomer(String firstName, String lastName) {
		//lambda ???
//		Customer tmpCustomer  = Customers.stream()
//			     .filter(item -> item.getFirstName().equals(firstName))
//			     .filter(item -> item.getFirstName().equals(lastName)).findFirst().get();
//		
		//iteration
		Customer tmpCustomer = null;
		for (Customer customer : this.Customers) {
			if(customer.getFirstName().equals(firstName) 
					&& customer.getLastName().equals(lastName) ){
				tmpCustomer = customer;
			}
		
		}
		return tmpCustomer;
	}

	public Account getAccount(String accountNumber) {
		return null;
	}

	public boolean createClient(Customer customer) {
		return Customers.add(customer);
	}

	public boolean createClient(String firstName, String lastName) {
		return Customers.add(new Customer(firstName = firstName, lastName = lastName));
	}

	public boolean deleteClient(Customer client) {
		return Customers.remove(client);
	}

	public boolean deleteClient(String firstName, String lastName){
		Customer tmpCustomer = new Customer(firstName,  lastName);
		return Customers.remove(tmpCustomer);
	}

	public Account openAccount(Customer customer) {
		return new Account(customer);
	}

	public Account openAccount(Customer customer, double deposit) {
		return new Account(customer, deposit);
	}

	public Account openAccount(String firstName, String lastName) {
		return openAccount( new Customer(firstName, lastName));
	}

	public Account openAccount(String firstName, String lastName, double deposit) {
		return openAccount(new Customer(firstName, lastName), deposit);
	}

	public boolean closeAccount(String accountNumber) {
		return false;
	}

	public boolean closeAccount(Account account) {
		return false;
	}

	public String toString() {
		return "Bank: ";
	}
}
