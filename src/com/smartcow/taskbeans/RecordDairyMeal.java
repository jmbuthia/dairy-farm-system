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
import com.smartcow.helperclasses.DairyMeal;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class RecordDairyMeal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cowtag;
	public void setCowtag(String cowtag) {
		this.cowtag = cowtag;
	}


	private DairyMeal dairyMeal;
	
	@PostConstruct
	public void init(){
		dairyMeal=new DairyMeal();
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		setCowtag(request.getParameter("cowtag"));
	}

	public RecordDairyMeal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DairyMeal getDairyMeal() {
		return dairyMeal;
	}

	public void setDairyMeal(DairyMeal dairyMeal) {
		this.dairyMeal = dairyMeal;
	}

	public String getCowtag() {
		return cowtag;
	}


	public String saveDairyMeal(){
		int isSaved=Registration.saveDairyMeal(dairyMeal, cowtag);
		if(isSaved>0){
			 RequestContext.getCurrentInstance().update("growl");  
		     	
	        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_INFO,
	                           "Success",
	                           "Dairy Meal record was saved successfully"));
		
			
		}else{
			RequestContext.getCurrentInstance().update("growl");  
	     	
        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_WARN,
	                           "ERROR!",
	                           "Error occured while saving Dairy Meal used, please try again"));
			
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
