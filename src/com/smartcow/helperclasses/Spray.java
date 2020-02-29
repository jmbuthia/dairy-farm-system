package com.smartcow.helperclasses;

import java.io.Serializable;
import java.math.BigDecimal;

public class Spray implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameOfAcaricide;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private double amountOfDose;
	private double pricePerDose;
	
	public String getNameOfAcaricide() {
		return nameOfAcaricide;
	}
	public Spray() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Spray(String nameOfAcaricide, double amountOfDose, double pricePerDose) {
		super();
		this.nameOfAcaricide = nameOfAcaricide;
		this.amountOfDose = amountOfDose;
		this.pricePerDose = pricePerDose;
	}
	public void setNameOfAcaricide(String nameOfAcaricide) {
		this.nameOfAcaricide = nameOfAcaricide;
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
	public BigDecimal getSprayCost(){
		BigDecimal amountOfDose = new BigDecimal(String.valueOf(this.amountOfDose));
		BigDecimal pricePerDose = new BigDecimal(String.valueOf(this.pricePerDose));
		return amountOfDose.multiply(pricePerDose);
		
	}
}
