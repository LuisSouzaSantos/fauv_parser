package com.fauv.parser.utils;

import com.fauv.parser.reader.DMOInfo;

public class DMOUtils {

	private static final String PMP_MATCH = "^OUTPUT/F\\(\\w{1,}\\)\\,T.*$";
	private static final String FM_MATCH = "^OUTPUT/F\\(\\w{1,}\\)\\,F.*$";
	
	public static boolean isPmp(DMOInfo dmoCarInformation) {
		if (dmoCarInformation == null) { return false; }
		
		return dmoCarInformation.getNominalHeaderItem().matches(PMP_MATCH);
	}

	public static boolean isFm(DMOInfo dmoCarInformation) {
		if (dmoCarInformation == null) { return false; }
		
		return dmoCarInformation.getNominalHeaderItem().matches(FM_MATCH); 
	}
	
}
