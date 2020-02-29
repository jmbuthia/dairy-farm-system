package com.smartcow.taskbeans;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.smartcow.datacontroller.Age;
import com.smartcow.datacontroller.AgeCalculator;
import com.smartcow.datacontroller.Registration;
import com.smartcow.helperclasses.Cow;
import com.smartcow.helperclasses.Farmer;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class RegisterCow implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cow cow;
	private Farmer farmer;
	
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	@PostConstruct
	public void init(){
		FacesContext context = FacesContext.getCurrentInstance();
		LoginLogout loginLogout = (LoginLogout) context.getApplication().evaluateExpressionGet(context, "#{loginLogout}", LoginLogout.class);
		farmer=loginLogout.getFarmer();
		System.out.println("farmerEmail= "+farmer.getEmailAddress());
		
		cow=new Cow();
		cow.setStatus("alive");
		cow.setOwnerId(this.farmer.getIdNo());
		
		
	}

	public RegisterCow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cow getCow() {
		return cow;
	}

	public void setCow(Cow cow) {
		this.cow = cow;
	}

	

	public Farmer getFarmer() {
		return farmer;
	}
	


public String minimumAge(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) ;
     int currMonth = now.get(Calendar.MONTH) + 1;
     int days = now.get(Calendar.DATE);
     String min=years+"/"+currMonth+"/"+days;

     return min;
     
}

public String maximumAge(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -50;
     int currMonth = now.get(Calendar.MONTH) + 1;
     int days = now.get(Calendar.DATE);
     String max=years+"/"+currMonth+"/"+days;
     
     return max;
   
     
}
public String yearmin(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) ;
  
     return String.valueOf(years);
     
}
public String yearmax(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -50;
  
     return String.valueOf(years);
     
}




public String registerMyCow(){
	System.out.println("inside registerMyCow method");
	String url="";
	if(Registration.cowExists(cow.getCowtag())==1){
		RequestContext.getCurrentInstance().update("growl");  
     	
   	 FacesContext.getCurrentInstance().addMessage(
               null,
               new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Error",
                       "Cow already exists."));
   	url= "registerCow";
	
	}
	else{
		if(cow.getCowtag().equals(cow.getFathersTag())||cow.getCowtag().equals(cow.getMothersTag())){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Error",
		                       "Cow cannot be its own mother or father."));
		   	url= "registerCow";
			
		}else{

			Age age = AgeCalculator.calculateAgeBefore(cow.getDob());
			System.out.println("Age is"+ age);
			//Date d1=new Date();
			//Date d2=new Date();
			if(age.getYears()<0&age.getMonths()<0&age.getDays()<0){
				RequestContext.getCurrentInstance().update("growl");  
		     	
			   	 FacesContext.getCurrentInstance().addMessage(
			               null,
			               new FacesMessage(FacesMessage.SEVERITY_WARN,
			                       "Error",
			                       "Cow must be born."));
				
			}else {
				
			
			//comment start
			
			int isSaved=Registration.saveCow(cow);
			
			if(isSaved>0){
				RequestContext.getCurrentInstance().update("growl");  
		     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_INFO,
		                       "Success",
		                       "Data saved successfully, Cow is added."));
		 
		   	 url= "farmerHome";
			}
			
			else if(isSaved==0) {
				 RequestContext.getCurrentInstance().update("growl");  
		     	
		    	 FacesContext.getCurrentInstance().addMessage(
		                null,
		                new FacesMessage(FacesMessage.SEVERITY_FATAL,
		                        "Error",
		                        "Error occured while savind your details, please try again."));
			
		    	 url= "registerCow";
			}
			else if(isSaved==-1) {
				 RequestContext.getCurrentInstance().update("growl,messages");  
		     	
			        	
		       	 FacesContext.getCurrentInstance().addMessage(
		                   null,
		                   new FacesMessage(FacesMessage.SEVERITY_WARN,
		                           "Sorry",
		                           "Please try again later, we are currently updating our database."));
			
		    	 url= "registerCow";
			}
			
		}
			//comment stop
			
		}
	}
	return url;
}





}
