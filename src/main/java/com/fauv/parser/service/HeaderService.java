package com.fauv.parser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fauv.parser.entity.Header;
import com.fauv.parser.entity.enums.HeaderType;
import com.fauv.parser.pattern.configuration.HeaderPattern;
import com.fauv.parser.reader.DMOHeader;

@Service
public class HeaderService {
	
	private static Logger logger = LoggerFactory.getLogger(HeaderService.class);
	
	private static HeaderPattern headerPattern = HeaderPattern.getInstance();
	
	public Header buildHeader(DMOHeader dmoHeaderInformation) throws Exception {
		Header header = new Header();
		
		for (String information : dmoHeaderInformation.getInformations()) {
						
			HeaderType type = headerPattern.getHeaderTypeByValue(information);
			
			if (type == null) {  
				logger.debug("Header Type Not Found: "+information);
				continue;
			}
			
			header.buildingHeader(type, type.extractValue(information));
		}
		
		return header;
	}
	
}
