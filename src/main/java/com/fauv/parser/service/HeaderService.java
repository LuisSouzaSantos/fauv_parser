package com.fauv.parser.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fauv.parser.entity.Header;
import com.fauv.parser.entity.enums.HeaderType;
import com.fauv.parser.pattern.configuration.HeaderPatternConfiguration;
import com.fauv.parser.pattern.configuration.LinePattern;
import com.fauv.parser.reader.DMOHeader;

@Service
public class HeaderService {
	
	private static Logger logger = LoggerFactory.getLogger(HeaderService.class);
	
	private static HeaderPatternConfiguration headerPatternConfiguration = HeaderPatternConfiguration.getInstance();
	
	public Header buildHeader(DMOHeader dmoHeaderInformation) throws Exception {
		Header header = new Header();
		
		for (String information : dmoHeaderInformation.getInformations()) {
			
			Map<HeaderType, List<LinePattern>> patterns = headerPatternConfiguration.getRegexLines();
			
			HeaderType type = null;
			boolean found = false;
			
			for (Entry<HeaderType, List<LinePattern>> entry : patterns.entrySet()) {
				for (LinePattern linePattern : entry.getValue()) {
					found = information.matches(linePattern.getPattern());

					if (found) {
						type = entry.getKey();
						break;
					}
				}
			}
			
			logger.debug("");
			
			if (type != null) { header.buildingHeader(type, type.extractValue(information)); }
			
		}
		
		return header;
	}

}
