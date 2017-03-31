package com.rbc._3m00.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class XMLFileHandler {
	
	private XMLFileHandler(){
		
	}
	
	private static String XML_BODY_CONTENT_REPLACEMENT_PATTERN = "<!-- XML Body Content -->";
	
	public static boolean generateXMLFile(File xmlTemplateFile, File xmlGeneratedFile, String xmlCode){
		boolean processingStatus = false;
		BufferedReader fileReader = null;
		BufferedWriter fileWriter = null;
		try {
			fileReader = new BufferedReader(new FileReader(xmlTemplateFile));
			fileWriter = new BufferedWriter(new FileWriter(xmlGeneratedFile));
			String readLine = "";
			
			while((readLine = fileReader.readLine()) != null) {
				if(readLine.contains(XML_BODY_CONTENT_REPLACEMENT_PATTERN))
					readLine = xmlCode;
				fileWriter.write(readLine+AppUtil.NEW_LINE);
			}
			processingStatus = true;
		} catch (FileNotFoundException e) {
			System.out.println("XMLFileHandler.generateXMLFile() :: File not found " + xmlTemplateFile.getName());
			processingStatus = false;
		} catch (IOException e) {
			System.out.println("XMLFileHandler.generateXMLFile() :: IOException " + xmlGeneratedFile.getName());
			processingStatus = false;
		}finally {
			try {
				fileReader.close();
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("XMLFileHandler.generateXMLFile() - IO Exception while trying to close fileReader");
				processingStatus = false;
			}
		}
		
	return processingStatus;
	}

}
