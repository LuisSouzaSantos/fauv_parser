package com.fauv.parser.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum AxisType {
	X("AXIS_COORDINATE_NOMINAL_X", "AXIS_COORDINATE_AXIS_NOMINAL_X", "AXIS_COORDINATE_AXIS_MEASUREMENT_X",
			"AXIS_COORDINATE_NOMINAL_DEFAULT_X", "AXIS_COORDINATE_NOMINAL_DISTB_X", "AXIS_COORDINATE_NOMINAL_PROFS_X",
			"AXIS_COORDINATE_MEASUREMENT_DEFAULT_X", "AXIS_COORDINATE_MEASUREMENT_DISTB_X", "AXIS_COORDINATE_MEASUREMENT_PROFS_X"),
	
	Y("AXIS_COORDINATE_NOMINAL_Y", "AXIS_COORDINATE_AXIS_NOMINAL_Y", "AXIS_COORDINATE_AXIS_MEASUREMENT_Y",
			"AXIS_COORDINATE_NOMINAL_DEFAULT_Y", "AXIS_COORDINATE_NOMINAL_DISTB_Y", "AXIS_COORDINATE_NOMINAL_PROFS_Y",
			"AXIS_COORDINATE_MEASUREMENT_DEFAULT_Y", "AXIS_COORDINATE_MEASUREMENT_DISTB_Y", "AXIS_COORDINATE_MEASUREMENT_PROFS_Y"),
	
	Z("AXIS_COORDINATE_NOMINAL_Z", "AXIS_COORDINATE_AXIS_NOMINAL_Z", "AXIS_COORDINATE_AXIS_MEASUREMENT_Z",
			"AXIS_COORDINATE_NOMINAL_DEFAULT_Z", "AXIS_COORDINATE_NOMINAL_DISTB_Z", "AXIS_COORDINATE_NOMINAL_PROFS_Z",
			"AXIS_COORDINATE_MEASUREMENT_DEFAULT_Z", "AXIS_COORDINATE_MEASUREMENT_DISTB_Z", "AXIS_COORDINATE_MEASUREMENT_PROFS_Z"), 
	
	T("AXIS_COORDINATE_NOMINAL_DEFAULT_T", "AXIS_COORDINATE_NOMINAL_DISTB_T", "AXIS_COORDINATE_NOMINAL_PROFS_T",
			"AXIS_COORDINATE_MEASUREMENT_DISTB_T", "AXIS_COORDINATE_MEASUREMENT_DEFAULT_T",
			"AXIS_COORDINATE_MEASUREMENT_PROFS_T"),
	D("AXIS_COORDINATE_NOMINAL_DEFAULT_D", "AXIS_COORDINATE_NOMINAL_DISTB_D", "AXIS_COORDINATE_NOMINAL_PROFS_D",
			"AXIS_COORDINATE_NOMINAL_DIAM_D", "AXIS_COORDINATE_MEASUREMENT_DISTB_D", "AXIS_COORDINATE_MEASUREMENT_DEFAULT_D",
			"AXIS_COORDINATE_MEASUREMENT_PROFS_D", "AXIS_COORDINATE_MEASUREMENT_DIAM_D");
	
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
