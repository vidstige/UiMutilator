package se.vidstige.android.uimultimator;

import java.io.IOException;
import java.util.List;

import se.vidstige.jadb.AndroidDevice;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbException;

public class UiMultimatorTestCase {

	protected UiDevice getUiDevice(int index) throws UiMultimatorException {
		
		try {
			JadbConnection adb = new JadbConnection();
			List<AndroidDevice> devices = adb.getDevices();
			if (index >= devices.size())
				throw new UiMultimatorException("Could not get device " + index + ". Only " + devices.size() + "connected");
			
			return new UiDevice(devices.get(index));
		} catch (IOException | JadbException e) {
			throw new UiMultimatorException("Could not get UiDevice", e);
		}
	}
}
