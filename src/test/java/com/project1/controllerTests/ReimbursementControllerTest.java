package com.project1.controllerTests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.controller.ReimbursementController;
import com.project1.controller.SessionController;
import com.project1.model.Reimbursement;
import com.project1.model.ReimbursementStatus;
import com.project1.model.ReimbursementType;
import com.project1.model.User;
import com.project1.model.UserRole;
import com.project1.service.ReimbursementService;

public class ReimbursementControllerTest {
	
	private ReimbursementController rc;
//	private MockHttpServletRequest req;
//	private MockHttpServletResponse resp;
	private ReimbursementService rsMock;
	private SessionController scMock;
	private PrintWriter writerMock;
	private HttpServletRequest reqMock;
	private HttpServletResponse respMock;
	private ObjectMapper objMock;
	
	
	@Before
	public void setup() {
		rsMock = mock(ReimbursementService.class);
		scMock = mock(SessionController.class);
		objMock = mock(ObjectMapper.class);
		rc = new ReimbursementController(rsMock, scMock, objMock);
		reqMock = mock(HttpServletRequest.class);
		respMock = mock(HttpServletResponse.class);
//		req = new MockHttpServletRequest();
//		resp = new MockHttpServletResponse();
		
	}

	@Test
	public void testGetAllTest() throws IOException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		UserRole role = new UserRole(2);
		ReimbursementType type = new ReimbursementType(1);
		ReimbursementStatus status = new ReimbursementStatus(1);
		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
		User mrsclaus = new User(1,"mrsclas", "Mrs.", "Claus", "email", new UserRole(1));
		Reimbursement r = new Reimbursement(0, 100, new Date(), null, "this is a test insert",null, santa, mrsclaus, status, type);
		List<Reimbursement> reimbs = new ArrayList<>();
		reimbs.add(r);
		when(rsMock.findAll()).thenReturn(reimbs);
		when(respMock.getWriter()).thenReturn(writer);
		when(objMock.writeValueAsString(reimbs)).thenReturn("hello");
		rc.getAll(respMock);
		verify(respMock).setContentType("text/json");
		verify(rsMock).findAll();
		writer.flush();
		assertTrue(stringWriter.toString().contains("hello"));
	}
	
	@Test
	public void testGetEmData() throws IOException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		UserRole role = new UserRole(2);
		ReimbursementType type = new ReimbursementType(1);
		ReimbursementStatus status = new ReimbursementStatus(1);
		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
		User mrsclaus = new User(1,"mrsclas", "Mrs.", "Claus", "email", new UserRole(1));
		Reimbursement r = new Reimbursement(0, 100, new Date(), null, "this is a test insert",null, santa, mrsclaus, status, type);
		List<Reimbursement> reimbs = new ArrayList<>();
		reimbs.add(r);
		when(scMock.getSessionUser(reqMock)).thenReturn(santa);
		when(rsMock.viewMyTickets(santa.getUserId())).thenReturn(reimbs);
		when(respMock.getWriter()).thenReturn(writer);
		when(objMock.writeValueAsString(reimbs)).thenReturn("hello");
		rc.getEmData(reqMock, respMock);
		writer.flush();
		assertTrue(stringWriter.toString().contains("hello"));
	}
	
	/**
	 * This tests for when the reimbursement is added. 
	 * @throws IOException
	 */
	@Test
	public void testAdd1() throws IOException{
		UserRole role = new UserRole(2);
		ReimbursementType type = new ReimbursementType(1);
		ReimbursementStatus status = new ReimbursementStatus(1);
		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
		User mrsclaus = new User(1,"mrsclas", "Mrs.", "Claus", "email", new UserRole(1));
		Reimbursement r = new Reimbursement(0, 100, new Date(), null, "this is a test insert",null, santa, mrsclaus, status, type);
		List<Reimbursement> reimbs = new ArrayList<>();
		
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		
		when(objMock.readValue(reqMock.getInputStream(), Reimbursement.class)).thenReturn(r);
		when(scMock.getSessionUser(reqMock)).thenReturn(santa);
		when(rsMock.submitRequest(r, santa.getUserId())).thenReturn(1);
		when(respMock.getWriter()).thenReturn(writer);
		rc.add(reqMock,respMock);
		writer.flush();
		assertTrue(stringWriter.toString().contains("null"));
	}
	
	@Test
	public void testAdd2() throws IOException{
		UserRole role = new UserRole(2);
		ReimbursementType type = new ReimbursementType(1);
		ReimbursementStatus status = new ReimbursementStatus(1);
		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
		User mrsclaus = new User(1,"mrsclas", "Mrs.", "Claus", "email", new UserRole(1));
		Reimbursement r = new Reimbursement(0, 100, new Date(), null, "this is a test insert",null, santa, mrsclaus, status, type);
		List<Reimbursement> reimbs = new ArrayList<>();
		
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		
		when(objMock.readValue(reqMock.getInputStream(), Reimbursement.class)).thenReturn(r);
		when(scMock.getSessionUser(reqMock)).thenReturn(santa);
		when(rsMock.submitRequest(r, santa.getUserId())).thenReturn(2);
		when(respMock.getWriter()).thenReturn(writer);
		rc.add(reqMock,respMock);
		writer.flush();
		assertTrue(stringWriter.toString().contains("null"));
	}
	
	
	@Test
	public void testApproveDeny() throws IOException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		UserRole role = new UserRole(2);
		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
		JsonNode jnodeMock = mock(JsonNode.class);
		when(objMock.readTree(reqMock.getInputStream())).thenReturn(jnodeMock);
		when(jnodeMock.get("reimbId")).thenReturn(jnodeMock);
		when(jnodeMock.get("approved")).thenReturn(jnodeMock);
		when(jnodeMock.asInt()).thenReturn(2);
		when(jnodeMock.asBoolean()).thenReturn(true);
		when(scMock.getSessionUser(reqMock)).thenReturn(santa);
		when(rsMock.approveDeny(santa.getUserId(), 2,true)).thenReturn(1);
		when(respMock.getWriter()).thenReturn(writer);
		rc.approveDeny(reqMock, respMock);
		verify(respMock).setContentType("text/json");
		writer.flush();
		assertTrue(stringWriter.toString().contains("null"));
	}
	
	@Test
	public void testApproveDeny2() throws IOException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		UserRole role = new UserRole(2);
		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
		JsonNode jnodeMock = mock(JsonNode.class);
		when(objMock.readTree(reqMock.getInputStream())).thenReturn(jnodeMock);
		when(jnodeMock.get("reimbId")).thenReturn(jnodeMock);
		when(jnodeMock.get("approved")).thenReturn(jnodeMock);
		when(jnodeMock.asInt()).thenReturn(2);
		when(jnodeMock.asBoolean()).thenReturn(true);
		when(scMock.getSessionUser(reqMock)).thenReturn(santa);
		when(rsMock.approveDeny(santa.getUserId(), 2,true)).thenReturn(2);
		when(respMock.getWriter()).thenReturn(writer);
		rc.approveDeny(reqMock, respMock);
		verify(respMock).setContentType("text/json");
		writer.flush();
		assertTrue(stringWriter.toString().contains("null"));
	}
	
	

}
