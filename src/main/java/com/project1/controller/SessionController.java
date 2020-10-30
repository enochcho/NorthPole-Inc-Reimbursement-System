package com.project1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project1.model.User;


/*
 * sessions
 * 
 * 		server side user management
 * 				think of cookies but for the server. 
 */
public class SessionController {
	
	private static final Logger log = Logger.getLogger("SessionController");
	
	
	public void setSession(HttpServletRequest req, User user) {
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		log.info("Session user was set as " + user.getFirst());
	}
	
	public User getSessionUser(HttpServletRequest req) {
		return (User) req.getSession().getAttribute("user");
	}
	
	public void invalidate(HttpServletRequest req) {
		req.getSession().invalidate();
	}

}