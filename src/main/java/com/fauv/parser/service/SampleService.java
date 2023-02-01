package com.fauv.parser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fauv.parser.entity.Header;
import com.fauv.parser.entity.PMPOrFM;
import com.fauv.parser.entity.Sample;
import com.fauv.parser.reader.DMO;

@Service
public class SampleService {

	@Autowired
	private HeaderService headerService;

	@Autowired
	private CarInformationService carInformationService;
	
	public Sample buildSample(DMO dmo) throws Exception {
		Header header = headerService.buildHeader(dmo.getHeader());
		List<PMPOrFM> pmpOrFmList = carInformationService.buildPmpOrFmList(dmo.getCarInformationList());
		
		Sample sample = new Sample();
		sample.setHeader(header);
		sample.setPmpOrFmList(pmpOrFmList);
		
		return sample;		
	}
	
}
