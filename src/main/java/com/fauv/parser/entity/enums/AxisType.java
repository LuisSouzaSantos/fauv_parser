package com.fauv.parser.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum AxisType {
	X("AXIS_COORDINATE_NOMINAL_X", "AXIS_COORDINATE_AXIS_NOMINAL_X", "AXIS_COORDINATE_AXIS_MEASUREMENT_X"), 
	Y("AXIS_COORDINATE_NOMINAL_Y", "AXIS_COORDINATE_AXIS_NOMINAL_Y", "AXIS_COORDINATE_AXIS_MEASUREMENT_Y"), 
	Z("AXIS_COORDINATE_NOMINAL_Z", "AXIS_COORDINATE_AXIS_NOMINAL_Z", "AXIS_COORDINATE_AXIS_MEASUREMENT_Z"), 
	T("AXIS_COORDINATE_NOMINAL_T"), 
	D("AXIS_COORDINATE_NOMINAL_D");
	
	private List<String> linePatterIndentifiers;
	
	private AxisType(String... linePatterIndentifiers) {
		this.linePatterIndentifiers = Arrays.asList(linePatterIndentifiers);
	}

	public List<String> getLinePatterIndentifier() {
		return linePatterIndentifiers;
	}

	public static AxisType getAxisTypeByLinePatternName(String linePatternName) {
		AxisType type = null;
		
		for (AxisType value : AxisType.values()) {
			if (value.getLinePatterIndentifier().stream().anyMatch(linePatterIndentifier -> linePatternName.startsWith(linePatterIndentifier))) {
				type = value;
				break;
			}
		}
		
		return type;
	}
	
	
}
