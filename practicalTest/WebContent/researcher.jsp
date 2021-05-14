<%@ page import="model.Researcher"%>
<%@ page import="com.ResearcherService" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
//Save---------------------------------
if (request.getParameter("researcherCode") != null)
{
	Researcher resObj = new Researcher();
	String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidResearcherIDSave") == "")
{
stsMsg = resObj.insertResearcher(request.getParameter("researcherCode"),
		request.getParameter("researcherName"),
		request.getParameter("projectCode"),
		request.getParameter("email"),
		request.getParameter("location"));
}
else//Update----------------------
{
stsMsg = resObj.updateResearcher(request.getParameter("hidResearcherIDSave"),
request.getParameter("researcherCode"),
request.getParameter("researcherName"),
request.getParameter("projectCode"),
request.getParameter("email"),
request.getParameter("location"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidResearcherIDDelete") != null)
{
	Researcher resObj = new Researcher();
	String stsMsg =
	resObj.deleteResearcher(request.getParameter("hidResearcherIDDelete"));
	session.setAttribute("statusMsg", stsMsg);
}

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher</title>
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="Views/bootstrap.min.css">
    <script type="text/javascript" src="Components/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="Components/researcher.js"></script>

</head>
<body>
<!-- form for the payment insertion and the update performance -->
	<div class="row container" style="margin:0">
		<div class="jumbotron col-12">
	      <h1 style="text-align:center">Researcher Details Management</h1>
		</div>
  		<div class="col-4">
			<form id="formRes" name="formRes" method="post">
	  			
	  			
				Researcher Code: <input id="researcherCode" name="researcherCode" type="text"class="form-control form-control-sm"> <br>
		
	    		Researcher Name: <input id="researcherName" name="researcherName" type="text"class="form-control form-control-sm"> <br> 

	    
	    		Project Code: <input id="projectCode" name="projectCode" type="text"class="form-control form-control-sm"> <br>
	    
	    		Email: <input id="email" name="email" type="text" class="form-control form-control-sm"> <br> 
	    
	    		Location: <input id="location" name="location" type="text"class="form-control form-control-sm"> <br>
	    
				<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> 
		
				<input type="hidden" id="hidResearcherIDSave" name="hidResearcherIDSave" value="">
			</form>
			
			<!--  Alert messages for the events-->
			<div id="alertSuccess" class="alert alert-success"></div> 
			<div id="alertError" class="alert alert-danger"></div>
			<%
			 out.print(session.getAttribute("statusMsg"));
			%>
			
 		<br>
 		<div id="divResearcherGrid">
 		     <%
 		         Researcher resObj = new Researcher();
 		         out.print(resObj.readResearcher());
 		     %>
 		</div>
	

	<!--blank space  -->
	<div style="height:60px">
	</div>
	</div></div>
</body>
</html>