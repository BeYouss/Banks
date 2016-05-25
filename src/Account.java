
public class Account {

	private Customer customer;
	private double balance;
	private String accountNumber;
	
	public Account(Customer customer) {
		this.customer = customer;
	}

	public Account(Customer customer, double deposit) {
		this.customer = customer;
		this.Credit(deposit);
	}
	

	public void Credit(double amount) {
		this.balance += amount;
	}

	public void Debit(double amount) {
		this.balance += amount;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public double getAccountBalance() {
		return this.balance;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public String toString() {
		return "Account: "+ this.getAccountNumber();
	}

}
