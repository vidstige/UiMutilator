package se.vidstige.android.uimultimator;

import se.vidstige.android.adb.AdbDevice;
import se.vidstige.android.adb.AdbException;

public class UiMultimatorTestCase {

	protected UiDeviceFluentBuilder getUiDevice() throws UiMultimatorException {
		return new UiDeviceFluentBuilder();
	}
	
	public static class UiDeviceFluentBuilder
	{
		public UiDevice any() throws UiMultimatorException
		{
			return new UiDevice(null);		
		}
		
		public UiDevice number(int n) throws UiMultimatorException
		{
			try
			{
				if (n < 0) throw new IllegalArgumentException("n must be >= 0");
				DevicesParser parser = new DevicesParser();
				new AdbDevice().sendAdbCommand(parser, "devices");
				if (n >= parser.getDeviceSerials().size())
					throw new UiMultimatorException("Could not connect to device " + n + ", only " + parser.getDeviceSerials().size() + " connected");
				String serial = parser.getDeviceSerials().get(n);
				return new UiDevice(serial);
			}
			catch (AdbException e)
			{
				throw new UiMultimatorException("Could not get devices", e);
			}
		}
		
		public UiDevice withSerial(String serial) throws UiMultimatorException
		{
			return new UiDevice(serial);		
		}

		public UiDevice first() throws UiMultimatorException {
			return number(0);			
		}
		
		public UiDevice second() throws UiMultimatorException
		{
			return number(1);
		}
	}
}
