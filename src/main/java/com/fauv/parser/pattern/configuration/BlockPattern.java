package com.fauv.parser.pattern.configuration;

import com.fauv.parser.pattern.enums.BlockPatternType;

public class BlockPattern {

	private String init;
	private String end;
	private BlockPatternType type;
	
	public String getInit() {
		return init;
	}
	
	public void setInit(String init) {
		this.init = init;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}

	public BlockPatternType getType() {
		return type;
	}

	public void setType(BlockPatternType type) {
		this.type = type;
	}
	
}
