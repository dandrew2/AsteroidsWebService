package edu.ycp.cs496.asteroids.main;

import org.cloudcoder.daemon.IDaemon;
import org.cloudcoder.daemon.Util;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class AsteroidsDaemon implements IDaemon {
	private Server server;
	
	@Override
	public void start(String instanceName) {
		try {
			server = new Server(8081);
	
			// Create and register a webapp context
			WebAppContext handler = new WebAppContext();
			handler.setContextPath("/"); // no context path

			// Run the webapp out of the /war directory of the jarfile
			String codeBase = Util.findCodeBase(AsteroidsDaemon.class);
			if (!codeBase.endsWith(".jar")) {
				throw new IllegalStateException("Only running from a jar file is supported");
			}
			System.out.println("Running from codebase: " + codeBase);
			String warPath = "jar:file:" + codeBase + "!/war";
			handler.setWar(warPath);
			System.out.println("War path is " + warPath);
			
			server.setHandler(handler);
			
			// Use 20 threads to handle requests
			server.setThreadPool(new QueuedThreadPool(20));
			
			// Start the server
			server.start();
			
			System.out.println("Web server started");
		} catch (Exception e) {
			System.out.println("Error starting server: " + e.getMessage());
		}
	}

	@Override
	public void handleCommand(String command) {
		// No commands are supported
	}

	@Override
	public void shutdown() {
		try {
			System.out.println("Shutting down...");
			server.stop();
			server.join();
			System.out.println("Server has shut down, exiting");
		} catch (Exception e) {
			System.out.println("Error shutting down server: " + e.getMessage());
		}
	}

}
