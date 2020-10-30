package com.project1.controllerTests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;

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
//
//	@Test
//	public void testGetAllTest() throws IOException {
//		StringWriter stringWriter = new StringWriter();
//		PrintWriter writer = new PrintWriter(stringWriter);
//		UserRole role = new UserRole(2);
//		ReimbursementType type = new ReimbursementType(1);
//		ReimbursementStatus status = new ReimbursementStatus(1);
//		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
//		User mrsclaus = new User(1,"mrsclas", "Mrs.", "Claus", "email", new UserRole(1));
//		Reimbursement r = new Reimbursement(0, 100, new Date(), null, "this is a test insert",null, santa, mrsclaus, status, type);
//		List<Reimbursement> reimbs = new ArrayList<>();
//		reimbs.add(r);
//		when(rsMock.findAll()).thenReturn(reimbs);
//		when(respMock.getWriter()).thenReturn(writer);
//		rc.getAll(respMock);
//		verify(respMock).setContentType("text/json");
//		verify(rsMock).findAll();
//		writer.flush();
//		assertTrue(stringWriter.toString().contains(new ObjectMapper().writeValueAsString(reimbs)));
//	}
//	
//	@Test
//	public void testGetEmData() throws IOException {
//		StringWriter stringWriter = new StringWriter();
//		PrintWriter writer = new PrintWriter(stringWriter);
//		UserRole role = new UserRole(2);
//		ReimbursementType type = new ReimbursementType(1);
//		ReimbursementStatus status = new ReimbursementStatus(1);
//		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
//		User mrsclaus = new User(1,"mrsclas", "Mrs.", "Claus", "email", new UserRole(1));
//		Reimbursement r = new Reimbursement(0, 100, new Date(), null, "this is a test insert",null, santa, mrsclaus, status, type);
//		List<Reimbursement> reimbs = new ArrayList<>();
//		reimbs.add(r);
//		when(scMock.getSessionUser(reqMock)).thenReturn(santa);
//		when(rsMock.viewMyTickets(santa.getUserId())).thenReturn(reimbs);
//		when(respMock.getWriter()).thenReturn(writer);
//		rc.getEmData(reqMock, respMock);
//		writer.flush();
//		assertTrue(stringWriter.toString().contains(new ObjectMapper().writeValueAsString(reimbs)));
//	}
//	
//	/**
//	 * This tests for when the reimbursement is added. 
//	 * @throws IOException
//	 */
//	@Test
//	public void testAdd1() throws IOException{
//		User userMocked = mock(User.class);
//		UserRole roleMocked = mock(UserRole.class);
//		ReimbursementType typeMocked = mock(ReimbursementType.class);
//		ReimbursementStatus statusMocked = mock(ReimbursementStatus.class);
//		Reimbursement rMocked = mock(Reimbursement.class, withSettings().serializable());
//		
//		StringWriter stringWriter = new StringWriter();
//		PrintWriter writer = new PrintWriter(stringWriter);
//		
//		ServletInputStream sisMocked = mock(ServletInputStream.class);
//		when(userMocked.getUserId()).thenReturn(1);
//		when(reqMock.getInputStream()).thenReturn(sisMocked);
//		when(scMock.getSessionUser(reqMock)).thenReturn(userMocked);
//		when(rsMock.submitRequest(rMocked, userMocked.getUserId())).thenReturn(1);
//		when(respMock.getWriter()).thenReturn(writer);
//		rc.add(reqMock,respMock);
//		writer.flush();
//		assertTrue(stringWriter.toString().contains(new ObjectMapper().writeValueAsString("The reimbursement was added")));
//	}
//	
//	@Test
//	public void testAdd2() throws IOException{
//		StringWriter stringWriter = new StringWriter();
//		PrintWriter writer = new PrintWriter(stringWriter);
//		UserRole role = new UserRole(2);
//		ReimbursementType type = new ReimbursementType(1);
//		ReimbursementStatus status = new ReimbursementStatus(1);
//		User santa = new User(3,"santa", "djd", "dd", "ddd", role);
//		User mrsclaus = new User(1,"mrsclas", "Mrs.", "Claus", "email", new UserRole(1));
//		Reimbursement r = new Reimbursement(0, 100, new Date(), null, "this is a test insert",null, santa, mrsclaus, status, type);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ObjectOutputStream oos = new ObjectOutputStream(baos);
//		oos.writeObject(r);
//		oos.flush();
//		oos.close();
//		ByteArrayInputStream is = new ByteArrayInputStream(baos.toByteArray());
//		ServletInputStream sis = new ServletInputStream() {
//			public int read() throws IOException{
//				return is.read();
//			}
//
//			@Override
//			public boolean isFinished() {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean isReady() {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public void setReadListener(ReadListener readListener) {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//		
//		when(reqMock.getInputStream()).thenReturn(sis);
//		when(scMock.getSessionUser(reqMock)).thenReturn(santa);
//		when(rsMock.submitRequest(r, santa.getUserId())).thenReturn(0);
//		when(respMock.getWriter()).thenReturn(writer);
//		rc.add(reqMock,respMock);
//		writer.flush();
//		assertTrue(stringWriter.toString().contains(new ObjectMapper().writeValueAsString("The reimbursement was NOT added")));
//	}
//	
	
	@Test
	public void testApproveDeny() {
		
	}
	
	

}
