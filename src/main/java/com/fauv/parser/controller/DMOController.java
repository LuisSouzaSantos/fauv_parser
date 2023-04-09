package com.fauv.parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fauv.parser.entity.Sample;
import com.fauv.parser.reader.DMO;
import com.fauv.parser.service.DMOService;
import com.fauv.parser.service.SampleService;

@CrossOrigin
@RestController
@RequestMapping("/dmo")
public class DMOController {

	@Autowired
	private SampleService sampleService;
	
	@Autowired
	private DMOService dmoService;
	
	@PostMapping
	public ResponseEntity<Sample> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		DMO dmo = dmoService.readMultipartFile(file);
		
		Sample sample = sampleService.buildSample(dmo);
		sample.getHeader().setFileName(file.getOriginalFilename());
		
		return ResponseEntity.ok(sample);
	}
	
}
