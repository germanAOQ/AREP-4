package edu.escuelaing.arep.httpserver.runningserver;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.escuelaing.arep.httpserver.HttpServer;
import edu.escuelaing.arep.nanospring.NanoSpringBoot;

public class RunningServer {

	public static void main(String[] args)
			throws FileNotFoundException, IOException, InterruptedException, ClassNotFoundException {
		// HttpServer httpServerFirstChallenge = new HttpServer();
		// httpServerFirstChallenge.startServer(HttpServer.getEnviorenmentPort());
		try {
			String[] path = { "edu.escuelaing.arep.nanospringdemo.NanoSpringDemo" };
			NanoSpringBoot.getInstance().loadComponents(path);
			NanoSpringBoot.getInstance().startServer();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

}
