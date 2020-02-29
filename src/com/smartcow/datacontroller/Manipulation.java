package com.smartcow.datacontroller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class Manipulation {

	
	public static int validate(String email, String password) {
		System.out.println("validate.email="+email);
		System.out.println("validate.password="+password);
        Connection con = DatabaseConnector.getConnection();
    
        
	       PreparedStatement ps = null;
	       ResultSet rs=null;

	       try {
	      
	           ps = con.prepareStatement("Select email, password from login where email = ? and password = ?");
	           ps.setString(1, email);
	           try {
				ps.setString(2, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error encrypting password");
				e.printStackTrace();
			}
	           

	            rs = ps.executeQuery();

	           if (rs.next()) {
	               //result found, means valid inputs
	        	   System.out.println("validate email="+rs.getString(1));
	        	   System.out.println("validate password="+rs.getString(2));
	               return 1;
	           }
	       } catch (SQLException ex) {
	           System.out.println("Login error -->" + ex.getMessage());
	           return -1;
	       } finally {
	    	   DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);//from here
	            if (con != null) {
	            	DatabaseConnector.close(con);
	            } else {
	            	return -1;
	            }
	       }
	       return 0;
	   }
	
	public static String getCategory(String email){
		//ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = DatabaseConnector.getConnection();//pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       String category="";
		
		String query="SELECT `category` FROM `login` WHERE `email` =?";
		
		
		try {
			 ps=con.prepareStatement(query);
			ps.setString(1,email);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				category=rs.getString("category");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getcategory method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            DatabaseConnector.close(con);//pool.freeConnection(connection);
		}
		
		return category;
		
		
	}
	public static boolean isAccepted(String email){
		//ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = DatabaseConnector.getConnection();//pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       boolean isAccepted=false;
		
		String query="SELECT `isAccepted` FROM `farmer` WHERE `email` =?";
		
		
		try {
			 ps=con.prepareStatement(query);
			ps.setString(1,email);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				isAccepted=rs.getBoolean("isAccepted");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getisAccepted method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            DatabaseConnector.close(con);//pool.freeConnection(connection);
		}
		
		return isAccepted;
		
		
	}
	

	public static ArrayList<DairyMeal> getAllDairyMealForMyCow(String cowtag) {

		

		System.out.println("inside getAllDairyMealForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.dairyMeal where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<DairyMeal> dairyMeal = new ArrayList<DairyMeal>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	DairyMeal d =new DairyMeal();
	            	d.setAmountInKg(rs.getDouble("amountInKg"));
	            	d.setPricePerKg(rs.getDouble("pricePerKg"));
	            	d.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            	dairyMeal.add(d);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return dairyMeal;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllDairyMealForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllDairyMealForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		

	
	
	
	}

	
	public static ArrayList<Silage> getAllSilageForMyCow(String cowtag) {
		

		System.out.println("inside getAllSilageForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.silage where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Silage> silage = new ArrayList<Silage>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Silage s =new Silage();
	            	s.setAmountInKg(rs.getDouble("amountInKg"));
	            	s.setPricePerKg(rs.getDouble("pricePerKg"));
	            	s.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            		silage.add(s);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return silage;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllSilageForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllSilageForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		

	
	
	}

	
	
	public static ArrayList<Hay> getAllHayForMyCow(String cowtag) {



		System.out.println("inside getAllHayForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.hay where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Hay> hay = new ArrayList<Hay>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Hay h =new Hay();
	            		h.setNumberOfBales(rs.getDouble("balesUsed"));
	            		h.setPricePerBale(rs.getDouble("pricePerBale"));
	            		h.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            		hay.add(h);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return hay;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllHayForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllHayForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		

	
	}
	

	public static ArrayList<Disease> getAllDiseaseForMyCow(String cowtag) {



		System.out.println("inside getAllDiseaseForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.diseases where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Disease> disease = new ArrayList<Disease>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Disease d =new Disease();
	            		d.setNameOfDisease(rs.getString("nameOfDisease"));
	            		d.setNameOfMedicine(rs.getString("nameOfMedicine"));
	            		d.setAmountOfDose(rs.getDouble("amountOfDose"));
	            		d.setPricePerDose(rs.getDouble("pricePerDose"));
	            		d.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            		disease.add(d);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return disease;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllDiseaseForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllDiseaseForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		

	
	}
	

	
	public static ArrayList<Water> getAllWaterForMyCow(String cowtag) {


		System.out.println("inside getAllWaterForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.water where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Water> water = new ArrayList<Water>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Water w =new Water();
	            		w.setAmountOfLitres(rs.getDouble("amountInLitres"));
	            		w.setPricePerLitre(rs.getDouble("pricePerLitre"));
	            		w.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            		water.add(w);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return water;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllWaterForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllWaterForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		

	}
	
	public static ArrayList<Milk> getAllMilkingForMyCow(String cowtag) {


		System.out.println("inside getAllMilkingForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.milkProduced where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Milk> milk = new ArrayList<Milk>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Milk m =new Milk();
	            		m.setAmountOfLitres(rs.getDouble("amountOfLitres"));
	            		m.setPricePerLitre(rs.getDouble("pricePerLitre"));
	            		m.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            	milk.add(m);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return milk;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMilkingForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMilkingForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		
	

	}
	

	public static ArrayList<Salt> getAllSaltForMyCow(String cowtag) {


		

		System.out.println("inside getAllSaltForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.salt where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Salt> salt = new ArrayList<Salt>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Salt s =new Salt();
	            	s.setNameOfSalt(rs.getString("nameOfSalt"));
	            	s.setAmountOfSaltInKg(rs.getDouble("amountInKg"));
	            	s.setPricePerKg(rs.getDouble("pricePerKg"));
	          		s.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            		salt.add(s);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return salt;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllSaltForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllSaltForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		
	
		
	
	}

	public static ArrayList<Spray> getAllSprayForMyCow(String cowtag) {


		

		System.out.println("inside getAllSprayForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.spray where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Spray> spray = new ArrayList<Spray>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Spray s =new Spray();
	            	s.setNameOfAcaricide(rs.getString("nameOfAcaricide"));
	            	s.setAmountOfDose(rs.getDouble("amountOfDose"));
	            	s.setPricePerDose(rs.getDouble("pricePerDose"));
	            	s.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            		spray.add(s);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return spray;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllSprayForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllSprayForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		
	
		
	
	}
	
	
	public static ArrayList<Deworm> getAllDewormingForMyCow(String cowtag) {

		

		System.out.println("inside getAllDewormingForMyCow method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT * FROM sdcms.deworm where cowtag='"+cowtag+"' ORDER BY time DESC;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Deworm> deworm = new ArrayList<Deworm>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Deworm d =new Deworm();
	            		d.setAmountOfDose(rs.getDouble("amountOfDose"));
	            		d.setNameOfMedicine(rs.getString("nameOfMedicine"));
	            		d.setPricePerDose(rs.getDouble("pricePerDose"));
	            		d.setTime(rs.getString("time"));
	            		
	            	
	            	          	
	            
	            	deworm.add(d);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return deworm;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllDewormingForMyCow catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllDewormingForMyCow -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		
	
		
	}
	
	
	
	public static ArrayList<Cow> getAllMyCows(int idNo) {
		

		System.out.println("inside getAllMyCows method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT DISTINCT * FROM sdcms.cow WHERE ownerIdNo="+idNo+";";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Cow> cow = new ArrayList<Cow>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Cow c =Manipulation.getCow(rs.getString("cowtag"));
	            	
	            	          	
	            
	            	cow.add(c);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return cow;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMyCows catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMyCows -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		
	}
	
	public static ArrayList<Cow> getAllLiveCows() {

		

		System.out.println("inside getAllLiveCows method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT DISTINCT * FROM sdcms.cow WHERE status='alive';";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Cow> cow = new ArrayList<Cow>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Cow c =Manipulation.getCow(rs.getString("cowtag"));
	            	
	            	          	
	            
	            	cow.add(c);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return cow;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllLiveCows catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllLiveCows -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		
	
	}
	
	
public static ArrayList<Cow> getAllMyLiveCows(int idNo) {
		

		System.out.println("inside getAllMyLiveCows method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT DISTINCT * FROM sdcms.cow WHERE ownerIdNo="+idNo+" AND status='alive';";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Cow> cow = new ArrayList<Cow>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Cow c =Manipulation.getCow(rs.getString("cowtag"));
	            	
	            	          	
	            
	            	cow.add(c);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return cow;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMyLiveCows catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMyLiveCows -->" + e.getMessage());
	            return (null);
	        }
	    
		
		
		
	}
	

public static ArrayList<Farmer> getAllAcceptedFarmers() {

	System.out.println("inside getAllAcceptedFarmers method");
	/*ConnectionPool pool = ConnectionPool.getInstance();
    Connection con = pool.getConnection();
*/
	Connection con=null;
	try {
		con=DatabaseConnector.getConnection();
         String query="SELECT Distinct * FROM sdcms.farmer where isAccepted=true;";
			PreparedStatement ps = con.prepareStatement(query);
            ArrayList<Farmer> farmer = new ArrayList<Farmer>();
           
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next()) {
            	Farmer f =Manipulation.getFarmer(rs.getString("email"));
            	
            	          	
            
            	farmer.add(f);
                found = true;
                System.out.println("setting data...");
            }
            rs.close();
            if (found) {
            	System.out.println("returning the list");
                return farmer;
            } else {
            	System.out.println("no entry found");
            	return null; // no entires found
                
            }
        } catch (Exception e) {
        	System.out.println("Error occured In getAllAcceptedFarmers catch exception..........."
        			+ "..........................."
        			+ "..........................."
        			+ ".............................");
            System.out.println("Error In getAllAcceptedFarmers -->" + e.getMessage());
            return (null);
        }
    
}

	

	public static ArrayList<Farmer> getAllUnacceptedFarmers() {
		System.out.println("inside getAllUnacceptedFarmers method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DatabaseConnector.getConnection();
	         String query="SELECT Distinct * FROM sdcms.farmer where isAccepted=false;";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Farmer> farmer = new ArrayList<Farmer>();
	           
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Farmer f =Manipulation.getFarmer(rs.getString("email"));
	            	
	            	          	
	            
	            	farmer.add(f);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return farmer;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllUnacceptedFarmers catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllUnacceptedFarmers -->" + e.getMessage());
	            return (null);
	        }
	    }
	

	
	
	public static Farmer getFarmer(String email){
		//ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = DatabaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Farmer f=new Farmer();
		
		String query="SELECT * FROM `farmer` WHERE `email` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,email);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				f.setIdNo(rs.getInt("idNo"));
				f.setEmailAddress(rs.getString("email"));
				f.setFirstName(rs.getString("firstName"));
				f.setLastName(rs.getString("lastName"));
				f.setLocation(rs.getString("location"));
				f.setDob(rs.getDate("dob"));
			
		
									
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getFarmer method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            DatabaseConnector.close(connection);
		}
		
		return f;
		
		
	}
	

	public static Cow getCow(String cowtag){
		//ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = DatabaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cow c= new Cow();
		
		String query="SELECT * FROM `cow` WHERE `cowtag` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,cowtag);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				c.setOwnerId(rs.getInt("ownerIdNo"));
				c.setCowtag(rs.getString("cowtag"));
				c.setBreed(rs.getString("breed"));
				c.setCowName(rs.getString("cowName"));
				c.setStatus(rs.getString("status"));
				c.setFathersTag(rs.getString("fathersTag"));
				c.setMothersTag(rs.getString("mothersTag"));
				c.setColour(rs.getString("colour"));
				c.setDob(rs.getDate("dob"));
			
		
									
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getCow method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            DatabaseConnector.close(connection);
		}
		
		return c;
		
		
	}
	

	

	

	public static void main(String[] args) {
		
		//System.out.println(Manipulation.validate("jmbuthia12@gmail.com", "12345678"));
		//System.out.println(Manipulation.getCategory("jmbuthia12@gmail.com"));
		//System.out.println(Manipulation.getFarmer("jmbuthia12@gmail.com").getFirstName());
		//System.out.println(Manipulation.isAccepted("jmbuthia12@gmail.com"));
		//System.out.println(Manipulation.getCow("cw12").getStatus());
		
	}

	

	

	

}
