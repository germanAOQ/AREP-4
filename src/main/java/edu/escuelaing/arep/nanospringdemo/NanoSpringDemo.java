package edu.escuelaing.arep.nanospringdemo;

import edu.escuelaing.arep.nanospring.RequestMapping;

public class NanoSpringDemo {
	
	@RequestMapping("/hello")
	public static String index() {
		return "Hi";
	}
	
	@RequestMapping("/hola")
	public static String hola() {
		return "Saludos desde Spring Boot!";
	}
}
