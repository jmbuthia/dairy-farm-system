package com.smartcow.datacontroller;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class DatabaseConnector {

    public static Connection getConnection() {
    	System.out.println("data connection called"
    			+ "trying to connect");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sdcms", "your username", "your password");
            System.out.println("database \"sdcms\" connected");
            return con;
        } catch (Exception ex) {
        	 RequestContext.getCurrentInstance().update("growl");
			  FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_FATAL,
		                       "Database Error",
		                       "Failed to connect to database, call us on 0715949519."));
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }

}
