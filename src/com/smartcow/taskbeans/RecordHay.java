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
import com.smartcow.helperclasses.Hay;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class RecordHay implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cowtag;
	public void setCowtag(String cowtag) {
		this.cowtag = cowtag;
	}


	private Hay hay;

	@PostConstruct
	public void init(){
		hay=new Hay();
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		setCowtag(request.getParameter("cowtag"));
	}

	public RecordHay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hay getHay() {
		return hay;
	}

	public void setHay(Hay hay) {
		this.hay = hay;
	}

	public String getCowtag() {
		return cowtag;
	}
	

	public String saveHayUsed(){
		int isSaved=Registration.saveHay(hay, cowtag);
		if(isSaved>0){
			 RequestContext.getCurrentInstance().update("growl");  
		     	
	        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_INFO,
	                           "Success",
	                           "Hay record was saved successfully"));
		
			
		}else{
			RequestContext.getCurrentInstance().update("growl");  
	     	
        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_WARN,
	                           "ERROR!",
	                           "Error occured while saving Hay record, please try again"));
			
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
