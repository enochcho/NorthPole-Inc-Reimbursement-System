package com.project1.serviceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.project1.model.Reimbursement;
import com.project1.model.ReimbursementStatus;
import com.project1.model.ReimbursementType;
import com.project1.model.User;
import com.project1.model.UserRole;
import com.project1.repo.ReimbursementDao;
import com.project1.service.ReimbursementService;

public class ReimbursementServiceTest {
	
	private ReimbursementService rs;
	private ReimbursementDao rd;
	@Before
	public void setup() {
		rd = mock(ReimbursementDao.class);
		rs = new ReimbursementService();
		
	}
	
	/**
	 * This tests the number of reimbursements returned, make sure its not 0
	 */
	@Test 
	public void testViewMyTickets() {
		assertNotEquals(rs.viewMyTickets(2).size(), 0);
	}
	
	/**
	 * This tests the number of reimbursements returned
	 * this user id doesn't exist so it should return 0. 
	 */
	@Test 
	public void testViewMyTickets2() {
		assertEquals(rs.viewMyTickets(0).size(), 0,0);
	}
	
	/**
	 * This method returns either a 0, or the id of the reimbursement
	 * that was just created. 
	 */
//	@Test
//	public void testSubmitRequest() {
////		UserRole role = new UserRole(2);
////		ReimbursementType type = new ReimbursementType(1);
////		ReimbursementStatus status = new ReimbursementStatus(1);
////		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
////		Reimbursement r = new Reimbursement(0, 100, new Date(), "this is a test insert", null, santa, status, type);
////		when(rd.create((Reimbursement) any(Reimbursement.class))).thenAnswer(i -> {
////			return 1;
////		});
////		
////		assertEquals(1,rd.create(r));
//		
//		//assertNotEquals(rs.submitRequest(r), 0, 0);
//	}
	
	/**
	 * A financial manager can approve or deny a request
	 * returns number of rows update, should be 1. 
	 */
	@Test
	public void testApproveDeny() {
		int approverId = 1;
		int reimbId = 4;
		assertEquals(rs.approveDeny(approverId, reimbId, true), 1, 0);
	}
	
	/**
	 * A financial manager can get back all the requests in the reimbursement table
	 * should return a list that isn't empty
	 */
	@Test
	public void testFindAll() {
		assertNotEquals(rs.findAll().size(), 0);
	}
	

}
