package com.fauv.parser.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fauv.parser.entity.Coordinate;
import com.fauv.parser.entity.CoordinateValue;
import com.fauv.parser.entity.FM;
import com.fauv.parser.entity.MeasurementAxisCoordinate;
import com.fauv.parser.entity.NominalAxisCoordinate;
import com.fauv.parser.entity.PMP;
import com.fauv.parser.entity.enums.AxisType;
import com.fauv.parser.entity.enums.TolaranceType;
import com.fauv.parser.pattern.configuration.CarInformationPatternConfiguration;
import com.fauv.parser.pattern.configuration.LinePattern;
import com.fauv.parser.pattern.enums.LinePatternType;
import com.fauv.parser.reader.DMOInfo;
import com.fauv.parser.utils.Utils;

@Service
public class CarInformationService {
	private static Logger logger = LoggerFactory.getLogger(CarInformationService.class);
	
	private static final String SEPARATOR_NAME_AND_VALUES = "=";
	private static final String SEPARATOR_VALUES = ",";
	private static CarInformationPatternConfiguration pattern = CarInformationPatternConfiguration.getInstance();
		
	public FM buildFM(DMOInfo dmoCarInformation) throws Exception { 
		FM fm = new FM();
		
		String nominalAxisCoordinate = dmoCarInformation.getNominalItems().get(dmoCarInformation.getNominalItems().size()-1);
		String measurementAxisCoordinate = dmoCarInformation.getMeasurementItems().get(dmoCarInformation.getMeasurementItems().size()-1);
		
		fm.setName(extractFMName(dmoCarInformation.getNominalHeaderItem()));
		fm.setNominalAxisCoodinates(buildNominalAxisCoordinate(nominalAxisCoordinate));
		fm.setMeasurementAxisCoordinates(buildMeasurementAxisCoordinate(measurementAxisCoordinate));
		
		for (String item : dmoCarInformation.getNominalItems()) {
			LinePattern linePattern = pattern.whichLinePatternIs(item);
			
			if (linePattern == null) { continue; }
			
			if (linePattern.getType().isAnyCoordinateNominal()) {
				Coordinate coordinate = buildCoordinate(item, linePattern);
				
				fm.getNominalCoordinates().add(coordinate);
			}
		}
		
		for (String item : dmoCarInformation.getMeasurementItems()) {
			LinePattern linePattern = pattern.whichLinePatternIs(item);
			
			if (linePattern == null) { continue; }
			
			if (linePattern.getType().isAnyCoordinateMeasurement()) {
				Coordinate coordinate = buildCoordinate(item, linePattern);
				
				fm.getMeasurementCoordinates().add(coordinate);
			}
		}
		
		return fm;
	}
	
	public PMP buildPmp(DMOInfo dmoCarInformation) throws Exception {
		PMP pmp = new PMP();
		
		//Nominal and measurement is in first position always
		String nominalCoordinate = dmoCarInformation.getNominalItems().get(0);
		String mensurementCoordinate = dmoCarInformation.getMeasurementItems().get(0);
		
		pmp.setName(extractPMPName(dmoCarInformation.getNominalHeaderItem()));
		pmp.setNominalCoordinate(buildCoordinate(nominalCoordinate, pattern.whichLinePatternIs(nominalCoordinate)));
		pmp.setMeasurementCoordinate(buildCoordinate(mensurementCoordinate, pattern.whichLinePatternIs(mensurementCoordinate)));
		
		for (String nominalItem : dmoCarInformation.getNominalItems()) {
			LinePattern usedLinePattern = pattern.whichLinePatternIs(nominalItem);
			
			if (usedLinePattern == null) { continue; }
			
			if (usedLinePattern.getType().isAnyAxisCoordinateNominal()) {
				NominalAxisCoordinate axisCoordinate = buildNominalAxisCoordinate(nominalItem);
				
				pmp.getNominalAxisCoordinates().add(axisCoordinate);
			}
			
		}
		
		for (String measurementItem : dmoCarInformation.getMeasurementItems()) {
			LinePattern linePattern = pattern.whichLinePatternIs(measurementItem);
			
			if (linePattern == null) { continue; }
			
			if (linePattern.getType().isAnyAxisCoordinateMeasurement()) {
				MeasurementAxisCoordinate axisCoordinate = buildMeasurementAxisCoordinate(measurementItem);
				
				pmp.getMeasurementAxisCoordinates().add(axisCoordinate);
			}
			
		}
		
		return pmp;
	}
	 
	private Coordinate buildCoordinate(String line, LinePattern linePattern) {
		if (line == null || line.trim().isEmpty()) { return null; }
		
		logger.info("Building Coordinate using line: "+line);
		
		Coordinate coordinate = new Coordinate();
		
		String[] keyAndValues = line.split(SEPARATOR_NAME_AND_VALUES);
		String[] values = keyAndValues[1].split(SEPARATOR_VALUES);
		
		coordinate.setName(line);
		
		List<Integer> additionalInformationIndexs = linePattern.getAdditionalInformation().getValues();
		
		for (Integer index : additionalInformationIndexs) {
			if (index == -1) { continue; }
			
			CoordinateValue value = new CoordinateValue(Double.parseDouble(values[index]));
			
			coordinate.addNewCoordinate(value);
		}
		
		return coordinate;
	}
	
	private NominalAxisCoordinate buildNominalAxisCoordinate(String line) throws Exception {
		if (line == null || line.trim().isEmpty()) { return null; }
				
		NominalAxisCoordinate axisCoordinate = new NominalAxisCoordinate();
		
		String[] keyAndValues = line.split(SEPARATOR_NAME_AND_VALUES);
		String[] values = keyAndValues[1].split(SEPARATOR_VALUES);
		
		axisCoordinate.setName(Utils.extractName(keyAndValues[0]));
		
		LinePattern linePattern = pattern.whichLinePatternIs(line);
		
		if (linePattern == null) { 
			logger.info("Nominal Line Pattern not found for: "+ line);
			return axisCoordinate; 
		}
		
		if (linePattern.hasAdditionalInformation()) {
			for (int i = 0; i < linePattern.getAdditionalInformation().getValues().size(); i++) {
				Integer index = linePattern.getAdditionalInformation().getValues().get(i);
				
				if (i == 1 && index != -1) { axisCoordinate.setLowerTolerance((Double.parseDouble(values[index])));}
				if (i == 2 && index != -1) { axisCoordinate.setSuperiorTolerance((Double.parseDouble(values[index])));}
			}
		}
	
		for (String value : values) {
			LinePattern valueLinePattern = pattern.whichLinePatternIsUsingNominalAxisCoordinate(value);
			
			if (valueLinePattern == null) { continue; }
						
			if (!axisCoordinate.bothToleranceAreSet() && valueLinePattern.getType().equals(LinePatternType.AXIS_COORDINATE_TOLERANCE_NOMINAL)) {
				axisCoordinate.setTolerance(Double.parseDouble(value));
			}
			
			if (valueLinePattern.getType().equals(LinePatternType.AXIS_COORDINATE_AXIS_NOMINAL)) {
				axisCoordinate.setAxisType(AxisType.getAxisTypeByLinePatternName(valueLinePattern.getName()));
			}
			
		}
		
		return axisCoordinate;
	}
	
	private MeasurementAxisCoordinate buildMeasurementAxisCoordinate(String line) throws Exception {
		if (line == null || line.trim().isEmpty()) { return null; }
		
		logger.info(line);
		
		MeasurementAxisCoordinate axisCoordinate = new MeasurementAxisCoordinate();
		
		String[] keyAndValues = line.split(SEPARATOR_NAME_AND_VALUES);
		String[] values = keyAndValues[1].split(SEPARATOR_VALUES);
		
		axisCoordinate.setName(Utils.extractName(keyAndValues[0]));
		
		LinePattern linePattern = pattern.whichLinePatternIs(line);
		
		if (linePattern == null) { 
			logger.info("Measurement Line Pattern not found for: "+ line);
			return axisCoordinate; 
		}
				
		for (String value : values) {
			LinePattern valueLinePattern = pattern.whitchLinePatternIsUsingMeasurementAxisCoordinate(value);
			
			if (valueLinePattern == null) { continue; }
			
			if (valueLinePattern.getType().equals(LinePatternType.AXIS_COORDINATE_AXIS_MEASUREMENT)) {
				axisCoordinate.setAxisType(AxisType.getAxisTypeByLinePatternName(valueLinePattern.getName()));
			}
			
			if (valueLinePattern.getType().equals(LinePatternType.AXIS_COORDINATE_TOLERANCE_RESULT_MEASUREMENT)) {
				axisCoordinate.setType(TolaranceType.valueOf(value));
			}
		}
		
		for (int i = 0; i < linePattern.getAdditionalInformation().getValues().size(); i++) {
			Integer index = linePattern.getAdditionalInformation().getValues().get(i);
			
			if (i == 0 && index != -1) { axisCoordinate.setCalculated((Double.parseDouble(values[index]))); ; }
			if (i == 1 && index != -1) { axisCoordinate.setLowerTolerance((Double.parseDouble(values[index])));}
			if (i == 2 && index != -1) { axisCoordinate.setSuperiorTolerance((Double.parseDouble(values[index])));}
		}
		
		return axisCoordinate;
	}
	
	public boolean isValidDMOInfo(DMOInfo dmoCarInformation) {
		if (dmoCarInformation == null) { return false; }
		
		if (dmoCarInformation.getNominalItems().size() <= 1 && dmoCarInformation.getMeasurementItems().size() <= 1) { return false; }
		
		return true;
	}
	
	private String extractPMPName(String line) {
		if (line == null) { return null; }
		
		String[] lineValues = line.split(SEPARATOR_VALUES);
	
		return Utils.extractName(lineValues[0]);
	}
	
	private String extractFMName(String line) {
		if (line == null) { return null; }
		
		String[] lineValues = line.split(SEPARATOR_VALUES);
	
		return Utils.extractName(lineValues[lineValues.length-1]);
	}
	
}
