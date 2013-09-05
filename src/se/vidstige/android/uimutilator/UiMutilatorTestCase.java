package se.vidstige.android.uimutilator;

import java.util.List;

import se.vidstige.android.adb.Adb;
import se.vidstige.android.adb.AdbDevice;
import se.vidstige.android.adb.AdbException;

public class UiMutilatorTestCase {

	protected UiDeviceFluentBuilder getUiDevice() throws UiMutilator {
		return new UiDeviceFluentBuilder();
	}
	
	public static class UiDeviceFluentBuilder
	{
		public UiDevice any() throws UiMutilator
		{
			return new UiDevice(AdbDevice.any());		
		}
		
		public UiDevice number(int n) throws UiMutilator
		{
			try
			{
				if (n < 0) throw new IllegalArgumentException("n must be >= 0");
				List<AdbDevice> devices = new Adb().getDevices();
				if (n >= devices.size())
					throw new UiMutilator("Could not connect to device " + n + ", only " + devices.size() + " connected");
				return new UiDevice(devices.get(n));
			}
			catch (AdbException e)
			{
				throw new UiMutilator("Could not get devices", e);
			}
		}
		
		public UiDevice withSerial(String serial) throws UiMutilator
		{
			return new UiDevice(new AdbDevice(serial));		
		}

		public UiDevice first() throws UiMutilator {
			return number(0);			
		}
		
		public UiDevice second() throws UiMutilator
		{
			return number(1);
		}
	}
}
