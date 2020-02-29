package com.smartcow.taskbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.smartcow.datacontroller.Registration;
import com.smartcow.helperclasses.Water;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class RecordWater implements Serializable {

	public void setCowtag(String cowtag) {
		this.cowtag = cowtag;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cowtag;
	private Water water;
	
	@PostConstruct
	public void init(){
		water=new Water();
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		setCowtag(request.getParameter("cowtag"));

		
	}

	public RecordWater() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Water getWater() {
		return water;
	}

	public void setWater(Water water) {
		this.water = water;
	}

	public String getCowtag() {
		return cowtag;
	}
	

	public String saveWaterUsed(){
		int isSaved=Registration.saveWaterUsed(water, cowtag);
		if(isSaved>0){
			 RequestContext.getCurrentInstance().update("growl");  
		     	
	        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_INFO,
	                           "Success",
	                           "Water Used was saved successfully"));
		
			
		}else{
			RequestContext.getCurrentInstance().update("growl");  
	     	
        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_WARN,
	                           "ERROR!",
	                           "Error occured while saving water used, please try again"));
			
		}
		return "record";
		
	}
	public void cowtagListener(){
		System.out.println("cowtagListener method called");
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		setCowtag(request.getParameter("cowtag"));
		System.out.println("and thecow tag selected is=-------------------------------------------------------------"
				+ "---------------------------------------------------------------------- "+cowtag);
		
	}


	

}
