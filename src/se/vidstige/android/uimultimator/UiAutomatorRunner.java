package se.vidstige.android.uimultimator;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

class UiAutomatorRunner {
	private AdbDevice adb = new AdbDevice();
	private String jarfile = "command-tests.jar";

	public void sendRaw(String ... arguments) throws IOException, InterruptedException {
		adb.sendAdbCommand(null, arguments);		
	}
	
	public void run(String classname, String methodname) throws IOException, InterruptedException
	{
		adb.sendAdbCommand(null, "shell", "uiautomator", "runtest", jarfile,
				"-c", classname + "#" + methodname);
	}

	public void run(String classname, String methodname, Map<String, String> parameters) throws IOException, InterruptedException {
		ArrayList<String> arguments = new ArrayList<String>(Arrays.asList("shell", "uiautomator", "runtest", jarfile,
				"-c", classname + "#" + methodname));
		for (Entry<String, String> entry : parameters.entrySet())
		{
			arguments.add("-e");
			arguments.add(entry.getKey());
			arguments.add(URLEncoder.encode(entry.getValue(), "utf-8"));
		}
		adb.sendAdbCommand(null, arguments);
	}
}
