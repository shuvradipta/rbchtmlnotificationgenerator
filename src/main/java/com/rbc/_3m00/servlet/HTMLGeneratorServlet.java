package com.rbc._3m00.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbc._3m00.util.HTMLPageGenerator;

/**
 * Servlet implementation class HTMLGeneratorServlet
 */
@WebServlet("/HTMLGeneratorServlet")
public class HTMLGeneratorServlet extends HttpServlet {
	private static final String PARAM_HTML_FILE_NAME = "htmlFileName";

	private static final long serialVersionUID = 1L;
	
	private static String[] appEnvMappings = {"HTMLGENAPPURL" , "SERVICENOTICEGENAPPURL", "HOMEDOMAIN"};
	
	private static HashMap<String,String> appConfigMappings = null;
	
	private static final String HTML_MESSAGE_PAGE = "htmlMessagePage.jsp";
	
	private static final String PROCESSING_STATUS = "PROCESSING_STATUS";
	
	private static final String GENERATED_HTML_PATH = "/html/generated.html";
	
	private static final String TEMPLATE_HTML_PATH = "/html/template.html";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HTMLGeneratorServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("appConfigMappings", appConfigMappings);
		
		request.getRequestDispatcher(HTML_MESSAGE_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File generatedFile = new File(this.getServletContext().getRealPath(GENERATED_HTML_PATH));
		File templateFile = new File(this.getServletContext().getRealPath(TEMPLATE_HTML_PATH));
		HTMLPageGenerator.getInstance().replaceContentInFile(generatedFile, templateFile, request.getParameter("bodyContent"), request.getParameter("htmlPageTitle"));
		
		request.setAttribute(PROCESSING_STATUS, true);
		request.setAttribute(PARAM_HTML_FILE_NAME, request.getParameter(PARAM_HTML_FILE_NAME));
		doGet(request, response);
	}
	
	private HashMap<String,String> initializeAppConfigMappings(){
		HashMap<String,String> appConfigMappings = new HashMap<String, String>();
		for (String envVar : appEnvMappings) {
			appConfigMappings.put(envVar, System.getenv(envVar));
		}
		System.out.println("appConfigMappings :: " + appConfigMappings);
		return appConfigMappings;
		
	}

	@Override
	public void init() throws ServletException {
		super.init();
		appConfigMappings = initializeAppConfigMappings();
	}

}
