package com.project1.controllerTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.controller.SessionController;
import com.project1.controller.UserController;
import com.project1.model.User;
import com.project1.model.UserRole;
import com.project1.service.UserService;

public class UserControllerTest {
	private UserController uc;
	private UserService usMock;
	private SessionController scMock;
	private HttpServletRequest reqMock;
	private HttpServletResponse respMock;
	private ObjectMapper objMock;
	
	@Before
	public void setup() {
		usMock = mock(UserService.class);
		scMock = mock(SessionController.class);
		reqMock = mock(HttpServletRequest.class);
		respMock = mock(HttpServletResponse.class);
		objMock = mock(ObjectMapper.class);
		uc = new UserController(usMock, scMock, objMock);
	}
	
	@Test
	public void testLoginAsManager() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.addParameter("username", "bobby");
		reqmm.addParameter("password", "bill");
		UserRole role = new UserRole(1);
		User santa = new User(0,"santa", "Santa", "Claus", "santa@northpole.net", role);
	
		when(usMock.login("bobby","bill")).thenReturn(santa);
		when(scMock.getSessionUser(reqmm)).thenReturn(santa);
		uc.login(reqmm,resmm);
		verify(scMock).setSession(reqmm,santa);
		assertTrue(true);
	}
	
	@Test
	public void testLoginAsEmployee() {
		
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		reqmm.addParameter("username", "bobby");
		reqmm.addParameter("password", "bill");
		UserRole role = new UserRole(2);
		User santa = new User(0,"santa", "Santa", "Claus", "santa@northpole.net", role);
	
		when(usMock.login("bobby","bill")).thenReturn(santa);
		when(scMock.getSessionUser(reqmm)).thenReturn(santa);
		uc.login(reqmm,resmm);
		verify(scMock).setSession(reqmm,santa);
		assertTrue(true);
		
	}
	
	@Test
	public void testLoginAsUnregistered() throws ServletException, IOException {
		String username = "bobby";
		String password = "bill";
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		
		reqmm.addParameter(username, "bobby");
		reqmm.addParameter(password, "bill");
		User userMock = mock(User.class);
		UserRole roleMock = mock(UserRole.class);
		RequestDispatcher redMock = mock(RequestDispatcher.class);
		
		when(usMock.login("bobby","billy")).thenReturn(userMock);
		when(scMock.getSessionUser(reqmm)).thenReturn(userMock);
		when(userMock.getUsername()).thenReturn("billy");
		when(userMock.getRole()).thenReturn(null);
		uc.login(reqmm, resmm);
		assertTrue(true);
	}
	
	@Test 
	public void testLogout() {
		MockHttpServletRequest reqmm = new MockHttpServletRequest();
		MockHttpServletResponse resmm = new MockHttpServletResponse();
		uc.logout(reqmm,resmm);
		verify(scMock).invalidate(reqmm);
		assertTrue(true);
	}
	
	@Test
	public void testRegister1() throws IOException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		UserRole role = new UserRole(2);
		User santa = new User(0,"hello", "hello", "hello", "hello", role);
		JsonNode jMock = mock(JsonNode.class);
		when(objMock.readTree(reqMock.getInputStream())).thenReturn(jMock);
		when(jMock.get("roleId")).thenReturn(jMock);
		when(jMock.get("password")).thenReturn(jMock);
		when(jMock.get("username")).thenReturn(jMock);
		when(jMock.get("first")).thenReturn(jMock);
		when(jMock.get("last")).thenReturn(jMock);
		when(jMock.get("email")).thenReturn(jMock);
		when(jMock.asInt()).thenReturn(1);
		when(jMock.asText()).thenReturn("hello");
		when(usMock.register(santa, "hello")).thenReturn(1);
		when(respMock.getWriter()).thenReturn(writer);
		uc.register(reqMock, respMock);
		verify(respMock).setContentType("text/json");
		writer.flush();
		assertTrue(stringWriter.toString().contains("null"));
		
	}
	@Test
	public void testRegister2() throws IOException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		UserRole role = new UserRole(2);
		User santa = new User(0,"hello", "hello", "hello", "hello", role);
		JsonNode jMock = mock(JsonNode.class);
		when(objMock.readTree(reqMock.getInputStream())).thenReturn(jMock);
		when(jMock.get("roleId")).thenReturn(jMock);
		when(jMock.get("password")).thenReturn(jMock);
		when(jMock.get("username")).thenReturn(jMock);
		when(jMock.get("first")).thenReturn(jMock);
		when(jMock.get("last")).thenReturn(jMock);
		when(jMock.get("email")).thenReturn(jMock);
		when(jMock.asInt()).thenReturn(2);
		when(jMock.asText()).thenReturn("hello");
		when(usMock.register(santa, "hello")).thenReturn(1);
		when(respMock.getWriter()).thenReturn(writer);
		uc.register(reqMock, respMock);
		verify(respMock).setContentType("text/json");
		writer.flush();
		assertTrue(stringWriter.toString().contains("null"));
		
	}

	

}
