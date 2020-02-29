package com.smartcow.taskbeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.smartcow.datacontroller.AES_EncryptDecrypt;
import com.smartcow.datacontroller.Manipulation;
import com.smartcow.datacontroller.Registration;
import com.smartcow.helperclasses.Farmer;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped


public class LoginLogout implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Farmer farmer;
	private String password;
	private String footerDate;
	
	@PostConstruct
	public void init(){
		farmer=new Farmer();
		
		
	}

	public LoginLogout() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			this.password =AES_EncryptDecrypt.encrypt(password) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public String login(){
		String navigate= "";
		System.out.println("Called login method");
		
		int valid=Manipulation.validate(farmer.getEmailAddress(), password);
		
		System.out.println("valid="+valid);
		System.out.println("email="+farmer.getEmailAddress());
		System.out.println("password="+password);
		
		if(Registration.farmerExists(farmer.getEmailAddress())==1){
			if(valid>0){
				farmer=Manipulation.getFarmer(farmer.getEmailAddress());
				System.out.println("isaccepted= "+Manipulation.isAccepted(farmer.getEmailAddress()));
				if(Manipulation.isAccepted(farmer.getEmailAddress())){
					if(Manipulation.getCategory(farmer.getEmailAddress()).equals("admin")){
						System.out.println("is Admin");
						navigate= "admin/adminHome?faces-redirect=true";
					}else if(Manipulation.getCategory(farmer.getEmailAddress()).equals("farmer")){
						System.out.println("is farmer");
						navigate= "farmer/farmerHome?faces-redirect=true";
					}
					
					
				}else{
					System.out.println("is restricted");
					navigate= "restrictedUsers/restrictedHome?faces-redirect=true";
					
				}
				
			}else{
				RequestContext.getCurrentInstance().update("growl,messages");  
	        	
	        	 FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                            "Error",
	                            "Please use the correct email or password and try again"));
	        	
				navigate= "login";
				
			}
			
		}else{
			 RequestContext.getCurrentInstance().update("growl,messages");  
	        	
        	 FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Please register first befor logging in"));
        	
			navigate= "register";
		}
		  
		
	return navigate;
	       }
	
	
	
	
	  public String logout() {
	    	System.out.println("logout button pressed");
	        HttpSession session = SessionBean.getSession();
	        session.invalidate();
	        return "/?faces-redirect=true";
	    }
	  
	  public String getFooterDate() {
			
	 		
	 		 GregorianCalendar currentDate = new GregorianCalendar();
	 		    int year = currentDate.get(Calendar.YEAR);
	 		    int m=currentDate.get(Calendar.MONTH);
	 		    String month="";
	 		    if(m==0){
	 		    	month="January";
	 		    }
	 		    if(m==1){
	 		    	month="February";
	 		    }
	 		    if(m==2){
	 		    	month="March";
	 		    }
	 		    if(m==3){
	 		    	month="April";
	 		    }
	 		    if(m==4){
	 		    	month="May";
	 		    }
	 		    if(m==5){
	 		    	month="June";
	 		    }
	 		    if(m==6){
	 		    	month="July";
	 		    }
	 		    if(m==7){
	 		    	month="August";
	 		    }
	 		    if(m==8){
	 		    	month="September";
	 		    }
	 		    if(m==9){
	 		    	month="October";
	 		    }
	 		    if(m==10){
	 		    	month="November";
	 		    }
	 		    if(m==11){
	 		    	month="December";
	 		    }
	 		   footerDate=month+", "+String.valueOf(year);
	 		 
	 	


		return footerDate;
	}





}
