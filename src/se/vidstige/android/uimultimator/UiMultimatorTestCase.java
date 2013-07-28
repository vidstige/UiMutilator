package se.vidstige.android.uimultimator;

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
			if (n < 0) throw new IllegalArgumentException("n must be >= 0");
			DevicesParser parser = new DevicesParser();
			new AdbDevice().sendAdbCommand(parser, "devices");
			if (n >= parser.getDeviceSerials().size())
				throw new UiMultimatorException("Could not connect to device " + n + ", only " + parser.getDeviceSerials().size() + " connected");
			String serial = parser.getDeviceSerials().get(n);
			return new UiDevice(serial);
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
