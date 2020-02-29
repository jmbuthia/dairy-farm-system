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
import com.smartcow.helperclasses.Deworm;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class RecordDeworming implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cowtag;
	private Deworm deworm;
	
	@PostConstruct
	public void init(){
		deworm=new Deworm();
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		setCowtag(request.getParameter("cowtag"));
		System.out.println("insid Recorddeworming.postconstruct and cowtag is "+getCowtag());
	}

	public RecordDeworming() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setCowtag(String cowtag) {
		this.cowtag = cowtag;
	}

	public Deworm getDeworm() {
		return deworm;
	}

	public void setDeworm(Deworm deworm) {
		this.deworm = deworm;
	}

	public String getCowtag() {
		return cowtag;
	}
	

	public String saveDeworming(){
		System.out.println("in the saveDeworming method. cowtag is= "+cowtag);
		int isSaved=Registration.saveDeworming(deworm, cowtag);
		if(isSaved>0){
			 RequestContext.getCurrentInstance().update("growl");  
		     	
	        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_INFO,
	                           "Success",
	                           "Deworming record was saved successfully"));
		
			
		}else{
			RequestContext.getCurrentInstance().update("growl");  
	     	
        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_WARN,
	                           "ERROR!",
	                           "Error occured while saving deworming record, please try again"));
			
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
