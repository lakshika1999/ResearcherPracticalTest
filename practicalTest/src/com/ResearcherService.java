package com;

import model.Researcher;

//for REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Researcher")
public class ResearcherService {

	Researcher resObj = new Researcher();
	
	//for viewing researcher
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearcher() {
		
		return resObj.readResearcher();
		
	}
	//researcherID','researcherName','email','location','category'
	//for inserting researcher	
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertResearcher( @FormParam("researcherCode") String researcherCode,
									@FormParam("researcherName") String researcherName,
									@FormParam("projectCode") String projectCode,
									  @FormParam("email") String email,
									  @FormParam("location") String location)
									 
									  
		{
				String output = resObj.insertResearcher(researcherCode, researcherName, projectCode, email, location);
				return output;
		}
		
		//insert and view is tested using POSTMAN
		
		
		//for updating researcher
		   @PUT
		   @Path("/")
		   @Produces(MediaType.TEXT_PLAIN)
		   public String updateResearcher(String resData) {
			
			 //Convert the input string to a JSON object
			  JsonObject resjObj = new JsonParser().parse(resData).getAsJsonObject();
			//researcherID','researcherName','email','location','category'
			//Read the values from the JSON object
			  String resID= resjObj.get("researcherID").getAsString();
			  String resCode= resjObj.get("researcherCode").getAsString();
			  String resName= resjObj.get("researcherName").getAsString();
			  String resProjCode= resjObj.get("projectCode").getAsString();
			  String resEmail= resjObj.get("email").getAsString();
			  String resLocation= resjObj.get("location").getAsString();
			  
			  
			  
			  String output= resObj.updateResearcher(resID, resCode, resName, resProjCode, resEmail, resLocation);
			  return output;
			  
			  
			 
		  }
		   
		   
		   //for deleting researcher
		   @DELETE
		   @Path("/")
		   @Consumes(MediaType.APPLICATION_XML)
		   @Produces(MediaType.TEXT_PLAIN)
		   public String deleteResearcher(String resData)
		   {
		   			//Convert the input string to an XML document
		   			Document doc = Jsoup.parse(resData, "", Parser.xmlParser());
		   			//Read the value from the element <researcherID>
		   			String resID = doc.select("researcherID").text();
		   			String output = resObj.deleteResearcher(resID);
		   			return output;
		   }
}
