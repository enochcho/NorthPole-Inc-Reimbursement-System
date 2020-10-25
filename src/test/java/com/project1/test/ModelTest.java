package com.project1.test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.project1.model.Reimbursement;
import com.project1.model.ReimbursementStatus;
import com.project1.model.ReimbursementType;
import com.project1.model.User;
import com.project1.model.UserRole;

public class ModelTest {
	
	private User user;
	private Reimbursement reimb;
	private ReimbursementStatus status;
	private ReimbursementType type;
	private UserRole role;
	@Before
	public void setup() {
		role = new UserRole(1);
		user = new User(1, "santa", "Santa", "Claus", "santa.clause@northpole.net", role);
		status = new ReimbursementStatus(1);
		type = new ReimbursementType(3);
		Timestamp ts = Timestamp.from(Instant.now());
		reimb = new Reimbursement(1, 100, ts, "milk and cookies",null, user, status, type);
				
	}

	@Test
	public void testInvalidStatus() {
		assertFalse(status.setStatusId(5));
	}
	
	@Test
	public void testValidStatus() {
		assertTrue(status.setStatusId(2));
	}
	
	@Test
	public void testInvalidType() {
		assertFalse(type.setTypeId(100));
	}
	
	@Test
	public void testValidType() {
		assertTrue(type.setTypeId(1));
	}
	
	@Test
	public void testReimbursement() {
		assertEquals(reimb.getAmount(), 100, 0);
	}

}
