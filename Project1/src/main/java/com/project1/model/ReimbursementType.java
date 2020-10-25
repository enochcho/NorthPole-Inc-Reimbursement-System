package com.project1.model;

public class ReimbursementType {
	private int type;

	public ReimbursementType(int type) {
		if(validate(type)) {
			this.type = type;			
		} else {
			throw new IllegalArgumentException(type + " is not a valid reimbursement type!");
		}
	}
	
	public int getTypeId() {
		return type;
	}
	
	public boolean setTypeId(int id) {
		if(validate(id)) {
			this.type = id;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		switch(type) {
		case 1:
			return "Lodging";
		case 2:
			return "Travel";
		case 3:
			return "Food";
		case 4:
			return "Other";
		default:
			return "Something is wrong with the type";
		}
	}
	
	public boolean validate(int id) {
		return (id > 0 && id < 5); 
	}
	
	

}
