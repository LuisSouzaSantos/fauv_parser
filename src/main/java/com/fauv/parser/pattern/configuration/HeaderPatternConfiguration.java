package com.fauv.parser.pattern.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fauv.parser.entity.enums.HeaderType;
import com.fauv.parser.pattern.enums.BlockPatternType;

public class HeaderPatternConfiguration {

	private static String HEADER_PART_TO_BE_REMOVED = BlockPatternType.HEADER.name()+"_";
	
	private List<BlockPattern> regexBlocks = new ArrayList<BlockPattern>();
	private Map<HeaderType, List<LinePattern>> regexLines = new HashMap<HeaderType, List<LinePattern>>();
	private static HeaderPatternConfiguration configuration;
	
	public static HeaderPatternConfiguration getInstance() {
		if (configuration == null) { configuration = new HeaderPatternConfiguration(); }
		
		return configuration;
	}
	
	private HeaderPatternConfiguration() {
		loadLineRegexs();
		loadHeaderBlockRegexs();
	}
	
	public Map<HeaderType, List<LinePattern>> getRegexLines() throws Exception {
		if (regexLines == null || regexLines.isEmpty()) { loadLineRegexs(); }
		
		return regexLines;
	}
	
	public List<BlockPattern> getRegexBlocks() {
		if (regexBlocks == null || regexBlocks.isEmpty()) { loadHeaderBlockRegexs(); }
		
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
		    if (!entry.getKey().startsWith(BlockPatternType.HEADER.name())) { continue; }
			
		    String prefixFileKey = entry.getKey().replace(HEADER_PART_TO_BE_REMOVED, "");
		    
		    HeaderType type = HeaderType.getTypeUsingPrefixFileKey(prefixFileKey);
		    
		    if (type == null) { continue; }
		    
		    List<LinePattern> currentRegexLines = regexLines.get(type);
		    
		    if (currentRegexLines == null) { currentRegexLines = new ArrayList<LinePattern>(); }
		    currentRegexLines.add(entry.getValue());
		   
		    regexLines.put(type, currentRegexLines);
		}
		
	}
	
	private void loadHeaderBlockRegexs() {
		PatternConfiguration configuration = PatternConfiguration.getInstance();
		
		List<BlockPattern> blockList = configuration.getBlockList();
		
		List<BlockPattern> headerBlockPatternList = blockList.stream()
				.filter(block -> block.getType().equals(BlockPatternType.HEADER)).collect(Collectors.toList());
		
		regexBlocks.clear();
		regexBlocks.addAll(headerBlockPatternList);
	}
	
}
