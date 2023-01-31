package com.fauv.parser.entity;

import com.fauv.parser.entity.enums.TolaranceType;

public class MeasurementAxisCoordinate extends AxisCoordinate {

	private Double calculated;
	private TolaranceType type;

	public Double getCalculated() {
		return calculated;
	}

	public void setCalculated(Double calculated) {
		this.calculated = calculated;
	}

	public TolaranceType getType() {
		return type;
	}

	public void setType(TolaranceType type) {
		this.type = type;
	}

}
