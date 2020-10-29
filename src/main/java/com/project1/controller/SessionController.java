package com.project1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.project1.model.User;


/*
 * sessions
 * 
 * 		server side user management
 * 				think of cookies but for the server. 
 */
public class SessionController {
	
	public void setSession(HttpServletRequest req, User user) {
		HttpSession session = req.getSession();
		System.out.println("This got here");
		session.setAttribute("user", user);
	}
	
	public User getSessionUser(HttpServletRequest req) {
		return (User) req.getSession().getAttribute("user");
	}
	
	public void invalidate(HttpServletRequest req) {
		req.getSession().invalidate();
	}

}