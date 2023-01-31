package com.fauv.parser.utils;

public class StringUtils {
	
    public static String getStringBetweenTwoCharacters(String input, String to, String from) {
        return input.substring(input.indexOf(to)+1, input.lastIndexOf(from));
    }
    
}
