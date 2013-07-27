package se.vidstige.android.uimultimator;

import java.io.IOException;


public class UiMultimatorTestCase {

	protected UiDevice getUiDevice(int index) throws UiMultimatorException {
		String serial = "foobar";
		try {
			return new UiDevice(serial);
		} catch (IOException e) {
			throw new UiMultimatorException("Could not create UiDevice for " + serial);
		} catch (InterruptedException e) {
			throw new UiMultimatorException("Could not create UiDevice for " + serial);
		}
//		try {
//			JadbConnection adb = new JadbConnection();
//			List<AndroidDevice> devices = adb.getDevices();
//			if (index >= devices.size())
//				throw new UiMultimatorException("Could not get device " + index + ". Only " + devices.size() + "connected");
//			
//			return new UiDevice(devices.get(index));
//		} catch (IOException | JadbException e) {
//			throw new UiMultimatorException("Could not get UiDevice", e);
//		}
	}
}
