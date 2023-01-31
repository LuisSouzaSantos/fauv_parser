package com.fauv.parser.entity;

import com.fauv.parser.entity.enums.AxisType;


public class CoordinateValue {

	private double value;
	private AxisType axis;
	
	public CoordinateValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public AxisType getAxisType() {
		return axis;
	}
	
	public void setAxisType(AxisType axis) {
		this.axis = axis;
	}
	
	@Override
	public String toString() {
		return "Value: "+ Double.toString(this.value) + " Eixo: " + axis.name();
	}
	
}
