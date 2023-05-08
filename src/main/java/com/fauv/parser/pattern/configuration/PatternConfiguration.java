package com.fauv.parser.pattern.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fauv.parser.pattern.enums.BlockPatternType;
import com.fauv.parser.pattern.enums.LinePatternType;
import com.fauv.parser.utils.Utils;

public class PatternConfiguration {
	
    private final Logger logger = LoggerFactory.getLogger(PatternConfiguration.class);

	private static final String BLOCK_PATTERN_CONFIGURATION_FILE_PATH = "/usr/local/fauv/parserRegex/blockPattern.txt";
	private static final String INVALID_PATTERN_CONFIGURATION_FILE_PATH = "/usr/local/fauv/parserRegex/ignorePattern.txt";
	private static final String LINE_PATTERN_CONFIGURATION_FILE_PATH = "/usr/local/fauv/parserRegex/linePattern.txt"; 
	private static final String	LINE_INFORMATION_PATTERN_CONFIGURATION_FILE_PATH = "/usr/local/fauv/parserRegex/contentPattern.txt"; 
	
	private List<BlockPattern> blockList = new ArrayList<BlockPattern>();
	private List<AdditionalInformation> additionalInformationList = new ArrayList<AdditionalInformation>();
	private List<String> ignoreInformation = new ArrayList<String>();
	private Map<String, LinePattern> linePatternList = new HashMap<String, LinePattern>();
	
	private static PatternConfiguration configuration;
	
	private PatternConfiguration() {
		try { 
			loadPatterns();
		} catch (IOException e) {
			logger.debug("CANNOT_LOAD_PATTERN_CONFIGURATION");
		}
	}
	
	public static PatternConfiguration getInstance() {
		if (configuration == null) { configuration = new PatternConfiguration(); }
		
		return configuration;
	}
	
	private void loadPatterns() throws FileNotFoundException, IOException {
		loadBlockContent();
		lineInformation();
		loadLineContent();
		loadIgnoreContent();
	}
	
	private void loadIgnoreContent() throws FileNotFoundException, IOException {
		String blockContent = Utils.readFile(new File(INVALID_PATTERN_CONFIGURATION_FILE_PATH));

		String[] lines = blockContent.split("\r\n");
		
		for (String line : lines) {
			String[] keyAndValue = line.split(":=");
			
			ignoreInformation.add(keyAndValue[1]);
		}
		
	}
	
	private void loadBlockContent() throws FileNotFoundException, IOException {
		String blockContent = Utils.readFile(new File(BLOCK_PATTERN_CONFIGURATION_FILE_PATH));
		
		String[] lines = blockContent.split("\r\n");
		
		for (int i = 0; i < lines.length; i = i + 3) {
			String[] firstInformation = lines[i].split(":=");
			String[] secondInformation = lines[i+1].split(":=");
			String[] thirdInformation = lines[i+2].split(":=");
		
			BlockPattern blockPattern = new BlockPattern();
			blockPattern.setInit(firstInformation[1]);
			blockPattern.setEnd(secondInformation[1]);
			blockPattern.setType(BlockPatternType.valueOf(thirdInformation[1]));
			
			this.blockList.add(blockPattern);
		}
		
		logger.debug("Number of BLOCK CONTENT loaded is: "+this.blockList.size());
	}
	
	private void loadLineContent() throws FileNotFoundException, IOException {
		String lineContent = Utils.readFile(new File(LINE_PATTERN_CONFIGURATION_FILE_PATH));
		
		String[] lines = lineContent.split("\r\n");
		
		for (String line : lines) {
			String[] information = line.split(":=");
			
			LinePattern linePattern = new LinePattern();
			linePattern.setName(information[0]);
			linePattern.setPattern(information[1]);
			
			LinePatternType type = LinePatternType.whichIs(information[0]);
			
			linePattern.setType(type);
			linePattern.setAdditionalInformation(getByLinePatternName(information[0]));
			
			this.linePatternList.put(linePattern.getName(), linePattern);
		}

		logger.debug("Number of LINE CONTENT loaded is: "+this.linePatternList.size());
	}
	
	private void lineInformation() throws FileNotFoundException, IOException {
		String lineInformation = Utils.readFile(new File(LINE_INFORMATION_PATTERN_CONFIGURATION_FILE_PATH));

		String[] lines = lineInformation.split("\r\n");
		
		for (String line : lines) { 
			String[] information = line.split(":=");
			String[] indexs = information[1].split(",");
			
			AdditionalInformation additionalInformation = new AdditionalInformation();
			additionalInformation.setName(information[0]);
			
			for (int i = 0; i < indexs.length; i++) {
				String value = indexs[i];
				additionalInformation.getValues().add(i, Integer.parseInt(value));
				
			}
			
			this.additionalInformationList.add(additionalInformation);
		}
		
		logger.debug("Number of ADDITIONAL INFORMATION loaded is: "+this.additionalInformationList.size());
	}
	
	private AdditionalInformation getByLinePatternName(String linePatternName) {
		return this.additionalInformationList.stream()
				.filter(lineInformation -> lineInformation.getName().contains(linePatternName)).findFirst()
				.orElse(null);
	}

	public List<BlockPattern> getBlockList() {
		return blockList;
	}

	public List<AdditionalInformation> getAdditionalInformationListList() {
		return additionalInformationList;
	}

	public Map<String, LinePattern> getLinePatternList() {
		return linePatternList;
	}

	public List<String> getIgnoreInformation() {
		return ignoreInformation;
	}
	
}
