package com.rbc._3m00.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLPageGenerator {
	
	private static final String REPLACEMENT_TITLE = "REPLACEMENTTITLE";
	
	private static final String REPLACEMENT_CONTENT = "REPLACEMENTCONTENT";

	private static final String NEW_LINE = "\n";

	private static HTMLPageGenerator instance = new HTMLPageGenerator();
	
	
	private HTMLPageGenerator() {
		super();
	}

	public static HTMLPageGenerator getInstance() {
		return instance;
	}
	
	public void replaceContentInFile(File generatedHTMLFile, File templateHTMLFile, String replacementContent, String newTitle){
		String replacementText = System.getenv(REPLACEMENT_CONTENT);
		String replacementTitle = System.getenv(REPLACEMENT_TITLE);
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(templateHTMLFile));
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(generatedHTMLFile));
			String readLine = "";
			while((readLine = fileReader.readLine()) != null){
				if(readLine.contains(replacementText)){
					readLine = readLine.replace(replacementText, replacementContent);
				}else if(readLine.contains(replacementTitle)){
					readLine = readLine.replace(replacementTitle, newTitle);
				}
				fileWriter.write(readLine+NEW_LINE);
			}
			fileWriter.flush();
			fileWriter.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("HTMLPageGenerator.replaceContentInFile() - Unable to locate file = " + templateHTMLFile.getName());
		} catch (IOException e) {
			System.out.println("HTMLPageGenerator.replaceContentInFile() - IOException");
		}
	}
}
