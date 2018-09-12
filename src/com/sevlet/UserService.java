package com.sevlet;

import java.sql.SQLException;

import com.dao.UserDao;

public class UserService {
	
	public String loginService(String cardNo, String password) throws SQLException{
		UserDao userDao = new UserDao();
		return userDao.login(cardNo, password);
	}
	public String insertBorrower(String name, String address, String phone, String password) throws SQLException{
		UserDao userDao = new UserDao();
		return userDao.newUser(name, address, phone, password);
	}
	public boolean checkOutBook(int bookId, int branchId){
		UserDao userDao = new UserDao();
		return userDao.checkOut(bookId, branchId);
	}
}
