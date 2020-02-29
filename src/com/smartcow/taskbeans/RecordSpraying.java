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
import com.smartcow.helperclasses.Spray;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class RecordSpraying implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cowtag;
	public void setCowtag(String cowtag) {
		this.cowtag = cowtag;
	}




	private Spray spray;
	
	@PostConstruct
	public void init(){
		spray=new Spray();
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		setCowtag(request.getParameter("cowtag"));
	}

	public RecordSpraying() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Spray getSpray() {
		return spray;
	}

	public void setSpray(Spray spray) {
		this.spray = spray;
	}

	public String getCowtag() {
		return cowtag;
	}
	

	

	public String saveSpray(){
		int isSaved=Registration.saveSpray(spray, cowtag);
		if(isSaved>0){
			 RequestContext.getCurrentInstance().update("growl");  
		     	
	        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_INFO,
	                           "Success",
	                           "Spray record was saved successfully"));
		
			
		}else{
			RequestContext.getCurrentInstance().update("growl");  
	     	
        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_WARN,
	                           "ERROR!",
	                           "Error occured while saving Spray record, please try again"));
			
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
