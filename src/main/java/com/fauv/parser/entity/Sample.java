package com.fauv.parser.entity;

import java.util.ArrayList;
import java.util.List;

public class Sample {

	private Header header;
	private List<PMP> pmpList = new ArrayList<>();
	private List<FM> fmList = new ArrayList<>();
	
	public Header getHeader() {
		return header;
	}
	
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public List<PMP> getPmpList() {
		return pmpList;
	}
	
	public void setPmpList(List<PMP> pmpList) {
		this.pmpList = pmpList;
	}
	
	public List<FM> getFmList() {
		return fmList;
	}
	
	public void setFmList(List<FM> fmList) {
		this.fmList = fmList;
	}
	
}
