package com.fauv.parser.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	public static String readFile(File file) throws FileNotFoundException, IOException {		
		if (!file.exists()) { return null; }
		
		String content = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {		    	
		    	content = content + line + "\r\n";
		    }
		    
		}
		
		return content;
	}
	
	public static String extractName(String value) {
		return StringUtils.getStringBetweenTwoCharacters(value, "(", ")");
	}
	
}
