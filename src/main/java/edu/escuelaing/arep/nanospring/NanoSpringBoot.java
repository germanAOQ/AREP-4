package edu.escuelaing.arep.nanospring;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class NanoSpringBoot {
	
	private static NanoSpringBoot _instance = new NanoSpringBoot();
	private Map<String, Method> requestProcessors = new HashMap();
	
	private NanoSpringBoot() {
		
	}
	public static NanoSpringBoot getInstance() {
		return _instance;
	}
	
	public void loadComponents(String[] componentsList) throws ClassNotFoundException {
		for(String componentName: componentsList) {
			loadComponent(componentName);
		}
	}
	private void loadComponent(String componentName) throws ClassNotFoundException {
		Class componentClass = Class.forName(componentName);
		Method[] componentMethods = componentClass.getDeclaredMethods();
		for(Method method: componentMethods) {
			if(method.isAnnotationPresent(RequestMapping.class)) {
				requestProcessors.put(method.getAnnotation(RequestMapping.class).value(), method);
			}
		}
	}
}
