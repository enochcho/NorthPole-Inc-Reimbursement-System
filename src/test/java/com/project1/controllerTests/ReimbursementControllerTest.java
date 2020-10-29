package com.project1.controllerTests;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.project1.controller.ReimbursementController;

public class ReimbursementControllerTest {
	
	private ReimbursementController rc;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;
	
	@Before
	public void setup() {
		rc = new ReimbursementController();
		req = new MockHttpServletRequest();
		req.setRequestURI("/add.json");
		resp = new MockHttpServletResponse();
	}
	
	
	@Test
	public void testGetEmData() {
		
	}
	
	@Test
	public void testGetAllTest() {
		
//		rc.getAll(resp);
//		assertEqual(resp.getContentAsString())
	}
	
	@Test
	public void testApproveDeny() {
		
	}
	
	

}
