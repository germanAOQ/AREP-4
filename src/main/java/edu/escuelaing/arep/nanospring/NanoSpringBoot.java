package edu.escuelaing.arep.nanospring;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import edu.escuelaing.arep.httpserver.HttpServer;
import edu.escuelaing.arep.httpserver.Processor;

public class NanoSpringBoot implements Processor{
	
	private static NanoSpringBoot _instance = new NanoSpringBoot();
	private Map<String, Method> requestProcessors = new HashMap();
	private HttpServer hserver;
	
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
	@Override
	public String handle(String path, HttpRequest req, HttpResponse res) {
		String resp = "";
		if(requestProcessors.containsKey(path)) {
			try {
				resp = requestProcessors.get(path).invoke(null, null).toString();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return this.validOkHttpHeader(resp);
	}
	
	public String validOkHttpHeader(String resp) {
		return "HTTP/1.1 200 OK\r\n"
		        + "Content-Type: text/html\r\n"
		         + "\r\n" + resp;
	}
	
	public void startServer() throws FileNotFoundException, IOException, InterruptedException {
		hserver = new HttpServer();
		hserver.registerProcessor("/springapp", this);
		hserver.startServer(HttpServer.getEnviorenmentPort());
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ClassNotFoundException {
		NanoSpringBoot.getInstance().loadComponents(args);
		NanoSpringBoot.getInstance().startServer();
	}
	
}
