package com.fauv.parser.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fauv.parser.pattern.configuration.CarInformationPatternConfiguration;
import com.fauv.parser.pattern.configuration.HeaderPatternConfiguration;
import com.fauv.parser.pattern.configuration.IgnorePatternConfiguration;
import com.fauv.parser.reader.DMO;
import com.fauv.parser.reader.DMOInfo;

public class DMOService {
	
	private static Logger logger = LoggerFactory.getLogger(DMOService.class);

	private static HeaderPatternConfiguration headerPatternConfiguration = HeaderPatternConfiguration.getInstance();
	private static CarInformationPatternConfiguration carInformationPatternConfiguration = CarInformationPatternConfiguration.getInstance();
	private static IgnorePatternConfiguration ignorePatternConfiguration = IgnorePatternConfiguration.getInstance();
	
	//Responsable to read DMO file and separed in Header and Car Info blocks
	public static DMO readDMOFileAndBuildIt(String fileName) throws Exception {
		File file = new File(fileName);
		
		if (!file.exists()) { return null; }
		
		DMO dmo = new DMO();
		DMOInfo carInformation = null;
		
		boolean isHeaderInformation = false;
		boolean isCarInformation = false;
		boolean extractDefaultPart = false;
		boolean extractMeasuremntPart = false;
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	logger.debug("Reading Line: "+line);
		    	
		    	// Ignore line if needed
		    	if (ignorePatternConfiguration.ignoreThisLine(line)) {
		    		logger.debug("Ignoring Line: "+line);
		    		continue; 
		    	}
		    	
		    	// Stop capturing header info
		    	if (isHeaderInformation && headerPatternConfiguration.isEnd(line)) {
		    		isHeaderInformation = false;
		    		continue;
		    	}
		    	
		    	// Stop capturing car info
		    	if (isCarInformation && carInformationPatternConfiguration.isEnd(line)) {
		    		dmo.getCarInformationList().add(carInformation);
		    		isCarInformation = false;
		    		continue;
		    	}
		    	
		    	if (!isHeaderInformation && !isCarInformation) {
		    		isHeaderInformation = headerPatternConfiguration.isBegin(line);
		    		isCarInformation = carInformationPatternConfiguration.isBegin(line);
		    		
		    		if (isCarInformation) { carInformation = new DMOInfo(); }
		    	}
		    	
		    	// Capture header car info
		    	if (isHeaderInformation) {
		    		logger.debug("Header Line: "+line);
		    		
		    		dmo.getHeader().getInformations().add(line);
		    	}
		    	
		    	// Capture car info
		    	if (isCarInformation) {
		    		boolean isBeginOfBlock = carInformationPatternConfiguration.isBegin(line);
		    		
		    		if (isBeginOfBlock && carInformation.getDefaultHeaderItems().isEmpty()) {
		    			logger.debug("Car Default Header info: "+line);
		    			carInformation.getDefaultHeaderItems().add(line);
		    			extractDefaultPart = true;
		    		} else if (!isBeginOfBlock && extractDefaultPart) {
		    			logger.debug("Car Default info: "+line);
		    			carInformation.getDefaultItems().add(line);
		    		} else if (isBeginOfBlock && carInformation.getMeasurementHeaderItems().isEmpty()) {
		    			logger.debug("Car Measurement Header info: "+line);
		    			carInformation.getMeasurementHeaderItems().add(line);
		    			extractDefaultPart = false;
		    			extractMeasuremntPart = true;
		    		} else if (!isBeginOfBlock && extractMeasuremntPart) {
		    			logger.debug("Car Measurement info: "+line);
		    			carInformation.getMeasurementItems().add(line);
		    		} else {
		    			logger.debug("Car info Line not captured: "+line);
		    		}
		    	}
		    	
		    }
		    
		}
				
		return dmo;
	}
	
}
