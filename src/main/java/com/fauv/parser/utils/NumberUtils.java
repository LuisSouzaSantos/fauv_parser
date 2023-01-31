package com.fauv.parser.utils;

public class NumberUtils {
	
	public static Double parseFromString(String value) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return null;
		}
	}

}
