package com.smartcow.helperclasses;

import java.io.Serializable;
import java.util.Date;

public class Farmer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idNo;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String location;
	private Date dob;
	
	public Farmer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Farmer(int idNo, String firstName, String lastName, String emailAddress, String location, Date dob) {
		super();
		this.idNo = idNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.location = location;
		this.dob = dob;
	}
	public int getIdNo() {
		return idNo;
	}
	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
}
