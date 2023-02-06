package com.fauv.parser.entity;

import java.util.ArrayList;
import java.util.List;

//This entity define a Car FM
public class FM {

	private String name;
	private List<Coordinate> nominalCoordinates = new ArrayList<Coordinate>();
	private NominalAxisCoordinate nominalAxisCoodinates;
	private List<Coordinate> measurementCoordinates = new ArrayList<Coordinate>();
	private MeasurementAxisCoordinate measurementAxisCoordinates;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Coordinate> getNominalCoordinates() {
		return nominalCoordinates;
	}
	
	public void setNominalCoordinates(List<Coordinate> nominalCoordinates) {
		this.nominalCoordinates = nominalCoordinates;
	}
	
	public NominalAxisCoordinate getNominalAxisCoodinates() {
		return nominalAxisCoodinates;
	}
	
	public void setNominalAxisCoodinates(NominalAxisCoordinate nominalAxisCoodinates) {
		this.nominalAxisCoodinates = nominalAxisCoodinates;
	}
	
	public List<Coordinate> getMeasurementCoordinates() {
		return measurementCoordinates;
	}
	
	public void setMeasurementCoordinates(List<Coordinate> measurementCoordinates) {
		this.measurementCoordinates = measurementCoordinates;
	}
	
	public MeasurementAxisCoordinate getMeasurementAxisCoordinates() {
		return measurementAxisCoordinates;
	}
	
	public void setMeasurementAxisCoordinates(MeasurementAxisCoordinate measurementAxisCoordinates) {
		this.measurementAxisCoordinates = measurementAxisCoordinates;
	}
	
	
	
}
