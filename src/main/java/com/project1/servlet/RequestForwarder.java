package com.project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestForwarder {

	public void routes (HttpServletRequest req, HttpServletResponse resp) {
		try {
			switch(req.getRequestURI()) {
			case "/Project1/test.page":
				req.getRequestDispatcher("/Project1/test.html").forward(req, resp);
				break;
			default:
				req.getRequestDispatcher("/Project1/index.html").forward(req, resp);
			}	
		}catch(ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
