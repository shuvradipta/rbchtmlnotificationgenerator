package com.rbc._3m00.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbc._3m00.dto.MobileNotice;
import com.rbc._3m00.util.AppUtil;
import com.rbc._3m00.util.XMLFileHandler;
import com.rbc._3m00.util.XMLGenerator;

/**
 * Servlet implementation class MobileNoticeServlet
 */
@WebServlet("/MobileNoticeServlet")
public class MobileNoticeServlet extends HttpServlet {
	private static final String PROCESSING_STATUS = "PROCESSING_STATUS";

	private static final String XML_MOBILE_MESSAGE_PATH = "/xml/mobile-message.xml";

	private static final String XML_MOBILE_MESSAGE_TEMPLATE_PATH = "/xml/mobile-message-template.xml";

	private static final String MOBILE_NOTICE_PAGE_JSP = "mobileNoticePage.jsp";
	
	private static final long serialVersionUID = 1L;
	
	private static HashMap<String,String> appConfigMappings = null;
	
	private static final String[] tableColumnNames = new String[]{"Notice Header", "Description", "Platform(s)","Start Date","Expiry Date"};
	
	private static final String[] requestParams = new String[]{"noticeHeader", "noticeDescription", "platform","startDate","expiryDate"};
	
	private static final String MOBILE_DATE_FORMAT = "yyyy-MM-dd";

	private static final String MAX_MOBILE_NOTICE_COUNT = "MAX_MOBILE_NOTICE_COUNT";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MobileNoticeServlet() {
        super();
        appConfigMappings = AppUtil.getInstance().initializeAppConfigMappings();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("appConfigMappings", appConfigMappings);
		request.setAttribute("tableColumnNames", tableColumnNames);
		request.setAttribute("serverDateTime", AppUtil.getInstance().getServerDateTime(MOBILE_DATE_FORMAT));
		request.getRequestDispatcher(MOBILE_NOTICE_PAGE_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int maxNotices = Integer.parseInt(appConfigMappings.get(MAX_MOBILE_NOTICE_COUNT));
		ArrayList<MobileNotice> mobileNotices = new ArrayList<MobileNotice>(0);
		for(int i = 1; i <= maxNotices; i++){
			if(checkInputs(request, i)){
				MobileNotice mobileNotice = new MobileNotice();
				mobileNotice.setMobileNoticeHeader(request.getParameter(requestParams[0]+i));
				mobileNotice.setMobileNoticeDescription((request.getParameter(requestParams[1]+i)));
				mobileNotice.setMobileNoticePlatform(AppUtil.convertArraytoString(request.getParameterValues(requestParams[2]+i)));
				mobileNotice.setMobileNoticeStartDate(request.getParameter(requestParams[3]+i));
				mobileNotice.setMobileNoticeEndDate(request.getParameter(requestParams[4]+i));
				mobileNotices.add(mobileNotice);
			}
		}
		request.setAttribute(PROCESSING_STATUS, XMLFileHandler.generateXMLFile(new File(this.getServletContext().getRealPath(XML_MOBILE_MESSAGE_TEMPLATE_PATH)),new File(this.getServletContext().getRealPath(XML_MOBILE_MESSAGE_PATH)), XMLGenerator.generateXMLCode(mobileNotices)));
		doGet(request, response);
	}

	private boolean checkInputs(HttpServletRequest request, int row) {
		boolean checkInputStatus = true;
		for (String requestParam : requestParams) {
			checkInputStatus = checkInputStatus && !AppUtil.checkEmpty(request.getParameter(requestParam+row));
		}
		return checkInputStatus;
	}
	
}
