package com.smartcow.taskbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.smartcow.datacontroller.Manipulation;
import com.smartcow.datacontroller.Registration;
import com.smartcow.helperclasses.Farmer;

@SuppressWarnings("deprecation")
@ManagedBean
public class AcceptFarmer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Farmer farmer;
	private boolean isAccepted;
	
	@PostConstruct
	public void init(){
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		String email=request.getParameter("email");
		farmer=Manipulation.getFarmer(email);
		isAccepted=true;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public AcceptFarmer() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public boolean isAccepted() {
		return isAccepted;
	}
	
	


	public String acceptFarmer(){
		int isSaved=Registration.acceptFarmer(farmer, isAccepted);
		if(isSaved>0){
			 RequestContext.getCurrentInstance().update("growl");  
		     	
	        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_INFO,
	                           "Success",
	                           "Farmer "+farmer.getFirstName()+" was accepted successfully"));
		
			
		}else{
			RequestContext.getCurrentInstance().update("growl");  
	     	
        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_WARN,
	                           "ERROR!",
	                           "Error occured while trying to accept farmer, please try again"));
			
		}
		return "#";
		
	}


}
