package model;

import java.sql.*;

public class Researcher {

	public Connection connect(){
		Connection con = null;
	 try
	 {
	       Class.forName("com.mysql.cj.jdbc.Driver");
	       //Provide the correct details: DBServer/DBName, username, password
	       con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget","root", "");
	       
	       //For testing
	       System.out.print("Successfully connected");
	 }
	 catch(Exception e){

	    e.printStackTrace();
	    System.out.print("not connected");
	 }
	 return con;
	}
	
	public String readResearcher(){

		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}


				// Prepare the html table to be displayed
				output = "<table border='1'> <tr>"
						+ "<th>Researcher Code</th>"
						+ "<th>Researcher Name</th>"
						+ "<th>Project Code</th>"
						+ "<th>Email</th>"
						+ "<th>Location</th>"
						+ "<th>Update</th>"
						+ "<th>Remove</th></tr>";
				
				String query = "select * from researcher";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					//'researcherID','researcherName','email','location','category'
					String researcherID = Integer.toString(rs.getInt("researcherID"));
					String researcherCode = rs.getString("researcherCode");
					String researcherName = rs.getString("researcherName");
					String projectCode = rs.getString("projectCode");
					String email = rs.getString("email");
					String location = rs.getString("location");
					

					// Add into the html table
					
					output += "<td>" + researcherCode + "</td>";
					output += "<td>" + researcherName + "</td>";
					output += "<td>" + projectCode + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + location + "</td>";
					
					
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-researcherid='" + researcherID + "'>" + "</td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-researcherid='" + researcherID + "'>" + "</td></tr>";
				}

				con.close();
				// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
				output = "Error while reading the values.";
				System.err.println(e.getMessage());
		}
		return output;
}
	
	
public String insertResearcher(String resCode, String resName, String resProjCode, String resEmail, String resLocation){
	    
	    String output = "";
		
		try{
	         Connection con = connect();
	         if (con == null)
	         {
	             return "Error while connecting to the database for insert";
	         }
	        
	        // create a prepared statement
	        String query = " insert into researcher(researcherID, researcherCode, researcherName, projectCode, email, location) values (?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);

	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, resCode);
	        preparedStmt.setString(3, resName);
	        preparedStmt.setString(4, resProjCode);
	        preparedStmt.setString(5, resEmail);
	        preparedStmt.setString(6, resLocation);
	        
	        

	        //execute the statement
	        preparedStmt.execute();
	        con.close();
	        
	        String newResearcher = readResearcher();
	        output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";

		}catch(Exception e){

			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
	        System.err.println(e.getMessage());
		}

		return output;

	}
	
	
	public String updateResearcher(String resID, String resCode, String resName,  String resProjCode, String resEmail, String resLocation){
	    
	    String output = "";

	    try{

	           Connection con = connect();
	           if (con == null){
	        	   return "Error while connecting to the database for updating.";
	           }
	           
	           // create a prepared statement

	           String query = "UPDATE researcher SET researcherCode=?,researcherName=?,projectCode=?,email=?,location=? WHERE researcherID=?";
	           PreparedStatement preparedStmt = con.prepareStatement(query);
	           
	           //binding values
	           //'researcherName','email','location','category'
	           preparedStmt.setString(1, resCode);
	           preparedStmt.setString(2, resName);
	           preparedStmt.setString(3, resProjCode);
	           preparedStmt.setString(4, resEmail);
	           preparedStmt.setString(5, resLocation);
	           preparedStmt.setInt(6, Integer.parseInt(resID));

	           // execute the statement
	           preparedStmt.execute();
	           con.close();
	           String newResearcher = readResearcher();
	           output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
	           


	        }catch(Exception e){
	        	output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
				System.err.println(e.getMessage());
	        }

	        return output;

	    }
	
	

	public String deleteResearcher(String researcherID)
	 {
	 
		String output = "";
		
		try{
			
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	 
			
			String query = "delete from researcher where researcherID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			preparedStmt.setInt(1, Integer.parseInt(researcherID));
			
			preparedStmt.execute();
			con.close();
			
			String newResearcher = readResearcher();
			output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Researcher Details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	 
	 
	}
}
