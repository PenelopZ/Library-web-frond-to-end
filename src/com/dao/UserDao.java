package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
	final String URL = "jdbc:oracle:thin:@dataserv.mscs.mu.edu:1521:orcl";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rst = null;
	static String cardNumber =""; 

	public String login(String cardNo, String password) throws SQLException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load the driver" + e.getMessage());
		}

		String user = "zhangs";
		String pwd = "006122685";

		try {
			conn = DriverManager.getConnection(URL, user, pwd);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Database connect fail");
			System.exit(0);
		}
		
		System.out.println(cardNo);
		String query = "select name, password from BORROWER " + "where cardNo = '" + cardNo +"'";

		rst = stmt.executeQuery(query);

		if (rst.next()) {
			if (rst.getString("password").equals(password)){
				cardNumber = cardNo;
				return rst.getString("name");
			}
			else {
				System.out.println("password is wrong");
				return null;
			}
			
		} else {
			System.out.println("Do not find user");
			return null;
		}
	}
	
	
	public String newUser(String name, String address, String phone, String password) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load the driver" + e.getMessage());
		}

		String user = "zhangs";
		String pwd = "006122685";

		try {
			conn = DriverManager.getConnection(URL, user, pwd);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Database connect fail");
			System.exit(0);
		}
		
		String insert = "INSERT INTO BORROWER VALUES (borrower_seq.nextval,'" + name + "','" + address + "','"
				+ phone+ "','" + password+"')";
		String query = "Select cardno from borrower where name = '" + name + "'";
		try {
			stmt.executeUpdate(insert);
			rst = stmt.executeQuery(query);
			System.out.println("Insert Success.");
			rst.next();
			return rst.getString("cardno");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert fail.");
			return null;
		}
	}
	 
	public boolean checkOut(int bookId, int branchId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load the driver" + e.getMessage());
		}

		String user = "zhangs";
		String pwd = "006122685";

		try {
			conn = DriverManager.getConnection(URL, user, pwd);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Database connect fail");
			System.exit(0);
		}
		String insert = "insert into book_loans values('" + bookId + "','" + branchId + "','" + cardNumber
				+ "'," + "sysdate,add_months(sysdate,1),null, null)";
		System.out.println(cardNumber + " " + bookId + " " +branchId);
		try {
			stmt.executeUpdate(insert);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

}
