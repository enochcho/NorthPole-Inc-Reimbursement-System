package com.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.UserService;

public class UserController {
	
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
					req.getRequestDispatcher("html/manager.html").forward(req, resp);
				} else {
					req.getRequestDispatcher("html/employee.html").forward(req, resp);
				}
			} else {
				req.getRequestDispatcher("html/index.html").forward(req, resp);
			}
		}catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		sc.invalidate(req);
		try {
			req.getRequestDispatcher("html/index.html").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
				resp.getWriter().println(new ObjectMapper().writeValueAsString("You registered successfully! Please login"));
			} else {
				resp.getWriter().println(new ObjectMapper().writeValueAsString("Your registration was unsuccessful, please try again"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
