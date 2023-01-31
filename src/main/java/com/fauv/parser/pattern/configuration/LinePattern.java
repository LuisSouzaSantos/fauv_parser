package com.fauv.parser.pattern.configuration;

import com.fauv.parser.pattern.enums.LinePatternType;

public class LinePattern {

	private String name;
	private String pattern;
	private LinePatternType type;
	private AdditionalInformation additionalInformation;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPattern() {
		return pattern;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public LinePatternType getType() {
		return type;
	}
	
	public void setType(LinePatternType type) {
		this.type = type;
	}

	public AdditionalInformation getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

}
