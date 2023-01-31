package com.fauv.parser.entity;

import java.util.ArrayList;
import java.util.List;

import com.fauv.parser.entity.enums.AxisType;

public class Coordinate {

	private String name;
	
	// The sequence is X, Y, Z, D and T
	private List<CoordinateValue> values = new ArrayList<CoordinateValue>();
	private AxisType workingOnAxis;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<CoordinateValue> getValues() {
		return values;
	}

	public void setValues(List<CoordinateValue> values) {
		this.values = values;
	}

	public AxisType getWorkingOnAxis() {
		return workingOnAxis;
	}
	
	public void setWorkingOnAxis(AxisType workingOnAxis) {
		this.workingOnAxis = workingOnAxis;
	}
	
	public void addNewCoordinate(CoordinateValue coordinateValue) {
		coordinateValue.setAxisType(defineNewAxisTypeAccordingIndex());
		this.getValues().add(coordinateValue);
		
	}
	
	private AxisType defineNewAxisTypeAccordingIndex() {
		if (values.size() == 0) { return AxisType.X; }
		if (values.size() == 1) { return AxisType.Y; }
		if (values.size() == 2) { return AxisType.Z; }
		if (values.size() == 3) { return AxisType.D; }
		if (values.size() == 4) { return AxisType.T; }
		
		return null;
	}
	
}
