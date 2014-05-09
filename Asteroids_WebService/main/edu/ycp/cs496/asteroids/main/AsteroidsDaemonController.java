package edu.ycp.cs496.asteroids.main;

import org.cloudcoder.daemon.DaemonController;
import org.cloudcoder.daemon.IDaemon;

public class AsteroidsDaemonController extends DaemonController {

	@Override
	public String getDefaultInstanceName() {
		return "instance";
	}

	@Override
	public Class<? extends IDaemon> getDaemonClass() {
		return AsteroidsDaemon.class;
	}
	
	@Override
	protected Options createOptions() {
		return new Options() {
			@Override
			public String getStdoutLogFileName() {
				String logFile = super.getStdoutLogFileName();
				if (logFile == null) {
					logFile = "logs/stdout.log";
				}
				return logFile;
			}
		};
	}
	
	public static void main(String[] args) {
		new AsteroidsDaemonController().exec(args);
	}

}
