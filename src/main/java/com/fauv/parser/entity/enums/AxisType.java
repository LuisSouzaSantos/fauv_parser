package com.fauv.parser.entity.enums;

public enum AxisType {
	X("AXIS_COORDINATE_NOMINAL_X"), 
	Y("AXIS_COORDINATE_NOMINAL_Y"), 
	Z("AXIS_COORDINATE_NOMINAL_Z"), 
	T("AXIS_COORDINATE_NOMINAL_T"), 
	D("AXIS_COORDINATE_NOMINAL_D");
	
	private String linePatterIndentifier;
	
	private AxisType(String linePatterIndentifier) {
		this.linePatterIndentifier = linePatterIndentifier;
	}

	public String getLinePatterIndentifier() {
		return linePatterIndentifier;
	}

	public static AxisType getAxisTypeByLinePatternName(String linePatternName) {
		AxisType type = null;
		
		for (AxisType value : AxisType.values()) {
			if (value.getLinePatterIndentifier().startsWith(linePatternName)) {
				type = value;
				break;
			}
		}
		
		return type;
	}
	
	
}
