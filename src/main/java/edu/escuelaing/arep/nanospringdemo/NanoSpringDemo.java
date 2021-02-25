package edu.escuelaing.arep.nanospringdemo;

import edu.escuelaing.arep.nanospring.RequestMapping;

public class NanoSpringDemo {
	
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
