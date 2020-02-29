package com.smartcow.datacontroller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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

public class Registration {
	public static int farmerExists(String email){
		System.out.println("farmerExists method");
		System.out.println("email="+email);
		//ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = DatabaseConnector.getConnection();//pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       int userExists=0;
		
		String query="SELECT `isAccepted` FROM `farmer` WHERE `email` =?";
		
		
		try {
			 ps=con.prepareStatement(query);
			ps.setString(1,email);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				userExists=1;
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in userExists method");
			e.printStackTrace();
			userExists=-1;
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            DatabaseConnector.close(con);//pool.freeConnection(connection);
		}
		
		return userExists;
		
		
	}
	

	public static String getCowStatus(String cowtag){
		//ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = DatabaseConnector.getConnection();//pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       String status="";
		
		String query="SELECT `status` FROM `cow` WHERE `cowtag` =?";
		
		
		try {
			 ps=con.prepareStatement(query);
			ps.setString(1,cowtag);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				status=rs.getString("status");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getCowStatus method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            DatabaseConnector.close(con);//pool.freeConnection(connection);
		}
		
		return status;
		
		
	}
	
	public static int cowExists(String cowtag){
		//ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = DatabaseConnector.getConnection();//pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       int cowExists=0;
		
		String query="SELECT * FROM sdcms.cow WHERE `cowtag` =?";
		
		
		try {
			 ps=con.prepareStatement(query);
			ps.setString(1,cowtag);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				cowExists=1;
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in cowExists method");
			e.printStackTrace();
			cowExists=-1;
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            DatabaseConnector.close(con);//pool.freeConnection(connection);
		}
		
		return cowExists;
		
		
	}
	
	public static int registerFarmer(String password, Farmer farmer){
		System.out.println("Connection pool is calling registerFarmer data method");
		
		System.out.println("inside registerFarmer method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
       // PreparedStatement ps = null;
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        System.out.println(" Registration.registerFarmer date is "+sdf.format(farmer.getDob()));
        

        String query1="INSERT INTO `sdcms`.`farmer` (`idNo`, `email`, `firstName`, `lastName`, `location`, `dob`, `isAccepted`) VALUES "
        		+ "("+farmer.getIdNo()+", '"+farmer.getEmailAddress()+"', '"+farmer.getFirstName()+"', "
        				+ "'"+farmer.getLastName()+"', '"+farmer.getLocation()+"', '"+sdf.format(farmer.getDob())+"', 0);";
    
	 	

       
        String query2="INSERT INTO `sdcms`.`login` "
        		+ "(`email`, `password`, `category`) "
        		+ "VALUES "
        		+ "('"+farmer.getEmailAddress()+"', '"+password+"', 'farmer');";
       
		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
		
		
			int count[]=s.executeBatch();
			System.out.println("completed registerFarmer method");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to registerFarmer");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            DatabaseConnector.close(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	
	
	
	/*

	public static int saveFarmer(Farmer farmer){
		System.out.println("Connection pool is calling saveFarmer data method");
		
		System.out.println("inside saveFarmer method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
        System.out.println(" Registration.saveFarmer date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`farmer` (`idNo`, `email`, `firstName`, `lastName`, `location`, `dob`, `isAccepted`) VALUES "
        		+ "("+farmer.getIdNo()+", '"+farmer.getEmailAddress()+"', '"+farmer.getFirstName()+"', "
        				+ "'"+farmer.getLastName()+"', '"+farmer.getLocation()+"', '"+sdf.format(farmer.getDob())+"', 0);";

		
		
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	
*/	public static int saveMilkProduced(Milk milk, String cowtag){
		System.out.println("Connection pool is calling saveMilkProduced data method");
		
		System.out.println("inside saveMilkProduced method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`milkProduced` (`cowtag`, `amountOfLitres`, `pricePerLitre`, `amount`) VALUES "
        		+ "('"+cowtag+"', "+milk.getAmountOfLitres()+", "+milk.getPricePerLitre()+", "+milk.getMilkPrice()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}


	public static int saveDiseases(Disease disease, String cowtag){
		System.out.println("Connection pool is calling saveDiseases data method");
		
		System.out.println("inside saveDiseases method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`diseases` "
        		+ "(`cowtag`, `nameOfDisease`, `nameOfMedicine`, `amountOfDose`, `pricePerDose`, `amount`) "
        		+ "VALUES "
        		+ "('"+cowtag+"', '"+disease.getNameOfDisease()+"', '"+disease.getNameOfMedicine()+"',"
        				+ " "+disease.getAmountOfDose()+", "+disease.getPricePerDose()+", "+disease.getDiseaseCost()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	
	

	public static int saveDeworming(Deworm deworm, String cowtag){
		System.out.println("Connection pool is calling saveDeworming data method");
		
		System.out.println("inside saveDeworming method");
		System.out.println(" in the registration.saveDeworming and cow tag is="+cowtag);
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`deworm` "
        		+ "(`cowtag`, `nameOfMedicine`, `amountOfDose`, `pricePerDose`, `amount`)"
        		+ " VALUES "
        		+ "('"+cowtag+"', '"+deworm.getNameOfMedicine()+"', "+deworm.getAmountOfDose()+", "+deworm.getPricePerDose()+", "+deworm.getDewormCost()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}


	public static int saveSilage(Silage silage, String cowtag){
		System.out.println("Connection pool is calling saveSilage data method");
		
		System.out.println("inside saveSilage method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`silage` "
        		+ "(`cowtag`, `amountInKg`, `pricePerKg`, `amount`)"
        		+ " VALUES "
        		+ "('"+cowtag+"', "+silage.getAmountInKg()+", "+silage.getPricePerKg()+", "+silage.getSilageCost()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	

	public static int saveDairyMeal(DairyMeal dairyMeal, String cowtag){
		System.out.println("Connection pool is calling saveDairyMeal data method");
		
		System.out.println("inside saveDairyMeal method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`dairyMeal` "
        		+ "(`cowtag`, `amountInKg`, `pricePerKg`, `amount`) "
        		+ "VALUES "
        		+ "('"+cowtag+"', "+dairyMeal.getAmountInKg()+", "+dairyMeal.getPricePerKg()+", "+dairyMeal.getDairyMealCost()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}

	
	public static int saveSalt(Salt salt, String cowtag){
		System.out.println("Connection pool is calling saveSalt data method");
		
		System.out.println("inside saveSalt method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`salt` "
        		+ "(`cowtag`, `nameOfSalt`, `amountInKg`, `pricePerKg`, `amount`) "
        		+ "VALUES "
        		+ "('"+cowtag+"', '"+salt.getNameOfSalt()+"', "+salt.getAmountOfSaltInKg()+", "+salt.getPricePerKg()+", "+salt.getSaltCost()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	



	
	public static int saveSpray(Spray spray, String cowtag){
		System.out.println("Connection pool is calling saveSpray data method");
		
		System.out.println("inside saveSpray method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`spray` "
        		+ "(`cowtag`, `nameOfAcaricide`, `amountOfDose`, `pricePerDose`, `amount`) "
        		+ "VALUES "
        		+ "('"+cowtag+"', '"+spray.getNameOfAcaricide()+"', "+spray.getAmountOfDose()+", "+spray.getPricePerDose()+", "+spray.getSprayCost()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	

	

	public static int saveHay(Hay hay, String cowtag){
		System.out.println("Connection pool is calling saveHay data method");
		
		System.out.println("inside saveHay method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`hay` "
        		+ "(`cowtag`, `balesUsed`, `pricePerBale`, `amount`)"
        		+ " VALUES"
        		+ " ('"+cowtag+"', "+hay.getNumberOfBales()+", "+hay.getPricePerBale()+", "+hay.getHayCost()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	

	
	

	public static int saveWaterUsed(Water water, String cowtag){
		System.out.println("Connection pool is calling saveWaterUsed data method");
		
		System.out.println("inside saveWaterUsed method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
      //  System.out.println(" Registration.saveMilkProduced date is "+sdf.format(farmer.getDob()));
        

        String query="INSERT INTO `sdcms`.`water` "
        		+ "(`cowtag`, `amountInLitres`, `pricePerLitre`, `amount`) "
        		+ "VALUES "
        		+ "('"+cowtag+"', "+water.getAmountOfLitres()+", "+water.getPricePerLitre()+", "+water.getWaterCost()+");";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}



	public static int saveCow(Cow cow){
		System.out.println("Connection pool is calling saveCow data method");
		
		System.out.println("inside saveCow method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
        System.out.println(" Registration.saveCow date is "+sdf.format(cow.getDob()));
        

        String query="INSERT INTO `sdcms`.`cow` "
        		+ "(`ownerIdNo`, `cowtag`, `breed`, `cowName`, `status`, `fathersTag`, `mothersTag`, `colour`, `dob`)"
        		+ " VALUES"
        		+ " ("+cow.getOwnerId()+", '"+cow.getCowtag()+"', '"+cow.getBreed()+"', '"+cow.getCowName()+"', "
        				+ "'"+cow.getStatus()+"', '"+cow.getFathersTag()+"', '"+cow.getMothersTag()+"', '"+cow.getColour()+"', '"+sdf.format(cow.getDob())+"');";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	

	
	
	public static int acceptFarmer(Farmer farmer, boolean isAccepted){
		System.out.println("Connection pool is calling acceptFarmer data method");
		
		System.out.println("inside acceptFarmer method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
        System.out.println(" Registration.acceptFarmer date is "+sdf.format(farmer.getDob()));
        

        String query="UPDATE `sdcms`.`farmer` SET `isAccepted`="+isAccepted+" WHERE `email`='"+farmer.getEmailAddress()+"';";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	

	
	public static int changeCowStatus(String cowtag, String status){
		System.out.println("Connection pool is calling changeCowStatus data method");
		
		System.out.println("inside changeCowStatus method");
		//ConnectionPool pool = ConnectionPool.getInstance();
       //Connection connection = pool.getConnection();
		Connection connection =DatabaseConnector.getConnection();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
       

        String query="UPDATE `sdcms`.`cow` SET `status`='"+status+"' WHERE `cowtag`='"+cowtag+"';";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("saving the data in progress.......");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            DatabaseConnector.close(connection);
		}
		
		
	}
	
	

	
	
	
	
public static void main(String[] args) {
		
		//System.out.println(Registration.farmerExists("jmbuhia12@gmail.com"));
		//Farmer f=new Farmer(12347,"firstName", "lastName", "johnson", "location", new java.util.Date());
		//System.out.println(Registration.saveFarmer(f));*/
		//System.out.println(Registration.acceptFarmer(f, false));
		//System.out.println(Registration.cowExists("cw12"));
		//System.out.println(Registration.getCowStatus("cw12"));
		//Cow c =new Cow(12345, "status", "cowName", "breed", "colour", "cowtag", "fathersTag", "mothersTag", new java.util.Date());	
		//System.out.println(Registration.saveCow(c));
		//Milk m=new Milk(124.17, 46.67);
		//System.out.println(Registration.saveMilkProduced(m, "cw12"));
	//Water w=new Water(20, 30);
	//System.out.println(Registration.saveWaterUsed(w, "cw12"));
	//Deworm d=new Deworm("nilzan", 20.8,37.78);
	//System.out.println(Registration.saveDeworming(d, "cw12"));
	//Disease di=new Disease("digana", "jetxoo", 20.8,37.78);
	//System.out.println(Registration.saveDiseases(di, "cw12"));
	//System.out.println(Registration.changeCowStatus("cw13","alive"));
	//Hay h= new Hay(20.8,37.78);
	//System.out.println(Registration.saveHay(h, "cw12"));
	//Silage s=new Silage(20.8, 37.78);
	//System.out.println(Registration.saveSilage(s, "cw12"));
	//DairyMeal dm =new DairyMeal(20.8, 37.78);
	//System.out.println(Registration.saveDairyMeal(dm, "cw12"));
	//Salt st= new Salt("molarplus", 20.8, 37.78);
	//System.out.println(Registration.saveSalt(st, "cw12"));
	//Spray sp = new Spray("dip", 20.8, 37.78);
	//System.out.println(Registration.saveSpray(sp, "cw12"));
		//System.out.println(Registration.registerFarmer("johnson", f));
		
	}

}
