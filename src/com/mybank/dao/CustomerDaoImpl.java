package com.mybank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.Customer;

public class CustomerDaoImpl implements CustomerDao {
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

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();

		String query = "select * from Customer";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Customer cust = new Customer();
				cust.setFirstName(res.getString("FirstName"));
				cust.setLastName(res.getString("LastName"));
				cust.setId(res.getInt("idCustomer"));
				customers.add(cust);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return customers;
	}

	@Override
	public List<Account> getAccount(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(int id) {
		String query = "select * from Customer where idCustomer = ?";
		Connection connection = null;

		Customer cust=null;;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			cust = new Customer();
			cust.setFirstName(res.getString("FirstName"));
			cust.setLastName(res.getString("LastName"));
			cust.setId(res.getInt("idCustomer"));
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return cust;
	}
}
