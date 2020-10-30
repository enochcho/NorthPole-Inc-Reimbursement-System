package com.project1.daoTests;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project1.model.User;
import com.project1.repo.UserDao;

public class UserDaoTest {
	
	private UserDao ud;
	
	@Before
	public void setup() {
		ud = new UserDao();
	}
	
	@Test
	public void testFindAll() {
		List<User> users = ud.findAll();
		assertNotEquals(users.size(), 0,0);
	}
	

}
