package se.vidstige.android.adb;

public class AdbDevice {

	private String serial;
	private final static AdbDevice any = new AdbDevice();
	
	public AdbDevice(String serial) {
		if (serial == null) throw new IllegalArgumentException("serial cannot be null. Use AdbDevice.any()");
		this.serial = serial;
	}
	
	private AdbDevice() {
		serial = null;
	}
	
	public String getSerial() { return serial; }

	public static AdbDevice any() {
		return any;
	}
}
