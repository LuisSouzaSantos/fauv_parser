package com.fauv.parser.entity;

import java.util.ArrayList;
import java.util.List;

public class Sample {

	private Header header;
	private List<PMPOrFM> pmpOrFmList = new ArrayList<>();
	
	public Header getHeader() {
		return header;
	}
	
	public void setHeader(Header header) {
		this.header = header;
	}

	public List<PMPOrFM> getPmpOrFmList() {
		return pmpOrFmList;
	}

	public void setPmpOrFmList(List<PMPOrFM> pmpOrFmList) {
		this.pmpOrFmList = pmpOrFmList;
	}

}
