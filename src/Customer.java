import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String firstName;
	private String lastName;
	private List<Account> Accounts;

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.Accounts = new ArrayList<>();
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFullName() {
		return this.getFirstName() + ", " + this.getLastName();
	}

	public List<Account> getAccounts() {
		return this.Accounts;
	}

	public String toString() {
		return "Customer: " + this.getFullName();
	}
}
