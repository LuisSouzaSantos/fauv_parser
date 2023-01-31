package com.fauv.parser.reader;

import java.util.ArrayList;
import java.util.List;

public class DMOInfo {

	private List<String> defaultHeaderItems = new ArrayList<String>();
	private List<String> defaultItems = new ArrayList<String>();
	private List<String> measurementHeaderItems = new ArrayList<String>();
	private List<String> measurementItems = new ArrayList<String>();
	
	public List<String> getDefaultHeaderItems() {
		return defaultHeaderItems;
	}
	
	public void setDefaultHeaderItems(List<String> defaultHeaderItems) {
		this.defaultHeaderItems = defaultHeaderItems;
	}
	
	public List<String> getDefaultItems() {
		return defaultItems;
	}
	
	public void setDefaultItems(List<String> defaultItems) {
		this.defaultItems = defaultItems;
	}
	
	public List<String> getMeasurementHeaderItems() {
		return measurementHeaderItems;
	}

	public void setMeasurementHeaderItems(List<String> measurementHeaderItems) {
		this.measurementHeaderItems = measurementHeaderItems;
	}

	public List<String> getMeasurementItems() {
		return measurementItems;
	}
	
	public void setMeasurementItems(List<String> measurementItems) {
		this.measurementItems = measurementItems;
	}
	
}
