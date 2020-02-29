package com.smartcow.helperclasses;

import java.io.Serializable;
import java.math.BigDecimal;

public class Salt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameOfSalt;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private double amountOfSaltInKg;
	private double pricePerKg;
	public Salt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salt(String nameOfSalt, double amountOfSaltInKg, double pricePerKg) {
		super();
		this.nameOfSalt = nameOfSalt;
		this.amountOfSaltInKg = amountOfSaltInKg;
		this.pricePerKg = pricePerKg;
	}
	public String getNameOfSalt() {
		return nameOfSalt;
	}
	public void setNameOfSalt(String nameOfSalt) {
		this.nameOfSalt = nameOfSalt;
	}
	public double getAmountOfSaltInKg() {
		return amountOfSaltInKg;
	}
	public void setAmountOfSaltInKg(double amountOfSaltInKg) {
		this.amountOfSaltInKg = amountOfSaltInKg;
	}
	public double getPricePerKg() {
		return pricePerKg;
	}
	public void setPricePerKg(double pricePerKg) {
		this.pricePerKg = pricePerKg;
	}
	public BigDecimal getSaltCost(){

		BigDecimal amountOfSaltInKg = new BigDecimal(String.valueOf(this.amountOfSaltInKg));
		BigDecimal pricePerKg = new BigDecimal(String.valueOf(this.pricePerKg));
		return amountOfSaltInKg.multiply(pricePerKg);
		
	}

}
