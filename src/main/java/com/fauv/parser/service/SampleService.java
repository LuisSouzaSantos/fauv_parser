package com.fauv.parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fauv.parser.entity.Header;
import com.fauv.parser.entity.Sample;
import com.fauv.parser.reader.DMO;
import com.fauv.parser.reader.DMOInfo;
import com.fauv.parser.utils.DMOUtils;

@Service
public class SampleService {

	@Autowired
	private HeaderService headerService;

	@Autowired
	private CarInformationService carInformationService;
	
	public Sample buildSample(DMO dmo) throws Exception {
		Sample sample = new Sample();
		
		Header header = headerService.buildHeader(dmo.getHeader());
		sample.setHeader(header);
		
		for (DMOInfo dmoCarInformation : dmo.getCarInformationList()) {
			if (!carInformationService.isValidDMOInfo(dmoCarInformation)) { continue; }
			
			if (DMOUtils.isFm(dmoCarInformation)) {
				sample.getFmList().add(carInformationService.buildFM(dmoCarInformation));
			}
			
			if (DMOUtils.isPmp(dmoCarInformation)) {
				sample.getPmpList().add(carInformationService.buildPmp(dmoCarInformation));
			}
			
		}
		
		return sample;		
	}
	
}
