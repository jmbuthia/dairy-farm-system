package com.smartcow.taskbeans;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
/*import javax.servlet.http.HttpServletRequest;*/

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;


import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.smartcow.datacontroller.Manipulation;
import com.smartcow.helperclasses.Cow;
import com.smartcow.helperclasses.DairyMeal;
import com.smartcow.helperclasses.Deworm;
import com.smartcow.helperclasses.Disease;
import com.smartcow.helperclasses.Farmer;
import com.smartcow.helperclasses.Hay;
import com.smartcow.helperclasses.Milk;
import com.smartcow.helperclasses.Salt;
import com.smartcow.helperclasses.Silage;
import com.smartcow.helperclasses.Spray;
import com.smartcow.helperclasses.Water;



@SuppressWarnings("deprecation")
@ManagedBean
public class DocumentExporters implements Serializable{
	

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String cowtag;
	
	public DocumentExporters() {
	super();
	// TODO Auto-generated constructor stub
}


	
	public ArrayList<Farmer> getAllUnacceptedFarmers(){
		
		 return Manipulation.getAllUnacceptedFarmers();
	 }
	
	public ArrayList<Farmer> getAllAcceptedFarmers(){
		
		 return Manipulation.getAllAcceptedFarmers();
	 }
	

	public ArrayList<Cow> getAllMyCows(){
		FacesContext context = FacesContext.getCurrentInstance();
		LoginLogout loginLogout = (LoginLogout) context.getApplication().evaluateExpressionGet(context, "#{loginLogout}", LoginLogout.class);
		
		
		 return Manipulation.getAllMyCows(loginLogout.getFarmer().getIdNo());
	 }
	
	public ArrayList<Deworm> getAllDewormingForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordDeworming recordDeworming = (RecordDeworming) fc.getApplication().evaluateExpressionGet(fc, "#{recordDeworming}", RecordDeworming.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordDeworming.getCowtag();
		
		
		
		 return Manipulation.getAllDewormingForMyCow(cowtag);
	 }
	
	public ArrayList<Milk> getAllMilkingForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordOutput recordOutput = (RecordOutput) fc.getApplication().evaluateExpressionGet(fc, "#{recordOutput}", RecordOutput.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordOutput.getCowtag();
		
		
		
		 return Manipulation.getAllMilkingForMyCow(cowtag);
	 }
	

	public ArrayList<Hay> getAllHayForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordHay recordHay = (RecordHay) fc.getApplication().evaluateExpressionGet(fc, "#{recordHay}", RecordHay.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordHay.getCowtag();
		
		
		
		 return Manipulation.getAllHayForMyCow(cowtag);
	 }
	
	
	public ArrayList<Silage> getAllSilageForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordSilage recordSilage = (RecordSilage) fc.getApplication().evaluateExpressionGet(fc, "#{recordSilage}", RecordSilage.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordSilage.getCowtag();
		
		
		
		 return Manipulation.getAllSilageForMyCow(cowtag);
	 }
	

	public ArrayList<Salt> getAllSaltForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordSalting recordSalting = (RecordSalting) fc.getApplication().evaluateExpressionGet(fc, "#{recordSalting}", RecordSalting.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordSalting.getCowtag();
		
		
		
		 return Manipulation.getAllSaltForMyCow(cowtag);
	 }
	
	public ArrayList<Spray> getAllSprayForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordSpraying recordSpraying = (RecordSpraying) fc.getApplication().evaluateExpressionGet(fc, "#{recordSpraying}", RecordSpraying.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordSpraying.getCowtag();
		
		
		
		 return Manipulation.getAllSprayForMyCow(cowtag);
	 }
	
	
	public ArrayList<DairyMeal> getAllDairyMealForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordDairyMeal recordDairyMeal = (RecordDairyMeal) fc.getApplication().evaluateExpressionGet(fc, "#{recordDairyMeal}", RecordDairyMeal.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordDairyMeal.getCowtag();
		
		
		
		 return Manipulation.getAllDairyMealForMyCow(cowtag);
	 }
	
	
	
	public ArrayList<Disease> getAllDiseaseForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordDisease recordDisease = (RecordDisease) fc.getApplication().evaluateExpressionGet(fc, "#{recordDisease}", RecordDisease.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordDisease.getCowtag();
		
		
		
		 return Manipulation.getAllDiseaseForMyCow(cowtag);
	 }
	
	
	
	
	public ArrayList<Water> getAllWaterForMyCow(){
		String cowtag;
		FacesContext fc = FacesContext.getCurrentInstance();
		//HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		RecordWater recordWater = (RecordWater) fc.getApplication().evaluateExpressionGet(fc, "#{recordWater}", RecordWater.class);
		//cowtag=request.getParameter("cowtag");
		cowtag=recordWater.getCowtag();
		
		
		
		 return Manipulation.getAllWaterForMyCow(cowtag);
	 }
	
	

	public ArrayList<Cow> getAllLiveCows(){
			
		
		 return Manipulation.getAllLiveCows();
	 }

	
	
	
	
	public ArrayList<Cow> getAllMyLiveCows(){
		FacesContext context = FacesContext.getCurrentInstance();
		LoginLogout loginLogout = (LoginLogout) context.getApplication().evaluateExpressionGet(context, "#{loginLogout}", LoginLogout.class);
		
		
		 return Manipulation.getAllMyLiveCows(loginLogout.getFarmer().getIdNo());
	 }

public void postProcessXLS(Object document) {
    HSSFWorkbook wb = (HSSFWorkbook) document;
    HSSFSheet sheet = wb.getSheetAt(0);
    CellStyle style = wb.createCellStyle();
    style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

    for (Row row : sheet) {
        for (Cell cell : row) {
            cell.setCellValue(cell.getStringCellValue().toUpperCase());
            cell.setCellStyle(style);
        }
    }
}


public void preProcessPDF(Object document) throws IOException,BadElementException,DocumentException{
	Document pdf=(Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ServletContext servletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	String logo=servletContext.getRealPath("")+File.separator+""+File.separator+"resources/images/sdcmsBanner.png";
	pdf.add(Image.getInstance(logo));
	pdf.addCreator("jmbuthia12@gmail.com");
	pdf.addAuthor("Johnson Mbuthia");
	pdf.addTitle("List of registered users");
	pdf.addCreationDate();
	pdf.addKeywords("Johnson Mbuthia:- name of the Developer");
	pdf.addProducer();
	pdf.setPageCount(1);
	pdf.addHeader("Johnson Mbuthia","Developer");

	pdf.addSubject("SDCMS System Documents");
	pdf.addTitle("SDCMS generated Document-(Johnson)");


	((Document) document).add(new Paragraph(""
			+ "                 SMART DAIRY COW Management System generated document. All rights reserved."
			+ "                  "
			+ "                                 (...Generated at  "+new Date()+ "...)"
					+ "                                                                "
					+ "              "));
}
/*public static void main(String[] args) {
	DocumentExporters de=new DocumentExporters();
	System.out.println(de.allRegisteredUsers.get(0).getIdNo());
}
*/
}
