package com.project1.service;

import com.project1.model.User;
import com.project1.repo.UserDao;

public class UserService {
	
	private UserDao ud;
	
	public UserService() {
		super();
		this.ud = new UserDao();
	}
	
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return the user if login was successful, or null. 
	 */
	public User login(String username, String password) {
		int id = ud.login(username, password);
		if( id == 0) {
			return null;
		} else {
			return ud.findById(id);
		}
	}
	
	/**
	 * 
	 * @param user
	 * @return 1 if the registration was successul, or 0 . 
	 */
	public int register(User user) {
		return ud.create(user);
	}

}
