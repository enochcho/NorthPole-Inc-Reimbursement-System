package com.project1.serviceTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.project1.service.UserService;

public class UserServiceTest {
	
	private UserService us;
	
	@Before
	public void setup() {
		us = new UserService();
	}
	
// Possibly create a register method if there is time. 
//	@Test
//	public void testRegister() {
//		fail("Not yet implemented");
//	}
	
/**
 * For login tests
 * 		Check with a select statement to see if the username and password matches
 * 		Return the role_id if it matches, otherwise return 0.
 * 			-- role_id: 1 is Financial Manager, 2 is Employee
 * 		create a session with the username and role_id. 
 */
	@Test
	public void testLoginSuccess() {
		String username = "santa";
		String password = "christmas";
		assertEquals(2,us.login(username, password),0);
	}
	
	@Test
	public void testLoginSucess2() {
		String username = "Mrs.Claus";
		String password = "christmas";
		assertEquals(1,us.login(username, password),0);
	}
	
	@Test 
	public void testLoginFail() {
		String username = "Dan";
		String password = "Hacker";
		assertEquals(0,us.login(username, password),0);
	}
	
}
