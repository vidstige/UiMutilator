package se.vidstige.android.uimultimator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import se.vidstige.android.adb.Adb;
import se.vidstige.android.adb.AdbException;

class UiAutomatorRunner {
	private final Adb adb;
	private final String jarfile;

	public UiAutomatorRunner(Adb adb, String jarfile) {
		this.jarfile = jarfile;
		this.adb = adb;
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
			parser.verify();
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
