package com.fauv.parser.entity;

import java.util.ArrayList;
import java.util.List;

//This entity define a Car PMP
public class PMP {

	private String name;
	private Coordinate nominalCoordinate;
	private List<NominalAxisCoordinate> nominalAxisCoordinates = new ArrayList<>();
	private Coordinate measurementCoordinate;
	private List<MeasurementAxisCoordinate> measurementAxisCoordinates = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Coordinate getNominalCoordinate() {
		return nominalCoordinate;
	}
	
	public void setNominalCoordinate(Coordinate nominalCoordinate) {
		this.nominalCoordinate = nominalCoordinate;
	}
	
	public List<NominalAxisCoordinate> getNominalAxisCoordinates() {
		return nominalAxisCoordinates;
	}
	
	public void setNominalAxisCoordinates(List<NominalAxisCoordinate> nominalAxisCoordinates) {
		this.nominalAxisCoordinates = nominalAxisCoordinates;
	}
	
	public Coordinate getMeasurementCoordinate() {
		return measurementCoordinate;
	}
	
	public void setMeasurementCoordinate(Coordinate measurementCoordinate) {
		this.measurementCoordinate = measurementCoordinate;
	}
	
	public List<MeasurementAxisCoordinate> getMeasurementAxisCoordinates() {
		return measurementAxisCoordinates;
	}
	
	public void setMeasurementAxisCoordinates(List<MeasurementAxisCoordinate> measurementAxisCoordinates) {
		this.measurementAxisCoordinates = measurementAxisCoordinates;
	}

}
