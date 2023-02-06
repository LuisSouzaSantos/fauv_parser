package com.fauv.parser.reader;

import java.util.ArrayList;
import java.util.List;

public class DMOInfo {

	private String nominalHeaderItem;
	private List<String> nominalItems = new ArrayList<String>();
	private String measurementHeaderItem;
	private List<String> measurementItems = new ArrayList<String>();
	
	public String getNominalHeaderItem() {
		return nominalHeaderItem;
	}
	
	public void setNominalHeaderItem(String nominalHeaderItem) {
		this.nominalHeaderItem = nominalHeaderItem;
	}
	
	public List<String> getNominalItems() {
		return nominalItems;
	}
	
	public void setNominalItems(List<String> nominalItems) {
		this.nominalItems = nominalItems;
	}
	
	public String getMeasurementHeaderItem() {
		return measurementHeaderItem;
	}
	
	public void setMeasurementHeaderItem(String measurementHeaderItem) {
		this.measurementHeaderItem = measurementHeaderItem;
	}
	
	public List<String> getMeasurementItems() {
		return measurementItems;
	}
	
	public void setMeasurementItems(List<String> measurementItems) {
		this.measurementItems = measurementItems;
	}
	
}
