package com.smartcow.taskbeans;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.smartcow.datacontroller.AES_EncryptDecrypt;
import com.smartcow.datacontroller.Age;
import com.smartcow.datacontroller.AgeCalculator;
import com.smartcow.datacontroller.Registration;
import com.smartcow.helperclasses.Farmer;

@SuppressWarnings("deprecation")
@RequestScoped
@ManagedBean

public class RegisterFarmer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Farmer farmer;
	private String password;
	@PostConstruct
	public void init(){
		farmer=new Farmer();
	}
	
	public RegisterFarmer() {
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
	

public String minimumAge(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) - 18;
     int currMonth = now.get(Calendar.MONTH) + 1;
     int days = now.get(Calendar.DATE);
     String min=years+"/"+currMonth+"/"+days;

     return min;
     
}

public String maximumAge(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -100;
     int currMonth = now.get(Calendar.MONTH) + 1;
     int days = now.get(Calendar.DATE);
     String max=years+"/"+currMonth+"/"+days;
     
     return max;
   
     
}
public String yearmin(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -18;
  
     return String.valueOf(years);
     
}
public String yearmax(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -100;
  
     return String.valueOf(years);
     
}


public String registerFarmer(){
	System.out.println("inside registerFarmer method");
	String url="";
	if(Registration.farmerExists(farmer.getEmailAddress())==1){
		RequestContext.getCurrentInstance().update("growl");  
     	
   	 FacesContext.getCurrentInstance().addMessage(
               null,
               new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Error",
                       "Farmer already exists, please login."));
   	url= "register";
	
	}
	else{
		Age age = AgeCalculator.calculateAgeBefore(farmer.getDob());
		System.out.println("Age is"+ age);
		//Date d1=new Date();
		//Date d2=new Date();
		if(age.getYears()<18){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_WARN,
		                       "Error",
		                       "You must be 18 years and over."));
			
		}else {
			
		
		//comment start
		
		int isSaved=Registration.registerFarmer(getPassword(), getFarmer());
		
		if(isSaved==2){
			RequestContext.getCurrentInstance().update("growl");  
	     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_INFO,
	                       "Success",
	                       "Data saved successfully, login now."));
	   	 HttpSession session= SessionBean.getSession();
	   	 session.setMaxInactiveInterval(10);
	   	
	   	 session.setAttribute("message","You registered successfully, please login now.");
	   	/* //sending notification to registered user
	   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
		  executor.execute(new SendSms(phone, 
				  "Thank you "+firstName+" for registering to DBMS. Use Id Number and password to login. Remember to keep your password secure."));
		  
		  try {
			  executor.shutdown();
			  System.out.println("Trying to wait thread to finish");
			executor.awaitTermination(10,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Failed to wait for some minutes to shutdown executor");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	   	*/
	   	 url= "login?faces-redirect=true";
		}
		
		else if(isSaved==0) {
			 RequestContext.getCurrentInstance().update("growl");  
	     	
	    	 FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_FATAL,
	                        "Error",
	                        "Error occured while savind your details, please try again."));
		
	    	 url= "register";
		}
		else if(isSaved==-1) {
			 RequestContext.getCurrentInstance().update("growl,messages");  
	     	
		        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_WARN,
	                           "Sorry",
	                           "Please try again later, we are currently updating our database."));
		
	    	 url= "register";
		}
		
	}
		//comment stop
		}
	return url;
}




}
