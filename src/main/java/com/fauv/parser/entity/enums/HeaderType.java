package com.fauv.parser.entity.enums;

public enum HeaderType {
	
	FILE_NAME("FILENAME", "/"),
	START_DATE("START_DATE", "="),
	START_TIME("START_TIME","="),
	END_DATE("END_DATE","="),
	END_TIME("END_TIME","="),
	PART_NUMBER("PART_NUMBER", "="),
	EQUIPMENT_NAME("EQUIPMENT_NAME","="),
	SAMPLE_ID("SAMPLE_ID","="),
	INSPECTOR("INSPECTOR", "=");
	
	private String name;
	private String separator;
	
	HeaderType(String name, String separator){
		this.separator = separator;
		this.name = name;
	}

	public String getSeparator() {
		return this.separator;
	}
	
	public String getName() {
		return name;
	}

	public String extractValue(String content) {
		if (content == null) { return ""; }
		
		String[] splitContent = content.split(this.getSeparator());
		
		if (splitContent.length <= 1) { return ""; }
		
		return splitContent[1];
	}
	
	
    /**
     * Identity type using begin of the prefix
     *
     *	E.g: FILENAME_1 : Type =  FILE_NAME
     *  E.g: START_DATE_1 : Type = START_DATE
     *
     */
	public static HeaderType getTypeUsingPrefixFileKey(String prefixFileKey) {
		HeaderType selected = null;
		
		for (HeaderType headerType : HeaderType.values()) {
			
			if (prefixFileKey.startsWith(headerType.getName())) {
				selected = headerType;
				break;
			}
			
		}
		
		return selected;
	}
		
}
