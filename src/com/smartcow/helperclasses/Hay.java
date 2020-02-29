package com.smartcow.helperclasses;

import java.io.Serializable;
import java.math.BigDecimal;

public class Hay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double numberOfBales;
	private double pricePerBale;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Hay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Hay(double numberOfBales, double pricePerBale) {
		super();
		this.numberOfBales = numberOfBales;
		this.pricePerBale = pricePerBale;
	}
	public double getNumberOfBales() {
		return numberOfBales;
	}
	public void setNumberOfBales(double numberOfBales) {
		this.numberOfBales = numberOfBales;
	}
	public double getPricePerBale() {
		return pricePerBale;
	}
	public void setPricePerBale(double pricePerBale) {
		this.pricePerBale = pricePerBale;
	}
	public BigDecimal getHayCost(){
		BigDecimal numberOfBales = new BigDecimal(String.valueOf(this.numberOfBales));
		BigDecimal pricePerBale = new BigDecimal(String.valueOf(this.pricePerBale));
		return numberOfBales.multiply(pricePerBale);
		
	}

}
