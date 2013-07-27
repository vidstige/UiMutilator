package se.vidstige.android.uimultimator;


public class UiMultimatorTestCase {

	protected UiDevice getUiDevice(int index) throws UiMultimatorException {
		return new UiDevice("<serial>");
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
