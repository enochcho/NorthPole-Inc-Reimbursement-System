package com.project1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.service.ReimbursementService;

public class ReimbursementController {
	private ReimbursementService rs;
	
	public ReimbursementController() {
		super();
		rs = new ReimbursementService();
	}

	public void getAll(HttpServletResponse resp) {
		resp.setContentType("text/json");
		
	}

	public void getEmData(HttpServletResponse resp) {
		resp.setContentType("text/json");
		List<Reimbursement> reimbs = rs.findAll();
		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(reimbs));
		} catch(IOException e) {
			e.printStackTrace();			
		}
	}

}
