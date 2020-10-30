package com.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.User;
import com.project1.model.UserRole;
import com.project1.service.UserService;

public class UserController {
	
	private static final Logger log = Logger.getLogger("UserController");
	private UserService us;
	private SessionController sc;
	private ObjectMapper om;
	
	public UserController(UserService us, SessionController sc, ObjectMapper om) {
		this.us = us;
		this.sc = sc;
		this.om= om;
	}
	public UserController() {
		this(new UserService(), new SessionController(), new ObjectMapper());
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			User x = us.login(username, password);
		
			if(x != null) {
				sc.setSession(req, x);
				log.info("In the user controller " + sc.getSessionUser(req).getUsername());
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
		try {
			
			JsonNode inf = om.readTree(req.getInputStream());
			int roleId = inf.get("roleId").asInt();
			String password = inf.get("password").asText();
			String username = inf.get("username").asText();
			String first = inf.get("first").asText();
			String last = inf.get("last").asText();
			String email = inf.get("email").asText();
			User user = new User(0,username, first, last, email, new UserRole(roleId));
			int x = us.register(user, password);
			
			if(x == 1) {
				log.info(user.getFirst() + " " + user.getLast() + " was successfully registered");
				JsonNode node = om.readTree("{\"message\":\"You registered successfully! Please login.\"}");
				resp.getWriter().println(node);
				
			} else {
				log.info(user.getFirst() + " " + user.getLast() + "'s registration failed");
				JsonNode node = om.readTree("{\"message\":\"Your registration was unsuccessful, please try again.\"}");
				resp.getWriter().println(node);
			}
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}
	

}
