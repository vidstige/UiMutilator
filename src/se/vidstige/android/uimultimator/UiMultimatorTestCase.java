package se.vidstige.android.uimultimator;

import java.io.IOException;


public class UiMultimatorTestCase {

	protected UiDeviceFluentBuilder getUiDevice() throws UiMultimatorException {
		return new UiDeviceFluentBuilder();
	}
	
	public static class UiDeviceFluentBuilder
	{
		public UiDevice any() throws UiMultimatorException
		{
			return createUiDevice(null);		
		}
		
		public UiDevice number(int n) throws UiMultimatorException
		{			
			try {
				if (n < 0) throw new IllegalArgumentException("n must be >= 0");
				DevicesParser parser = new DevicesParser();
				new AdbDevice().sendAdbCommand(parser, "devices");
				if (n >= parser.getDeviceSerials().size())
					throw new UiMultimatorException("Could not connect to device " + n + ", only " + parser.getDeviceSerials().size() + " connected");
				String serial = parser.getDeviceSerials().get(n);
				return createUiDevice(serial);
			} catch (IOException e) {
				throw new UiMultimatorException("Could not create ui device", e);
			} catch (InterruptedException e) {
				throw new UiMultimatorException("Could not create ui device", e);
			}		
		}
		
		public UiDevice withSerial(String serial) throws UiMultimatorException
		{
			return createUiDevice(serial);		
		}

		private UiDevice createUiDevice(String serial)
				throws UiMultimatorException {
			try {
				return new UiDevice(serial);
			} catch (IOException e) {
				throw new UiMultimatorException("Could not create UiDevice for " + serial);
			} catch (InterruptedException e) {
				throw new UiMultimatorException("Could not create UiDevice for " + serial);
			}
		}
	}
}
