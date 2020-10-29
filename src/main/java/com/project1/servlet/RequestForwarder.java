package com.project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project1.controller.ReimbursementController;
import com.project1.controller.UserController;

public class RequestForwarder {

	public void routes (HttpServletRequest req, HttpServletResponse resp) {
		try {
			switch(req.getRequestURI()) {
			case "/Project1/login.page":
				System.out.println("got to log in");
				new UserController().login(req,resp);
				break;
			case "/Project1/logout.page":
				System.out.println("got to log out");
				new UserController().logout(req,resp);
				break;
			default:
				req.getRequestDispatcher("html/index.html").forward(req, resp);
			}	
		}catch(ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	public void data(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
		case "/Project1/empl.json":
			new ReimbursementController().getEmData(req,resp);
			break;
		case "/Project1/all.json":
			System.out.println("all.json");
			new ReimbursementController().getAll(resp);
			break;
		case "/Project1/add.json":
			System.out.println("add.json");
			new ReimbursementController().add(req,resp);
			break;
		case "/Project1/aprvdeny.json":
			System.out.println("aprvdeny.json");
			new ReimbursementController().approveDeny(req, resp);
			break;
		case "/Project1/register.json":
			System.out.println("register.json");
			new UserController().register(req,resp);
		}
	}
}
