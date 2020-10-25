package com.project1.model;

public class UserRole {
	private int role;
	
	//1 is financial manager
	//2 is employee
	public UserRole(int role) {
		if(validate(role)) {
			this.role = role;			
		} else {
			throw new IllegalArgumentException(role + " is not a valid user role!");
		}
	}
	
	public int getTypeId() {
		return role;
	}
	
	public boolean setTypeId(int id) {
		if(validate(id)) {
			this.role = id;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		switch(role) {
		case 1:
			return "Financial Manager";
		case 2:
			return "Employee";
		default:
			return "Something is wrong with the role";
		}
	}
	
	public boolean validate(int id) {
		return (id > 0 && id < 3); 
	}
}
