package com.fauv.parser.entity;

import java.util.ArrayList;
import java.util.List;

//This entity define a Car FM
public class PMPOrFM {

	private String name;
	private List<Coordinate> nominalCoordinates = new ArrayList<Coordinate>();
	private List<NominalAxisCoordinate> nominalAxisCoodinates = new ArrayList<NominalAxisCoordinate>();
	private List<Coordinate> measurementCoordinates = new ArrayList<Coordinate>();
	private List<MeasurementAxisCoordinate> measurementAxisCoordinates = new ArrayList<MeasurementAxisCoordinate>();
	
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
	
	public List<NominalAxisCoordinate> getNominalAxisCoodinates() {
		return nominalAxisCoodinates;
	}
	
	public void setNominalAxisCoodinates(List<NominalAxisCoordinate> nominalAxisCoodinates) {
		this.nominalAxisCoodinates = nominalAxisCoodinates;
	}
	
	public List<Coordinate> getMeasurementCoordinates() {
		return measurementCoordinates;
	}
	
	public void setMeasurementCoordinates(List<Coordinate> measurementCoordinates) {
		this.measurementCoordinates = measurementCoordinates;
	}
	
	public List<MeasurementAxisCoordinate> getMeasurementAxisCoordinates() {
		return measurementAxisCoordinates;
	}
	
	public void setMeasurementAxisCoordinates(List<MeasurementAxisCoordinate> measurementAxisCoordinates) {
		this.measurementAxisCoordinates = measurementAxisCoordinates;
	}
	
}
