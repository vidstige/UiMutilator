package se.vidstige.android.uimultimator;

import java.io.IOException;

import se.vidstige.jadb.AndroidDevice;
import se.vidstige.jadb.JadbException;

public class UiDevice {

	private AndroidDevice androidDevice;

	UiDevice(AndroidDevice androidDevice) {
		this.androidDevice = androidDevice;
	}

	public void pressHome() throws UiMultimatorException {
		try {
			androidDevice.push("C:\\abc.jar", "/data/local/tmp/");
			androidDevice.executeShell("uiautomator", "runtest", "LaunchSettings.jar");
		} catch (IOException | JadbException e) {
			throw new UiMultimatorException("Could not press home", e);
		}
	}
}
