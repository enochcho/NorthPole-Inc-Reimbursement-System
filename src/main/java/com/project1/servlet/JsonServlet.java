package com.project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet (name = "jsonServlet", urlPatterns = {"*.json"})
public class JsonServlet extends HttpServlet{
	private static final Logger log = Logger.getLogger("JsonServlet");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("A GET request was made to the JsonServlet");
		new RequestForwarder().data(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("A POST request was made to the JsonServlet");
		new RequestForwarder().data(req,resp);
	}

	

}
