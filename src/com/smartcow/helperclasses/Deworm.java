package com.smartcow.helperclasses;

import java.io.Serializable;
import java.math.BigDecimal;

public class Deworm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameOfMedicine;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	private double amountOfDose;
	private double pricePerDose;
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
	public Deworm(String nameOfMedicine, double amountOfDose, double pricePerDose) {
		super();
		this.nameOfMedicine = nameOfMedicine;
		this.amountOfDose = amountOfDose;
		this.pricePerDose = pricePerDose;
	}
	public Deworm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BigDecimal getDewormCost(){
		BigDecimal amountOfDose = new BigDecimal(String.valueOf(this.amountOfDose));
		BigDecimal pricePerDose = new BigDecimal(String.valueOf(this.pricePerDose));
		return amountOfDose.multiply(pricePerDose);
		
	
	}
}
