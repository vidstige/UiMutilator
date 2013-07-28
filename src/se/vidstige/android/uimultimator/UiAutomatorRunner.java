package se.vidstige.android.uimultimator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

class UiAutomatorRunner {
	private final AdbDevice adb;
	private final String jarfile;

	public UiAutomatorRunner(String serial, String jarfile) {
		this.jarfile = jarfile;
		adb = new AdbDevice(serial);
	}

	public void sendRaw(String ... arguments) throws UiMultimatorException {
		try {
			adb.sendAdbCommand(new DummyParser(), arguments);
		} catch (AdbException e) {
			throw new UiMultimatorException("Could not run raw command", e);
		}		
	}
	
	public void run(String classname, String methodname) throws UiMultimatorException
	{
		try {
			adb.sendAdbCommand(new ReadTestParser(classname, methodname), "shell", "uiautomator", "runtest", jarfile,
					"-c", classname + "#" + methodname);
		} catch (AdbException e) {
			throw new UiMultimatorException("Could not run uiautomator test: " + classname + "#" + methodname, e);
		}
	}

	public String run(String classname, String methodname, Map<String, String> parameters) throws UiMultimatorException {
		try
		{
			ArrayList<String> arguments = new ArrayList<String>(Arrays.asList("shell", "uiautomator", "runtest", jarfile,
					"-c", classname + "#" + methodname));
			for (Entry<String, String> entry : parameters.entrySet())
			{
				arguments.add("-e");
				arguments.add(entry.getKey());
				arguments.add(URLEncoder.encode(entry.getValue(), "utf-8"));
			}
			ReadTestParser parser = new ReadTestParser(classname, methodname);
			adb.sendAdbCommand(parser, arguments);
			return parser.getResponse();
		}
		catch (UnsupportedEncodingException  e)
		{
			throw new UiMultimatorException("Could not run test on device", e);
		} catch (AdbException e) {
			throw new UiMultimatorException("Could not run test on device", e);
		}
	}
}
