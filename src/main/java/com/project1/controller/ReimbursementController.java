package com.project1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.service.ReimbursementService;

public class ReimbursementController {
	private ReimbursementService rs;
	
	public ReimbursementController() {
		super();
		rs = new ReimbursementService();
	}

	/**
	 * used by financial managers
	 * @param resp
	 * Sends a json of all the reimbursements
	 */
	public void getAll(HttpServletResponse resp) {
		resp.setContentType("text/json");
		List<Reimbursement> reimbs = rs.findAll();
//		UserRole role = new UserRole(2);
//		ReimbursementType type = new ReimbursementType(1);
//		ReimbursementStatus status = new ReimbursementStatus(1);
//		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
//		User mrsclaus = new User(1,"mrsclas", "Mrs.", "Claus", "email", new UserRole(1));
//		Reimbursement r = new Reimbursement(0, 100, new Date(), null, "this is a test insert",null, santa, mrsclaus, status, type);
//		
//		List<Reimbursement> reimbs = new ArrayList<>();
//		reimbs.add(r);
		System.out.println(reimbs);
		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(reimbs));
		} catch(IOException e) {
			e.printStackTrace();			
		}
		
	}

	/**
	 * used by employees
	 * @param resp
	 * sends a json of an employee's reimbursements
	 */
	public void getEmData(HttpServletResponse resp) {
		resp.setContentType("text/json");
	}

	/**
	 * used by employees to add a reimbursement
	 * @param req
	 * @param resp
	 * sends a line confirming the addition
	 */
	public void add(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Reimbursement r = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
			int x = rs.submitRequest(r);
			if(x ==1) {
				resp.getWriter().println("The reimbursement was added");
			} else {
				resp.getWriter().println("The reimbursement was not added");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used by Financial Managers to approve/deny
	 * @param req
	 * @param resp
	 * sends a confirmation of approve or deny 
	 */
	public void approveDeny(HttpServletRequest req, HttpServletResponse resp) {
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(req.getInputStream());
			int approverId = jsonNode.get("approverId").asInt();
			int reimbId = jsonNode.get("reimbId").asInt();
			boolean approved = jsonNode.get("approved").asBoolean();
			int x = rs.approveDeny(approverId, reimbId, approved);
			if(x == 1) {
				resp.getWriter().println("The reimbursement was approved/denied");
			} else {
				resp.getWriter().println("The reimbursement was not approved/denied");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	

}
