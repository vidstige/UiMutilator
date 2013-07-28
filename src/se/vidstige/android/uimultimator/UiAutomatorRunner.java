package se.vidstige.android.uimultimator;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

class UiAutomatorRunner {
	private final AdbDevice adb;
	private final String jarfile = "command-tests.jar";

	public UiAutomatorRunner(String serial) {
		adb = new AdbDevice(serial);
	}

	public void sendRaw(String ... arguments) throws IOException, InterruptedException, UiMultimatorException {
		adb.sendAdbCommand(new DummyParser(), arguments);		
	}
	
	public void run(String classname, String methodname) throws IOException, InterruptedException, UiMultimatorException
	{
		adb.sendAdbCommand(new ReadTestParser(classname, methodname), "shell", "uiautomator", "runtest", jarfile,
				"-c", classname + "#" + methodname);
	}

	public String run(String classname, String methodname, Map<String, String> parameters) throws IOException, InterruptedException, UiMultimatorException {
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
}
