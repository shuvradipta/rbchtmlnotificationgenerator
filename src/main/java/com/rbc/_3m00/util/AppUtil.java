package com.rbc._3m00.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class AppUtil {
	
	private static final String COMMA = ",";

	private static AppUtil instance = new AppUtil();
	
	private static String[] appEnvMappings = {"HTMLGENAPPURL" , "SERVICENOTICEGENAPPURL", "HOMEDOMAIN", "MAX_MOBILE_NOTICE_COUNT"};
	
	private static final String TIMEZONE_AMERICA_TORONTO = "America/Toronto";
	
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";
	
	public static final String NEW_LINE = "\n";
	
	private AppUtil(){
		super();
	}
	
	public static AppUtil getInstance(){
		return instance;
	}
	
	public HashMap<String,String> initializeAppConfigMappings(){
		HashMap<String,String> appConfigMappings = new HashMap<String, String>();
		for (String envVar : appEnvMappings) {
			appConfigMappings.put(envVar, System.getenv(envVar));
		}
		System.out.println("appConfigMappings :: " + appConfigMappings);
		return appConfigMappings;
		
	}
	
	public String getServerDateTime(String dateFormatParam){
		TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_AMERICA_TORONTO));
		//System.out.println("Default TimeZone ==>" + TimeZone.getDefault().getID());
		Calendar serverTime = Calendar.getInstance(TimeZone.getDefault());
		
		if(null == dateFormatParam || "".equalsIgnoreCase(dateFormatParam.trim()))
			dateFormatParam = DEFAULT_DATE_FORMAT;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatParam);
		System.out.println(dateFormat.format(serverTime.getTime()));
		return dateFormat.format(serverTime.getTime());
	}
	
	public static boolean checkEmpty(String testString){
		return null == testString || "null".equalsIgnoreCase(testString) || "".equalsIgnoreCase(testString.trim());
	}
	
	public static String convertArraytoString(String[] stringArray){
		StringBuilder convertedArrayText = new StringBuilder();
		if(null != stringArray){
			for (int i = 0; i < stringArray.length; i++) {
				convertedArrayText.append(stringArray[i]).append(COMMA);
			}
		}	
		convertedArrayText.deleteCharAt(convertedArrayText.length()-1);
		return convertedArrayText.toString();
	}

}
