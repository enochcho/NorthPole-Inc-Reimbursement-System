package com.project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestForwarder {

	public void routes (HttpServletRequest req, HttpServletResponse resp) {
		try {
			switch(req.getRequestURI()) {
			case "/Project1-0.0.1-SNAPSHOT/test.page":
				req.getRequestDispatcher("html/test.html").forward(req, resp);
				break;
			default:
				req.getRequestDispatcher("html/index.html").forward(req, resp);
			}	
		}catch(ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
