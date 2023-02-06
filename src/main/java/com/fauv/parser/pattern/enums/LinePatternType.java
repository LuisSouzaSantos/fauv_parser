package com.fauv.parser.pattern.enums;

public enum LinePatternType {
	HEADER_FILENAME,
	HEADER_START_DATE,
	HEADER_START_TIME,
	HEADER_END_DATE,
	HEADER_END_TIME,
	HEADER_PART_NUMBER,
	HEADER_EQUIPMENT_NAME,
	HEADER_SAMPLE_ID,
	HEADER_INSPECTOR,
	COORDINATE_NOMINAL_SPHERE,
	COORDINATE_NOMINAL_CIRCLE,
	COORDINATE_NOMINAL_DEFAULT,
	COORDINATE_MEASUREMENT_SPHERE,
	COORDINATE_MEASUREMENT_CIRCLE,
	COORDINATE_MEASUREMENT_DEFAULT,
	AXIS_COORDINATE_NOMINAL_DEFAULT,
	AXIS_COORDINATE_NOMINAL_DISTB,
	AXIS_COORDINATE_NOMINAL_PROFS,
	AXIS_COORDINATE_TOLERANCE_NOMINAL,
	AXIS_COORDINATE_AXIS_NOMINAL,
	AXIS_COORDINATE_MEASUREMENT_DEFAULT,
	AXIS_COORDINATE_MEASUREMENT_DISTB,
	AXIS_COORDINATE_MEASUREMENT_PROFS,
	AXIS_COORDINATE_AXIS_MEASUREMENT,
	AXIS_COORDINATE_TOLERANCE_MEASUREMENT,
	AXIS_COORDINATE_TOLERANCE_RESULT_MEASUREMENT;
		
	public static LinePatternType whichIs(String line) {
		LinePatternType selectedLinePatternType = null;
		
		for (LinePatternType linePatternType : LinePatternType.values()) {
			if (linePatternType.name().equals(line) || line.startsWith(linePatternType.name())) {
				selectedLinePatternType = linePatternType;
				break;
			}
		}
		
		return selectedLinePatternType;
	}
	
	public boolean isAnyHeaderType() {
		return this.equals(LinePatternType.HEADER_FILENAME) || this.equals(LinePatternType.HEADER_START_DATE)
				|| this.equals(LinePatternType.HEADER_START_TIME) || this.equals(LinePatternType.HEADER_END_DATE)
				|| this.equals(LinePatternType.HEADER_END_TIME) || this.equals(LinePatternType.HEADER_PART_NUMBER)
				|| this.equals(LinePatternType.HEADER_EQUIPMENT_NAME) || this.equals(LinePatternType.HEADER_SAMPLE_ID)
				|| this.equals(LinePatternType.HEADER_INSPECTOR);
	}
		
	public boolean isAnyCoordinateNominal () {
		return this.equals(LinePatternType.COORDINATE_NOMINAL_SPHERE)
				|| this.equals(LinePatternType.COORDINATE_NOMINAL_CIRCLE)
				|| this.equals(LinePatternType.COORDINATE_NOMINAL_DEFAULT);
	} 
	
	public boolean isAnyCoordinateMeasurement () {
		return this.equals(LinePatternType.COORDINATE_MEASUREMENT_SPHERE)
				|| this.equals(LinePatternType.COORDINATE_MEASUREMENT_CIRCLE)
				|| this.equals(LinePatternType.COORDINATE_MEASUREMENT_DEFAULT);
	} 
	
	public boolean isDifferentNominalInfo() {
		return this.equals(LinePatternType.AXIS_COORDINATE_TOLERANCE_NOMINAL)
				|| this.equals(LinePatternType.AXIS_COORDINATE_AXIS_NOMINAL);
	}
	
	public boolean isDifferentMeasurementInfo() {
		return this.equals(LinePatternType.AXIS_COORDINATE_AXIS_MEASUREMENT)
				|| this.equals(LinePatternType.AXIS_COORDINATE_TOLERANCE_MEASUREMENT)
				|| this.equals(LinePatternType.AXIS_COORDINATE_TOLERANCE_RESULT_MEASUREMENT);
	}
	
	public boolean isAnyAxisCoordinateNominal() {
		return this.equals(LinePatternType.AXIS_COORDINATE_NOMINAL_DEFAULT)
				|| this.equals(LinePatternType.AXIS_COORDINATE_NOMINAL_DISTB)
				|| this.equals(LinePatternType.AXIS_COORDINATE_NOMINAL_PROFS);
	}
	
	public boolean isAnyAxisCoordinateMeasurement() {
		return this.equals(LinePatternType.AXIS_COORDINATE_MEASUREMENT_DEFAULT)
				|| this.equals(LinePatternType.AXIS_COORDINATE_MEASUREMENT_DISTB)
				|| this.equals(LinePatternType.AXIS_COORDINATE_MEASUREMENT_PROFS);
	}
	
}
