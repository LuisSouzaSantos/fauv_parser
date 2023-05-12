package com.fauv.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fauv.parser.pattern.configuration.PatternConfiguration;

@SpringBootApplication
public class ParserApplication {

	public static void main(String[] args) {
		//First Arg is the path of Regex files;
		PatternConfiguration.PATH = args[0];
		
		SpringApplication.run(ParserApplication.class, args);
	}

}
