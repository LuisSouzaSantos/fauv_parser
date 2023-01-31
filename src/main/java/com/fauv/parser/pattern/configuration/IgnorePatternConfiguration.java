package com.fauv.parser.pattern.configuration;

public class IgnorePatternConfiguration {

	private static PatternConfiguration patternConfiguration = PatternConfiguration.getInstance();
	private static IgnorePatternConfiguration configuration;
	
	private IgnorePatternConfiguration() {
		
	}
	
	public static IgnorePatternConfiguration getInstance() {
		if (configuration == null) { configuration = new IgnorePatternConfiguration(); }
		
		return configuration;
	}
	
	public boolean ignoreThisLine(String line) {
		boolean ignore = false;
		
		for (String pattern : patternConfiguration.getIgnoreInformation()) {
			ignore = line.matches(pattern);
			
			if (ignore) { break; }
		}
		
		return ignore;
	}
	
}
