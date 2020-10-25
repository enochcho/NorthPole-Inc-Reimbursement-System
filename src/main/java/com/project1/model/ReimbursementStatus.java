package com.project1.model;

public class ReimbursementStatus {
	private int status;

	public ReimbursementStatus(int status) {
		if(validate(status)) {
			this.status = status;
		} else {
			throw new IllegalArgumentException(status + " is not a valid reimbursement status!");
		}
	}
	
	public int getStatusId() {
		return status;
	}
	
	public boolean setStatusId(int id) {
		if(validate(id)) {
			this.status = id;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		switch(status) {
		case 1:
			return "Pending";
		case 2:
			return "Approved";
		case 3:
			return "Denied";
		default:
			return "something is wrong";
		}
	}
	
	public boolean validate(int id) {
		return (id > 0 && id < 4); 
	}
	
	
}
