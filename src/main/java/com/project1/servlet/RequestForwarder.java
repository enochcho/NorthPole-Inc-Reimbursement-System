package com.project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project1.controller.ReimbursementController;

public class RequestForwarder {

	public void routes (HttpServletRequest req, HttpServletResponse resp) {
		try {
			switch(req.getRequestURI()) {
			case "/Project1/test.page":
				req.getRequestDispatcher("html/test.html").forward(req, resp);
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
			new ReimbursementController().getEmData(resp);
			break;
		case "/Project1/all.json":
			System.out.println("all.json");
			new ReimbursementController().getAll(resp);
			break;
		case "/Project1/add.json":
			new ReimbursementController().add(req,resp);
			break;
		case "/Project1/aprvdeny.json":
			new ReimbursementController().add(req,resp);
			break;
		}
	}
}
