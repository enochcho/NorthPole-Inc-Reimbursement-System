package com.project1.service;

import org.apache.log4j.Logger;

import com.project1.model.User;
import com.project1.repo.UserDao;

public class UserService {
	
	private static final Logger log = Logger.getLogger("UserService");
	private UserDao ud;
	
	public UserService(UserDao ud) {
		this.ud = ud;
	}
	
	public UserService() {
		this(new UserDao());
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
			log.info("Failed a login attempt");
			return null;
		} else {
			return ud.findById(id);
		}
	}
	
	/**
	 * 
	 * @param user
	 * @return 1 if the registration was successful, or 0 . 
	 */
	public int register(User user) {
		log.info("Created a new user");
		return ud.create(user);
	}

}
