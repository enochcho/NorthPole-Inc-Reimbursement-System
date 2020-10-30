package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.ReimbursementService;

public class ReimbursementController {
	private ReimbursementService rs;
	private SessionController sc;
	private ObjectMapper om;
	final static Logger log = Logger.getLogger("ReimbursementController");
	
	public ReimbursementController(ReimbursementService rs, SessionController sc, ObjectMapper om) {
		this.rs = rs;
		this.sc = sc;
		this.om = om;
	}
	
	public ReimbursementController() {
		this(new ReimbursementService(), new SessionController(), new ObjectMapper());
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
		try {
			log.info("retrieving all reimbursements");
			resp.getWriter().println(om.writeValueAsString(reimbs));
		} catch(IOException e) {
			log.error(e);
			e.printStackTrace();			
		}
		
	}

	/**
	 * used by employees
	 * @param req 
	 * @param resp
	 * sends a json of an employee's reimbursements
	 */
	public void getEmData(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/json");
		List<Reimbursement> reimbs = rs.viewMyTickets(sc.getSessionUser(req).getUserId());
		try {
			log.info("Retrieved all of an employee's reimbursements");
			resp.getWriter().println(om.writeValueAsString(reimbs));
		} catch(IOException e) {
			log.error(e);
			e.printStackTrace();			
		}
	}

	/**
	 * used by employees to add a reimbursement
	 * @param req
	 * @param resp
	 * sends a line confirming the addition
	 */
	public void add(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Reimbursement r = om.readValue(req.getInputStream(), Reimbursement.class);
			int x = rs.submitRequest(r,sc.getSessionUser(req).getUserId());
			if(x ==1) {
				log.info("A reimbursement was added");
				resp.getWriter().println(om.writeValueAsString("The reimbursement was added"));
			} else {
				log.info("Something went wrong with the values and a reimbursement was not added.");
				resp.getWriter().println(om.writeValueAsString("The reimbursement was NOT added"));
			}
		}catch(IOException e) {
			log.error(e);
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
			System.out.println("In the reimbursement controller " + sc.getSessionUser(req).getUsername());
			resp.setContentType("text/json");
			JsonNode jsonNode = om.readTree(req.getInputStream());
			int reimbId = jsonNode.get("reimbId").asInt();
			boolean approved = jsonNode.get("approved").asBoolean();
			User approver = sc.getSessionUser(req);
			int x = rs.approveDeny(approver.getUserId(), reimbId, approved);
			if(x == 1) {
				log.info("the reimbursement was updated");
				resp.getWriter().println(om.writeValueAsString("The reimbursement was approved/denied"));
			} else {
				log.info("something was wrong with the reimbursement and it wasn't updated");
				resp.getWriter().println(om.writeValueAsString("The reimbursement wasn't approved or denied"));
			}
		}catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	
	

	

}
