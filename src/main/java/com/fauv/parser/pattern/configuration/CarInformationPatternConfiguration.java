package com.fauv.parser.pattern.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fauv.parser.pattern.enums.BlockPatternType;
import com.fauv.parser.pattern.enums.LinePatternType;

public class CarInformationPatternConfiguration {
	
	private List<BlockPattern> regexBlocks = new ArrayList<BlockPattern>();
	private List<LinePattern> regexLines = new ArrayList<LinePattern>();
	private static CarInformationPatternConfiguration configuration;
	
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
	
	public LinePattern whitchLinePatternIsUsingNominalAxisCoordinate(String line) throws Exception {
		LinePattern selectedLinePattern = null;
		
		for (LinePattern linePattern : getRegexLines()) {
			if(!linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL) || 
				!linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL_AXIS) || 
				 !linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL_TOLERANCE)) { continue; }
			
			if (line.matches(linePattern.getPattern())) {
				selectedLinePattern = linePattern;
				break;
			}
		}
		
		return selectedLinePattern;
	}
	
	public LinePattern whitchLinePatternIsUsingMeasurementAxisCoordinate(String line) throws Exception {
		LinePattern selectedLinePattern = null;
				
		for (LinePattern linePattern : getRegexLines()) {
			if(linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL) || 
				linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL_AXIS) || 
				 linePattern.getType().equals(LinePatternType.AXIS_COORDINATE_NOMINAL_TOLERANCE)) { continue; }
			
			
			if (line.matches(linePattern.getPattern())) {
				selectedLinePattern = linePattern;
				break;
			}
		}
		
		return selectedLinePattern;
	}
	
	public List<LinePattern> getRegexLines() throws Exception {
		if (regexLines == null || regexLines.isEmpty()) { loadLineRegexs(); }
		
		return regexLines;
	}
	
	public List<BlockPattern> getRegexBlocks() {
		if (regexBlocks == null || regexBlocks.isEmpty()) { loadBlockRegexs(); }
		
		return regexBlocks;
	}
	
	public boolean isBegin(String content) {
		boolean isBegin = false;
		
		for (BlockPattern headerBlock : regexBlocks) {
			isBegin = content.matches(headerBlock.getInit());
			 
			if (isBegin) { break; }
		}
				
		return isBegin;
	}
	
	public boolean isEnd(String content) {
		boolean isEnd = false;
		
		for (BlockPattern headerBlock : regexBlocks) {
			isEnd = content.matches(headerBlock.getEnd());
			 
			if (isEnd) { break; }
		}
				
		return isEnd;
	}
	
	private void loadLineRegexs() {
		PatternConfiguration configuration = PatternConfiguration.getInstance();
		
		Map<String, LinePattern> linePatternList = configuration.getLinePatternList();
				
		regexLines.clear();
		
		for (Map.Entry<String, LinePattern> entry : linePatternList.entrySet()) {
		    if (entry.getKey().startsWith(BlockPatternType.HEADER.name())) { continue; }
		   
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
