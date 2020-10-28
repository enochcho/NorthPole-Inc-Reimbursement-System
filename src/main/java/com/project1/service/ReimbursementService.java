package com.project1.service;

import java.util.List;

import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.repo.ReimbursementDao;
import com.project1.repo.UserDao;

public class ReimbursementService {
	
	private ReimbursementDao rd;
	private UserDao ud;
	
	public ReimbursementService() {
		super();
		this.rd = new ReimbursementDao();
		this.ud = new UserDao();
	}
	
	//
	public List<Reimbursement> viewMyTickets(int userId) {
		return rd.getReimbursementByEmployee(userId);
	}

	public int submitRequest(Reimbursement r) {
		return rd.create(r);
	}

	public int approveDeny(int approverId, int reimbId, boolean approved) {
		//grab the Financial Manager user and the reimbursement itself
		User finMan = ud.findById(approverId);
		Reimbursement toChange = rd.findById(reimbId);
		//set the resolver to the financial manager
		toChange.setResolver(finMan);
		//set the status type depending on the boolean: 2 is approved, 3 is denied.
		int status = approved ? 2:3;
		System.out.println(status);
		toChange.getStatus().setStatusId(status);
		//resolve time will be added in the db
		return rd.updateStatus(toChange);
	}
	
	public List<Reimbursement> findAll(){
		return rd.findAll();
	}
	

}
