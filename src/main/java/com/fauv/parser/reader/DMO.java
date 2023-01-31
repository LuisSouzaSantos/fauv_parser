package com.fauv.parser.reader;

import java.util.ArrayList;
import java.util.List;

public class DMO {

	private DMOHeader header = new DMOHeader();
	private List<DMOInfo> carInformationList = new ArrayList<DMOInfo>();
	
	public DMOHeader getHeader() {
		return header;
	}
	
	public void setHeader(DMOHeader header) {
		this.header = header;
	}
	
	public List<DMOInfo> getCarInformationList() {
		return carInformationList;
	}
	
	public void setCarInformationList(List<DMOInfo> carInformationList) {
		this.carInformationList = carInformationList;
	}
	
}
