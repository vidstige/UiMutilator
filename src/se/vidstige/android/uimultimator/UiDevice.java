package se.vidstige.android.uimultimator;

import java.io.IOException;

public class UiDevice {

	private String serial;
	private final UiAutomatorRunner runner = new UiAutomatorRunner();
	
	UiDevice(String serial) throws IOException, InterruptedException, UiMultimatorException {
		this.serial = serial;
		
		String deluxJar = System.getProperty("user.dir") + "/command-tests/bin/command-tests.jar";
		runner.sendRaw("push", deluxJar, "/data/local/tmp/");
	}

	public void pressHome() throws UiMultimatorException, IOException, InterruptedException {
		runner.run("se.vidstige.android.uimultimator.UiDeviceCommands", "testPressHome");
	}

	public void pressMenu() throws IOException, InterruptedException, UiMultimatorException {
		runner.run("se.vidstige.android.uimultimator.UiDeviceCommands", "testPressMenu");
	}
}
