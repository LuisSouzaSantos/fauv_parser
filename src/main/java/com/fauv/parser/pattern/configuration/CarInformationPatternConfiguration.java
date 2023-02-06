package com.fauv.parser.pattern.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fauv.parser.pattern.enums.BlockPatternType;

public class CarInformationPatternConfiguration {
	
	private static CarInformationPatternConfiguration configuration;	
	private List<BlockPattern> regexBlocks = new ArrayList<BlockPattern>();
	
	private List<LinePattern> regexLines = new ArrayList<LinePattern>();
	private List<LinePattern> nominalCoordinateRegexLines = new ArrayList<LinePattern>();
	private List<LinePattern> measurementCoordinateRegexLines = new ArrayList<LinePattern>();
	private List<LinePattern> axisNominalCoordinateRegexLines = new ArrayList<LinePattern>();
	private List<LinePattern> axisMeasurementCoordinateRegexLines = new ArrayList<LinePattern>();
	private List<LinePattern> differentNominalInfoRegexLines = new ArrayList<LinePattern>();
	private List<LinePattern> differentMeasurementInfoRegexLines = new ArrayList<LinePattern>();

	
	public static CarInformationPatternConfiguration getInstance() {
		if (configuration == null) { configuration = new CarInformationPatternConfiguration(); }
		
		return configuration;
	}
	
	private CarInformationPatternConfiguration() {
		loadLineRegexs();
		loadBlockRegexs();
	}
	
	public LinePattern whichLinePatternIs(String line) throws Exception {
		LinePattern selectedLinePattern = null;
		
		for (LinePattern linePattern : getRegexLines()) {
			if (line.matches(linePattern.getPattern())) {
				selectedLinePattern = linePattern;
				break;
			}
		}
		
		return selectedLinePattern;
	}
	
	public LinePattern whichLinePatternIsUsingNominalAxisCoordinate(String line) throws Exception {		
		List<LinePattern> list = Stream
				.concat(getNominalCoordinateRegexLines().stream(), getAxisNominalCoordinateRegexLines().stream())
				.collect(Collectors.toList());
		
		list = Stream.concat(list.stream(), getDifferentNominalInfoRegexLines().stream())
				.collect(Collectors.toList());
		
		return list.stream().filter(regex -> line.matches(regex.getPattern())).findFirst().orElse(null);
	}
	
	public LinePattern whitchLinePatternIsUsingMeasurementAxisCoordinate(String line) throws Exception {
		List<LinePattern> list = Stream
				.concat(getMeasurementCoordinateRegexLines().stream(), getAxisMeasurementCoordinateRegexLines().stream())
				.collect(Collectors.toList());
		
		list = Stream.concat(list.stream(), getDifferentMeasurementInfoRegexLines().stream())
				.collect(Collectors.toList());
		
		return list.stream().filter(regex -> line.matches(regex.getPattern())).findFirst().orElse(null);
	}
	
	public List<LinePattern> getRegexLines() throws Exception {
		if (regexLines == null || regexLines.isEmpty()) { loadLineRegexs(); }
		
		return regexLines;
	}
	
	public List<LinePattern> getNominalCoordinateRegexLines() throws Exception {
		if (nominalCoordinateRegexLines == null || nominalCoordinateRegexLines.isEmpty()) { loadLineRegexs(); }
		
		return nominalCoordinateRegexLines;
	}
	
	public List<LinePattern> getMeasurementCoordinateRegexLines() throws Exception {
		if (measurementCoordinateRegexLines == null || measurementCoordinateRegexLines.isEmpty()) { loadLineRegexs(); }
		
		return measurementCoordinateRegexLines;
	}
	
	public List<LinePattern> getAxisNominalCoordinateRegexLines() throws Exception {
		if (axisNominalCoordinateRegexLines == null || axisNominalCoordinateRegexLines.isEmpty()) { loadLineRegexs(); }
		
		return axisNominalCoordinateRegexLines;
	}
	
	public List<LinePattern> getAxisMeasurementCoordinateRegexLines() throws Exception {
		if (axisMeasurementCoordinateRegexLines == null || axisMeasurementCoordinateRegexLines.isEmpty()) { loadLineRegexs(); }
		
		return axisMeasurementCoordinateRegexLines;
	}
	
	public List<LinePattern> getDifferentNominalInfoRegexLines() {
		if (differentNominalInfoRegexLines == null || differentNominalInfoRegexLines.isEmpty()) { loadLineRegexs(); }
		
		return differentNominalInfoRegexLines;
	}
	
	public List<LinePattern> getDifferentMeasurementInfoRegexLines() {
		if (differentMeasurementInfoRegexLines == null || differentMeasurementInfoRegexLines.isEmpty()) { loadLineRegexs(); }
		
		return differentMeasurementInfoRegexLines;
	}
	
	public List<BlockPattern> getRegexBlocks() {
		if (regexBlocks == null || regexBlocks.isEmpty()) { loadBlockRegexs(); }
		
		return regexBlocks;
	}
	
	public boolean isBegin(String content) {
		return getRegexBlocks().stream().anyMatch(headerBlock -> content.matches(headerBlock.getInit()));
	}
	
	public boolean isEnd(String content) {
		return getRegexBlocks().stream().anyMatch(headerBlock -> content.matches(headerBlock.getEnd()));
	}
	
	private void loadLineRegexs() {
		PatternConfiguration configuration = PatternConfiguration.getInstance();
		
		Map<String, LinePattern> linePatternList = configuration.getLinePatternList();
				
		regexLines.clear();
		nominalCoordinateRegexLines.clear();
		measurementCoordinateRegexLines.clear();
		axisNominalCoordinateRegexLines.clear();
		axisMeasurementCoordinateRegexLines.clear();
		differentNominalInfoRegexLines.clear();
		differentMeasurementInfoRegexLines.clear();
		
		for (Map.Entry<String, LinePattern> entry : linePatternList.entrySet()) {
		    if (entry.getKey().startsWith(BlockPatternType.HEADER.name())) { continue; }
		   
		    if (entry.getValue().getType().isDifferentNominalInfo()) {
		    	differentNominalInfoRegexLines.add(entry.getValue());
		    }
		    
		    if (entry.getValue().getType().isDifferentMeasurementInfo()) {
		    	differentMeasurementInfoRegexLines.add(entry.getValue());
		    }
		    
		    if (entry.getValue().getType().isAnyCoordinateNominal()) {
		    	nominalCoordinateRegexLines.add(entry.getValue());
		    }
		    
		    if (entry.getValue().getType().isAnyCoordinateMeasurement()) {
		    	measurementCoordinateRegexLines.add(entry.getValue());
		    }
		    
		    if (entry.getValue().getType().isAnyAxisCoordinateNominal()) {
		    	axisNominalCoordinateRegexLines.add(entry.getValue());
		    }
		    
		    if (entry.getValue().getType().isAnyAxisCoordinateMeasurement()) {
		    	axisMeasurementCoordinateRegexLines.add(entry.getValue());
		    }
		    
		    regexLines.add(entry.getValue());
		}
		
	}
	
	private void loadBlockRegexs() {
		PatternConfiguration configuration = PatternConfiguration.getInstance();
		
		List<BlockPattern> blockList = configuration.getBlockList();
		
		List<BlockPattern> blockPatternList = blockList.stream()
				.filter(block -> block.getType().equals(BlockPatternType.CAR_INFORMATION)).collect(Collectors.toList());
		
		regexBlocks.clear();
		regexBlocks.addAll(blockPatternList);
	}

}
