package com.project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.project1.controller.ReimbursementController;
import com.project1.controller.UserController;

public class RequestForwarder {
	private static final Logger log = Logger.getLogger("routes");

	public void routes (HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			switch(req.getRequestURI()) {
			case "/Project1/login.page":
				log.info("/login.page was requested");
				new UserController().login(req,resp);
				break;
			case "/Project1/logout.page":
				log.info("/logout.page was requested");
				new UserController().logout(req,resp);
				break;
			default:
				log.warn("The request fell through because it didn't match login or logout");
				req.getRequestDispatcher("html/index.html").forward(req, resp);
			}	
		}catch(ServletException | IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}

	public void data(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
		case "/Project1/empl.json":
			log.info("/empl.json was requested");
			new ReimbursementController().getEmData(req,resp);
			break;
		case "/Project1/all.json":
			log.info("/all.json was requested");
			new ReimbursementController().getAll(resp);
			break;
		case "/Project1/add.json":
			log.info("/add.json was requested");
			new ReimbursementController().add(req,resp);
			break;
		case "/Project1/aprvdeny.json":
			log.info("/aprvdeny.json was requested");
			new ReimbursementController().approveDeny(req, resp);
			break;
		case "/Project1/register.json":
			log.info("/register.json was requested");
			new UserController().register(req,resp);
		}
	}
}
