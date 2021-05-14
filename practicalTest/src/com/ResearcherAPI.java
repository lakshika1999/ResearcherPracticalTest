package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Researcher;
import com.ResearcherService;

/**
 * Servlet implementation class ResearcherAPI
 */
@WebServlet("/ResearcherAPI")
public class ResearcherAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Researcher resObj = new Researcher();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResearcherAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = resObj.insertResearcher(request.getParameter("researcherCode"),
				request.getParameter("researcherName"),
				request.getParameter("projectCode"),
				request.getParameter("email"),
				request.getParameter("location"));
				response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		
		Map paras = getParasMap(request);
		
		String output = resObj.updateResearcher(paras.get("hidResearcherIDSave").toString(),
				paras.get("researcherCode").toString(),
				paras.get("researcherName").toString(),
				paras.get("projectCode").toString(),
				paras.get("email").toString(),
				paras.get("location").toString());
				response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = resObj.deleteResearcher(paras.get("researcherID").toString());
		response.getWriter().write(output);
	}
//
	//Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
				scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				String[] params = queryString.split("&");
				for (String param : params)
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			}
			catch (Exception e)
			{
			}
		return map; 
		} 
}
