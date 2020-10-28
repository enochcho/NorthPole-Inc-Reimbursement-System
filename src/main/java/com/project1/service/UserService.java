package com.project1.service;

import com.project1.repo.UserDao;

public class UserService {
	
	private UserDao ud;
	
	public UserService() {
		super();
		this.ud = new UserDao();
	}
	
	
	//hash password in database
	//returns role_id: 1 if Financial Manager, 2 if Employee OR returns 0 if login failed. 
	public int login(String username, String password) {
		return ud.login(username, password);
	}

}
