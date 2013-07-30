package se.vidstige.android.uimultimator;

import java.util.List;

import se.vidstige.android.adb.Adb;
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
				List<AdbDevice> devices = new Adb().getDevices();
				if (n >= devices.size())
					throw new UiMultimatorException("Could not connect to device " + n + ", only " + devices.size() + " connected");
				return new UiDevice(devices.get(n).getSerial());
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
