package com.project1.servletTests;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.project1.servlet.JsonServlet;

public class JsonServletTest {
	
	private JsonServlet js;
	
	@Before
	public void setup() {
		js = new JsonServlet();
	}
	
	
	
}
