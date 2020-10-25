package com.project1.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

/*
 * You can create Timestamp with Timestamp.from(Instant.now());
 * InputStream is converted to byte[] with inputstream.readAllBytes() method. 
 * You can convert a file into a inputstream by uploading as a FileItem and using the 
 * FileItem.getInputStream() method. 
 * - using an inputstream allows us to avoid using File, which requires the file to be stored on the disk. 
 */
public class Reimbursement {
	private int id;
	private double amount;
	private Timestamp submitTime;
	private Timestamp resolveTime;
	private String description;
	private InputStream receipt;
	private User author;
	private User resolver;
	private ReimbursementStatus status;
	private ReimbursementType type;
	
	
	public Reimbursement(int id, double amount, Timestamp submitTime, Timestamp resolveTime, String description,
			InputStream receipt, User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
		this.id = id;
		this.amount = amount;
		this.submitTime = submitTime;
		this.resolveTime = resolveTime;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	
	//use this for creating reimbursements that have just been submitted
	public Reimbursement (int id, double amount, Timestamp submitTime, String description, InputStream receipt, User author, ReimbursementStatus status, ReimbursementType type) {
		this(id, amount, submitTime, null, description, receipt, author, null, status, type);
	}

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public Timestamp getSubmitTime() {
		return submitTime;
	}

	public Timestamp getResolveTime() {
		return resolveTime;
	}

	public String getDescription() {
		return description;
	}

	public InputStream getReceipt() {
		return receipt;
	}

	public User getAuthor() {
		return author;
	}

	public User getResolver() {
		return resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}

	public void setResolveTime(Timestamp resolveTime) {
		this.resolveTime = resolveTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setReceipt(InputStream receipt) {
		this.receipt = receipt;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}
	
	
	
}
