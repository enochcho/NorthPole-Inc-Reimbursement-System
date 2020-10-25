package com.project1.model;

public class User {
	private String username;
	private int userId;
	private String first;
	private String last;
	private String email;
	private UserRole role;
	
	public User(int userId, String username, String first, String last, String email, UserRole role) {
		this.userId = userId;
		this.username = username;
		this.first = first;
		this.last = last;
		this.email =email;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public int getUserId() {
		return userId;
	}

	public String getFirst() {
		return first;
	}

	public UserRole getRole() {
		return role;
	}


	public String getLast() {
		return last;
	}

	public String getEmail() {
		return email;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public void setFirst(String first) {
		this.first = first;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
