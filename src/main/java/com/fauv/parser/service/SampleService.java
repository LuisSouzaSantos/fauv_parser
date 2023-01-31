package com.fauv.parser.service;

import java.util.List;

import com.fauv.parser.entity.Header;
import com.fauv.parser.entity.PMPOrFM;
import com.fauv.parser.entity.Sample;
import com.fauv.parser.reader.DMO;

public class SampleService {

	public static Sample buildSample(DMO dmo) throws Exception {
		Header header = HeaderService.buildHeader(dmo.getHeader());
		List<PMPOrFM> pmpOrFmList = CarInformationService.buildPmpOrFmList(dmo.getCarInformationList());
		
		Sample sample = new Sample();
		sample.setHeader(header);
		sample.setPmpOrFmList(pmpOrFmList);
		
		return sample;		
	}
	
}
