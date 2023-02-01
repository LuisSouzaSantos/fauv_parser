package com.fauv.parser.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fauv.parser.entity.Coordinate;
import com.fauv.parser.entity.CoordinateValue;
import com.fauv.parser.entity.MeasurementAxisCoordinate;
import com.fauv.parser.entity.NominalAxisCoordinate;
import com.fauv.parser.entity.PMPOrFM;
import com.fauv.parser.entity.enums.AxisType;
import com.fauv.parser.entity.enums.TolaranceType;
import com.fauv.parser.pattern.configuration.CarInformationPatternConfiguration;
import com.fauv.parser.pattern.configuration.LinePattern;
import com.fauv.parser.pattern.enums.LinePatternType;
import com.fauv.parser.reader.DMOInfo;
import com.fauv.parser.utils.Utils;

@Service
public class CarInformationService {

	private static final String SEPARATOR_NAME_AND_VALUES = "=";
	private static final String SEPARATOR_VALUES = ",";
	private static CarInformationPatternConfiguration pattern = CarInformationPatternConfiguration.getInstance();
	
	public List<PMPOrFM> buildPmpOrFmList(List<DMOInfo> dmoCarInformationList) throws Exception {
		List<PMPOrFM> list = new ArrayList<>();
		
		for (DMOInfo dmoCarInformation : dmoCarInformationList) {
			PMPOrFM pmpOrFm = new PMPOrFM();
			
			for (String item : dmoCarInformation.getDefaultItems()) {
				LinePattern linePattern = pattern.whichLinePatternIs(item);
				
				if (linePattern == null) { continue; }
				
				if (linePattern.getType().equals(LinePatternType.COORDINATE_NOMINAL)) {
					Coordinate coordinate = buildCoordinate(item, linePattern);
					
					pmpOrFm.getNominalCoordinates().add(coordinate);
				}
				
				if (linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL)) {
					NominalAxisCoordinate axisCoordinate = buildNominalAxisCoordinate(item);
					
					pmpOrFm.getNominalAxisCoodinates().add(axisCoordinate);
				}
			}
			
			for (String item : dmoCarInformation.getMeasurementItems()) {
				LinePattern linePattern = pattern.whichLinePatternIs(item);
				
				if (linePattern == null) { continue; }
				
				if (linePattern.getType().equals(LinePatternType.COORDINATE_MEASUREMENT)) {
					Coordinate coordinate = buildCoordinate(item, linePattern);
					
					pmpOrFm.getMeasurementCoordinates().add(coordinate);
				}
				
				if (linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_MEASUREMENT)) {
					MeasurementAxisCoordinate axisCoordinate = buildMeasurementAxisCoordinate(item);
					
					pmpOrFm.getMeasurementAxisCoordinates().add(axisCoordinate);
				}
			}
			
			
			list.add(pmpOrFm);
		}
		
		return list;
	}
	 
	private Coordinate buildCoordinate(String line, LinePattern linePattern) {
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
		NominalAxisCoordinate axisCoordinate = new NominalAxisCoordinate();
		
		String[] keyAndValues = line.split(SEPARATOR_NAME_AND_VALUES);
		String[] values = keyAndValues[1].split(SEPARATOR_VALUES);
		
		axisCoordinate.setName(Utils.extractName(keyAndValues[0]));
		
		for (String value : values) {
			LinePattern linePattern = pattern.whitchLinePatternIsUsingNominalAxisCoordinate(value);
			
			if (linePattern == null) { continue; }
						
			if (linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL_TOLERANCE)) {
				axisCoordinate.setTolerance(Double.parseDouble(value));
			}
			
			if (linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL_AXIS)) {
				axisCoordinate.setAxis(AxisType.getAxisTypeByLinePatternName(linePattern.getName()));
			}
			
		}
		
		return axisCoordinate;
	}
	
	private MeasurementAxisCoordinate buildMeasurementAxisCoordinate(String line) throws Exception {
		if (line == null || line.trim().isEmpty()) { return null; }
		
		MeasurementAxisCoordinate axisCoordinate = new MeasurementAxisCoordinate();
		
		String[] keyAndValues = line.split(SEPARATOR_NAME_AND_VALUES);
		String[] values = keyAndValues[1].split(SEPARATOR_VALUES);
		
		axisCoordinate.setName(Utils.extractName(keyAndValues[0]));
		
		LinePattern linePattern = pattern.whichLinePatternIs(line);
				
		for (String value : values) {
			LinePattern valueLinePattern = pattern.whitchLinePatternIsUsingMeasurementAxisCoordinate(value);
			
			if (valueLinePattern == null) { continue; }
			
			if (valueLinePattern.getType().equals(LinePatternType.AXIS_COORDINATE_MEASUREMENT_AXIS)) {
				axisCoordinate.setAxis(AxisType.getAxisTypeByLinePatternName(linePattern.getName()));
			}
			
			if (valueLinePattern.getType().equals(LinePatternType.AXIS_COORDINATE_MEASUREMENT_TOLERANCE_RESULT)) {
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

}
