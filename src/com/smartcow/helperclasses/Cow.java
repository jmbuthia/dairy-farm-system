package com.smartcow.helperclasses;

import java.io.Serializable;
import java.util.Date;

public class Cow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ownerId;
	private String status;
	private String cowName;
	private String breed;
	private String colour;
	private String cowtag;
	private String fathersTag;
	private String mothersTag;
	private Date dob;
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCowName() {
		return cowName;
	}
	public void setCowName(String cowName) {
		this.cowName = cowName;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getCowtag() {
		return cowtag;
	}
	public void setCowtag(String cowtag) {
		this.cowtag = cowtag;
	}
	public String getFathersTag() {
		return fathersTag;
	}
	public void setFathersTag(String fathersTag) {
		this.fathersTag = fathersTag;
	}
	public String getMothersTag() {
		return mothersTag;
	}
	public void setMothersTag(String mothersTag) {
		this.mothersTag = mothersTag;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Cow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cow(int ownerId, String status, String cowName, String breed, String colour, String cowtag,
			String fathersTag, String mothersTag, Date dob) {
		super();
		this.ownerId = ownerId;
		this.status = status;
		this.cowName = cowName;
		this.breed = breed;
		this.colour = colour;
		this.cowtag = cowtag;
		this.fathersTag = fathersTag;
		this.mothersTag = mothersTag;
		this.dob = dob;
	}
	

}
