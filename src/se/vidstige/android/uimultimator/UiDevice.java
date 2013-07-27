package se.vidstige.android.uimultimator;

import java.io.IOException;

public class UiDevice {

	private String serial;
	private final AdbDevice adb = new AdbDevice();
	
	UiDevice(String serial) {
		this.serial = serial;		
	}

	public void pressHome() throws UiMultimatorException, IOException, InterruptedException {
		String deluxJar = System.getProperty("user.dir") + "/command-tests/bin/command-tests.jar";
		adb.sendAdbCommand("push", deluxJar, "/data/local/tmp/");
		
		adb.sendAdbCommand("shell", "uiautomator", "runtest", "command-tests.jar",
				"-c", "se.vidstige.android.uimultimator.UiDeviceCommands#testPressHome");		
	}

	public void pressMenu() throws IOException, InterruptedException {
		adb.sendAdbCommand("shell", "uiautomator", "runtest", "command-tests.jar",
			"-c", "se.vidstige.android.uimultimator.UiDeviceCommands#testPressMenu");
	}
}
