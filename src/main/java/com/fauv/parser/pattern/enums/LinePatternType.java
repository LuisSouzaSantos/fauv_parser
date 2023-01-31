package com.fauv.parser.pattern.enums;

public enum LinePatternType {
	HEADER, 
	COORDINATE_NOMINAL, 
	COORDINATE_MEASUREMENT,
	AXIS_COORDINATE_NOMINAL,
	AXIS_COORDINATE_NOMINAL_TOLERANCE,
	AXIS_COORDINATE_NOMINAL_AXIS,
	AXIS_COORDINATE_MEASUREMENT,
	AXIS_COORDINATE_MEASUREMENT_AXIS,
	AXIS_COORDINATE_MEASUREMENT_TOLERANCE,
	AXIS_COORDINATE_MEASUREMENT_TOLERANCE_RESULT;
	
	public static LinePatternType whichIs(String type) {
		LinePatternType selectedLinePatternType = null;
		
		for (LinePatternType linePatternType : LinePatternType.values()) {
			if (linePatternType.name().equals(type) || type.startsWith(linePatternType.name())) {
				selectedLinePatternType = linePatternType;
				break;
			}
		}
		
		return selectedLinePatternType;
	}
	
}
