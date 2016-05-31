package com.mybank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mybank.exceptions.InvalidAccountNumberFormatException;
import com.mybank.exceptions.NotEnoughFundsException;
import com.mybank.models.Account;
import com.mybank.models.Customer;

public class AccountDaoImpl implements AccountDao {
	static {
		try {
			Class.forName("com.mysql.jdbc.driver");
		} catch (ClassNotFoundException ex) {
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/humanbooster?autoReconnect=true&useSSL=false",
				"root", "root");
	}

	private void closeConnection(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		} catch (SQLException ex) {
		}

	}

	public Account getAccount(String accountNumber) {
		if (!accountNumber.matches("^[0-9]{3}-[0-9]{4}$")) {
			throw new InvalidAccountNumberFormatException("Invalid account format");
			//return null;
		}
		String query = "select * from Account where AccountNumber=?";
		Account account = null;
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, accountNumber);
			ResultSet res = statement.executeQuery();
			res.next();
			account = new Account();
			account.setAccountNumber(res.getString("AccountNumber"));
			account.setBalance(res.getDouble("Balance"));
		} catch (

		SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return account;
	}

	@Override
	public List<Account> findAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();

		String query = "select * from Account";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Account cust = new Account();
				cust.setAccountNumber(res.getString("AccountNumber"));
				cust.setBalance(res.getDouble("Balance"));
				accounts.add(cust);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return accounts;
	}

	@Override
	public List<Account> getAccounts(Customer customer) {
		List<Account> accounts = new ArrayList<Account>();

		String query = "select * from Account where idCustomer=?";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customer.getId());
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Account cust = new Account();
				cust.setAccountNumber(res.getString("AccountNumber"));
				cust.setBalance(res.getDouble("Balance"));
				accounts.add(cust);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return accounts;
	}

	public boolean transferMoney(Account originAccount, Account destinationAccount, double transferAmount) {
		/*
		 * TODO:
		 * Check for sufficient funds before transfer
		 */
		
		String queryCredit = "UPDATE Account set Balance = (Balance + ?)  where AccountNumber=?";
		String queryDebit = "UPDATE Account set Balance = (Balance - ?)  where AccountNumber=?";
		boolean success = false;
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			String checkCredit = "SELECT balance from Account where AccountNumber = ?";
			PreparedStatement statementcheckCredit = connection.prepareStatement(checkCredit);
			statementcheckCredit.setString(1, originAccount.getAccountNumber());
			ResultSet myResult = statementcheckCredit.executeQuery();
			myResult.next();
			if(myResult.getDouble("Balance") < transferAmount){
				connection.rollback();
				String message = "You do not have "+ String.valueOf(transferAmount);
				throw new NotEnoughFundsException(message);
			}
			
			
			PreparedStatement statementDebit = connection.prepareStatement(queryDebit);
			statementDebit.setDouble(1, transferAmount);
			statementDebit.setString(2, originAccount.getAccountNumber());
			statementDebit.executeUpdate();

			PreparedStatement statementCredit = connection.prepareStatement(queryCredit);
			statementCredit.setDouble(1, transferAmount);
			statementCredit.setString(2, destinationAccount.getAccountNumber());
			statementCredit.executeUpdate();
			connection.commit();
			success = true;
		} catch (SQLException ex) {
			success = false;
			System.out.println(ex.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

}
