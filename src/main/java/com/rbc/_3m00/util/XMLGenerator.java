package com.rbc._3m00.util;

import java.util.ArrayList;

import com.rbc._3m00.dto.MobileNotice;

public class XMLGenerator {
	
	private XMLGenerator(){
		
	}
	
	private static final String NEW_LINE = "\n";

	private static String MOBILE_NOTICE_LIST_XML_START_TAG = "<noticeList>";
	
	private static String MOBILE_NOTICE_LIST_XML_END_TAG = "</noticeList>";
	
	private static String MOBILE_NOTICE_HEADER_XML_START_TAG = "<header>";
	
	private static String MOBILE_NOTICE_HEADER_XML_END_TAG = "</header>";
	
	private static String MOBILE_NOTICE_DESCRIPTION_XML_START_TAG = "<description>";
	
	private static String MOBILE_NOTICE_DESCRIPTION_XML_END_TAG = "</description>";
	
	private static String MOBILE_NOTICE_PLATFORM_XML_START_TAG = "<platfrom>";
	
	private static String MOBILE_NOTICE_PLATFORM_XML_END_TAG = "</platform>";
	
	private static String MOBILE_NOTICE_START_DATE_XML_START_TAG = "<startDate>";
	
	private static String MOBILE_NOTICE_START_DATE_XML_END_TAG = "</startDate>";
	
	private static String MOBILE_NOTICE_END_DATE_XML_START_TAG = "<endDate>";
	
	private static String MOBILE_NOTICE_END_DATE_XML_END_TAG = "</endDate>";
	
	public static String generateXMLCode(ArrayList<MobileNotice> mobileNotices){
		StringBuilder xmlCode = new StringBuilder();
		for (MobileNotice mobileNotice : mobileNotices) {
			xmlCode.append(MOBILE_NOTICE_LIST_XML_START_TAG).append(NEW_LINE);
			xmlCode.append(MOBILE_NOTICE_HEADER_XML_START_TAG).append(mobileNotice.getMobileNoticeHeader()).append(MOBILE_NOTICE_HEADER_XML_END_TAG).append(NEW_LINE);
			xmlCode.append(MOBILE_NOTICE_DESCRIPTION_XML_START_TAG).append(mobileNotice.getMobileNoticeDescription()).append(MOBILE_NOTICE_DESCRIPTION_XML_END_TAG).append(NEW_LINE);
			xmlCode.append(MOBILE_NOTICE_PLATFORM_XML_START_TAG).append(mobileNotice.getMobileNoticePlatform()).append(MOBILE_NOTICE_PLATFORM_XML_END_TAG).append(NEW_LINE);
			xmlCode.append(MOBILE_NOTICE_START_DATE_XML_START_TAG).append(mobileNotice.getMobileNoticeStartDate()).append(MOBILE_NOTICE_START_DATE_XML_END_TAG).append(NEW_LINE);
			xmlCode.append(MOBILE_NOTICE_END_DATE_XML_START_TAG).append(mobileNotice.getMobileNoticeEndDate()).append(MOBILE_NOTICE_END_DATE_XML_END_TAG).append(NEW_LINE);
			xmlCode.append(MOBILE_NOTICE_LIST_XML_END_TAG).append(NEW_LINE);
		}
		
		System.out.println("XMLGenerator.generateXMLCode() ::" + xmlCode.toString());
		return xmlCode.toString();
	}
	
}
