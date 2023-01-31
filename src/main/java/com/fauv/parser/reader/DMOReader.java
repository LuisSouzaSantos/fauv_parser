package com.fauv.parser.reader;

import com.fauv.parser.entity.Sample;
import com.fauv.parser.service.DMOService;
import com.fauv.parser.service.SampleService;

public class DMOReader {

	public static void main(String[] args) throws Exception {
		String fileName = "/Users/luisgustavosouzasantos/Documents/Faculdade/TCC/CONFIDENCIAL/NEW_DMOS/2G1800709A_11112022_1056.dmo";
		
		DMO dmo = DMOService.readDMOFileAndBuildIt(fileName);
		
		Sample sample = SampleService.buildSample(dmo);
		
		System.out.println(sample);
	}
	
}
