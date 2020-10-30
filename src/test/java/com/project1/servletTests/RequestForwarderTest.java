package com.project1.servletTests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.project1.controller.ReimbursementController;
import com.project1.controller.UserController;
import com.project1.servlet.RequestForwarder;

public class RequestForwarderTest {
	
	private UserController ucMock;
	private ReimbursementController rcMock;
	
	@Before
	public void setup(){
		ucMock = mock(UserController.class);
		rcMock = mock(ReimbursementController.class);
		
	}
	
	@Test
	public void testConstructor() {
		new RequestForwarder(ucMock,rcMock);
		assertTrue(true);
	}
	
	@Test
	public void testRoutesLogin() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.setRequestURI("/Project1/login.page");
		new RequestForwarder(ucMock,rcMock).routes(reqmm, resmm);
		verify(ucMock).login(reqmm,resmm);
		assertTrue(true);
	}
	
	@Test
	public void testRoutesLogout() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.setRequestURI("/Project1/logout.page");
		new RequestForwarder(ucMock,rcMock).routes(reqmm, resmm);
		verify(ucMock).logout(reqmm,resmm);
		assertTrue(true);
	}
	@Test
	public void testRoutesdefault() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.setRequestURI("/Project1/log.page");
		new RequestForwarder(ucMock,rcMock).routes(reqmm, resmm);
		assertTrue(true);
	}
	
	@Test
	public void testDataEmpl() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.setRequestURI("/Project1/empl.json");
		new RequestForwarder(ucMock,rcMock).data(reqmm, resmm);
		verify(rcMock).getEmData(reqmm,resmm);
		assertTrue(true);
	}
	@Test
	public void testDataAll() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.setRequestURI("/Project1/all.json");
		new RequestForwarder(ucMock,rcMock).data(reqmm, resmm);
		verify(rcMock).getAll(resmm);
		assertTrue(true);
	}
	
	@Test
	public void testDataAdd() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.setRequestURI("/Project1/add.json");
		new RequestForwarder(ucMock,rcMock).data(reqmm, resmm);
		verify(rcMock).add(reqmm,resmm);
		assertTrue(true);
	}
	
	@Test
	public void testDataAprvDeny() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.setRequestURI("/Project1/aprvdeny.json");
		new RequestForwarder(ucMock,rcMock).data(reqmm, resmm);
		verify(rcMock).approveDeny(reqmm,resmm);
		assertTrue(true);
	}
	@Test
	public void testDataRegister() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.setRequestURI("/Project1/register.json");
		new RequestForwarder(ucMock,rcMock).data(reqmm, resmm);
		verify(ucMock).register(reqmm,resmm);
		assertTrue(true);
	}

}
