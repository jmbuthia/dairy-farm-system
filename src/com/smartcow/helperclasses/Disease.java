package com.smartcow.helperclasses;

import java.io.Serializable;
import java.math.BigDecimal;

public class Disease implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameOfDisease;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private String nameOfMedicine;
	private double amountOfDose;
	private double pricePerDose;
	public String getNameOfDisease() {
		return nameOfDisease;
	}
	public void setNameOfDisease(String nameOfDisease) {
		this.nameOfDisease = nameOfDisease;
	}
	public String getNameOfMedicine() {
		return nameOfMedicine;
	}
	public void setNameOfMedicine(String nameOfMedicine) {
		this.nameOfMedicine = nameOfMedicine;
	}
	public double getAmountOfDose() {
		return amountOfDose;
	}
	public void setAmountOfDose(double amountOfDose) {
		this.amountOfDose = amountOfDose;
	}
	public double getPricePerDose() {
		return pricePerDose;
	}
	public void setPricePerDose(double pricePerDose) {
		this.pricePerDose = pricePerDose;
	}
	public Disease() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Disease(String nameOfDisease, String nameOfMedicine, double amountOfDose, double pricePerDose) {
		super();
		this.nameOfDisease = nameOfDisease;
		this.nameOfMedicine = nameOfMedicine;
		this.amountOfDose = amountOfDose;
		this.pricePerDose = pricePerDose;
	}
	public BigDecimal getDiseaseCost(){
		BigDecimal amountOfDose = new BigDecimal(String.valueOf(this.amountOfDose));
		BigDecimal pricePerDose = new BigDecimal(String.valueOf(this.pricePerDose));
		return amountOfDose.multiply(pricePerDose);
		
	
	}
}
