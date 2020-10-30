package com.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.UserService;

public class UserController {
	
	private static final Logger log = Logger.getLogger("UserController");
	private UserService us;
	private SessionController sc;
	
	public UserController(UserService us, SessionController sc) {
		this.us = us;
		this.sc = sc;
	}
	public UserController() {
		this(new UserService(), new SessionController());
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			User x = us.login(username, password);
			System.out.println(username);
			System.out.println(password);
			if(x != null) {
				sc.setSession(req, x);
				System.out.println("In the user controller " + sc.getSessionUser(req).getUsername());
				if(x.getRole().getRoleId() == 1) {
					log.info(username + " logged in as a manager");
					req.getRequestDispatcher("html/manager.html").forward(req, resp);
				} else {
					log.info(username + " logged in as an employee");
					req.getRequestDispatcher("html/employee.html").forward(req, resp);
				}
			} else {
				log.info(username + " attempted to login but failed");
				req.getRequestDispatcher("html/index.html").forward(req, resp);
			}
		}catch (IOException | ServletException e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}
	
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		sc.invalidate(req);
		log.info("The session was invalidated");
		try {
			req.getRequestDispatcher("html/index.html").forward(req, resp);
		} catch (ServletException | IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}
	public void register(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/json");
		JsonNode jsonNode;
		try {
			User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
			int x = us.register(user);
			if(x == 1) {
				log.info(user.getFirst() + " " + user.getLast() + " was successfully registered");
				resp.getWriter().println(new ObjectMapper().writeValueAsString("You registered successfully! Please login"));
			} else {
				log.info(user.getFirst() + " " + user.getLast() + "'s registration failed");
				resp.getWriter().println(new ObjectMapper().writeValueAsString("Your registration was unsuccessful, please try again"));
			}
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}
	

}
