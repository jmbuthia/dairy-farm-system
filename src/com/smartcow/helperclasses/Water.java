package com.smartcow.helperclasses;

import java.io.Serializable;
import java.math.BigDecimal;

public class Water implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double amountOfLitres;
	private double pricePerLitre;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Water() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Water(double amountOfLitres, double pricePerLitre) {
		super();
		this.amountOfLitres = amountOfLitres;
		this.pricePerLitre = pricePerLitre;
	}
	public double getAmountOfLitres() {
		return amountOfLitres;
	}
	public void setAmountOfLitres(double amountOfLitres) {
		this.amountOfLitres = amountOfLitres;
	}
	public double getPricePerLitre() {
		return pricePerLitre;
	}
	public void setPricePerLitre(double pricePerLitre) {
		this.pricePerLitre = pricePerLitre;
	}
	public BigDecimal getWaterCost(){
		BigDecimal amountOfLitres = new BigDecimal(String.valueOf(this.amountOfLitres));
		BigDecimal pricePerLitre = new BigDecimal(String.valueOf(this.pricePerLitre));
		return amountOfLitres.multiply(pricePerLitre);
	}

}
