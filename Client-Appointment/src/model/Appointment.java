package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBConnection;

public class Appointment{

	public Appointment() {

	}

	// Appointment Table
	public String readAppointment() {
String output = "";
		
	    try {
	    	Connection con = DBConnection.connect();

            if (con == null) {

                return "Error while connecting to the database while reading.";

            }
			
			output = "<h2>Appointment Management</h2>"+
							 "<table class='table'>"
							 +"<thead class='thead-dark'>"
							 + "<tr>"
			                 + "<th>User ID</th>"
			                 + "<th>Doctor ID</th>"
			                 + "<th>Appointment Date</th>"
			                 + "<th>Appointment Time</th>"
			                 + "<th>Token No.</th>"
			                 + "<th>Payment Type</th>"
			                 + "<th>Amount</th>"
							 + "</tr>"
							 +"<thead>"
							 +"</div>";
			
			 String query = "select * from appointment";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String appointmentID = Integer.toString(rs.getInt("appointmentID"));
			 int userID = rs.getInt("userID");
			 int doctorID = rs.getInt("doctorID");
			 String appointmentDate = rs.getString("appointmentDate");
			 String appointmentTime = rs.getString("appointmentTime");
			 int tokenNo = rs.getInt("tokenNo");
			 String payType = rs.getString("payType");
			 double amount =  rs.getDouble("amount");
			 appointmentDate = appointmentDate.replaceAll("%2F","/");
			 
			 
			 // Add into the html table
			 output += "<td>"
			 		+ "<input id='hidAppointmentIDUpdate' name='hidAppointmentIDUpdate' type='hidden' value='"
					+ appointmentID 
					+ "'/>" + userID + "</td>";
			 output += "<td>" + doctorID + "</td>";
			 output += "<td>" + appointmentDate + "</td>";
			 output += "<td>" + appointmentTime + "</td>";
			 output += "<td>" + tokenNo + "</td>";
			 output += "<td>" + payType + "</td>";
			 output += "<td>" + amount + "</td>";
			 
			// buttons
			 output += "<td><input id='btnUpdate' name='btnUpdate' type='button'value='Update' class='btnUpdate btn btn-secondary'></td> <td><input id='btnRemove' name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
			 + appointmentID + "'>" + "</td></tr>";
			 }
			 
			 con.close();
			 // Complete the html table
			 output += "</table>";
							 
		}
		catch (Exception e)
		 {
		 output = "Error while reading the Appointment details.";
		 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	public String updateAppointment(String appointmentID, String userID, String doctorID, String appointmentDate, String appointmentTime, String tokenNo, String payType, String amount) {
		String output = "";
		
		try {
			Connection con = DBConnection.connect();

            if (con == null) {

                return "Error while connecting to the database while updating.";

            }
            
         String   query = " UPDATE appointment SET "+
                 " userID = ?, "+
                 " doctorID = ?, "+
                 " appointmentDate = ?, "+
                 " appointmentTime = ?, "+
                 " tokenNo = ?, "+
                 " payType = ?, "+
                 " amount = ? "+
                 " WHERE appointmentID = ?";
         		
         PreparedStatement preparedStmt = con.prepareStatement(query);
         appointmentDate = appointmentDate.replaceAll("%2F","/");
         
    	 // binding values
    	 preparedStmt.setInt(1, Integer.parseInt(userID));
    	 preparedStmt.setInt(2, Integer.parseInt(doctorID));
    	 preparedStmt.setString(3, appointmentDate);
    	 preparedStmt.setString(4, appointmentTime);
    	 preparedStmt.setInt(5, Integer.parseInt(tokenNo));
    	 preparedStmt.setString(6, payType);
    	 preparedStmt.setDouble(7, Double.parseDouble(amount));
    	 preparedStmt.setInt(8, Integer.parseInt(appointmentID));
    	 
    	 // execute the statement
    	 preparedStmt.execute();
    	 con.close();
    	 
    	 String newAppointment = readAppointment();
    	 output =  "{\"status\":\"success\", \"data\": \"" +
    			 newAppointment + "\"}"; 
    	 }
    	 catch (Exception e)
    	 {
    		 output = "{\"status\":\"error\", \"data\": \"Error while updating the Appointment.\"}"; 
    	 System.err.println(e.getMessage());
    	 }
		
    	 return output;
		
	}
	
	
	public String deleteAppointment(String appointmentID)
	 {
	 String output = "";
	 try {
    	Connection con = DBConnection.connect();

        if (con == null) {

            return "Error while connecting to the database while deleting.";

        }
	 // create a prepared statement
	 String query = "delete from appointment where appointmentID = ?";
	  
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(appointmentID));
	
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newAppointment = readAppointment();
	 output = "{\"status\":\"success\", \"data\": \"" +
			 newAppointment + "\"}"; 
	 
	 
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Appointment.\"}";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
public String insertAppointment(String userID, String doctorID, String appointmentDate, String appointmentTime, String tokenNo, String payType, String amount ){
		
		String output = "";

        try {
        	Connection con = DBConnection.connect();

            if (con == null) {

                return "Error while connecting to the database while inserting.";

            }

		String query ="INSERT INTO appointment(userID, doctorID, appointmentDate, appointmentTime, tokenNo,payType, amount) "+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		 // binding values
	
		 preparedStmt.setInt(1, Integer.parseInt(userID));
		 preparedStmt.setInt(2, Integer.parseInt(doctorID));
		 preparedStmt.setString(3, appointmentDate); 
		 preparedStmt.setString(4, appointmentTime); 
		 preparedStmt.setInt(5, Integer.parseInt(tokenNo)); 
		 preparedStmt.setString(6, payType); 
		 preparedStmt.setDouble(7, Double.parseDouble(amount));
		 // execute the statement
		 preparedStmt.execute();
		 
		
		 
		 con.close();
		 
		 String newAppointment = readAppointment();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newAppointment + "\"}"; 
         
		}
		
		catch (Exception e) {

			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Appointment.\"}"; 
        System.err.println(e.getMessage());

    }

    return output;
		
	}
	
}
