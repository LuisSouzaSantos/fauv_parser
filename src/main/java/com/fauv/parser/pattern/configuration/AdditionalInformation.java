package com.fauv.parser.pattern.configuration;

import java.util.ArrayList;
import java.util.List;

import com.fauv.parser.pattern.enums.AdditionalInformationType;

public class AdditionalInformation {

	private String name;
	private AdditionalInformationType type;
	
	// - COORDINATE
	//
	// For coordinate you will get values of indexOfAxisX, indexOfAxisY, indexOfAxisZ and indexOfAxisD in sequence.
	//
	// - AXIS COORDINATE
	//
	// - Nominal
	//
	// For axis coordinate measurement you will get values of possibleMeasurementIndex, possibleLowTolaranceIndex and possibleSuperiorTolaranceIndex in sequence. 
	//
	// - Measuremet
	// For axis coordinate measurement you will get values of possibleMeasurementIndex, possibleLowTolaranceIndex and possibleSuperiorTolaranceIndex in sequence.
	private List<Integer> values = new ArrayList<Integer>();
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public AdditionalInformationType getType() {
		return type;
	}

	public void setType(AdditionalInformationType type) {
		this.type = type;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}
	
}
