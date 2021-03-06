package com.smartcow.helperclasses;

import java.io.Serializable;
import java.math.BigDecimal;

public class DairyMeal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double amountInKg;
	private double pricePerKg;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getAmountInKg() {
		return amountInKg;
	}
	public void setAmountInKg(double amountInKg) {
		this.amountInKg = amountInKg;
	}
	public double getPricePerKg() {
		return pricePerKg;
	}
	public void setPricePerKg(double pricePerKg) {
		this.pricePerKg = pricePerKg;
	}
	public DairyMeal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DairyMeal(double amountInKg, double pricePerKg) {
		super();
		this.amountInKg = amountInKg;
		this.pricePerKg = pricePerKg;
	}
	public BigDecimal getDairyMealCost(){
		BigDecimal amountInKg = new BigDecimal(String.valueOf(this.amountInKg));
		BigDecimal pricePerKg = new BigDecimal(String.valueOf(this.pricePerKg));
		return amountInKg.multiply(pricePerKg);
		
		
	}
}
