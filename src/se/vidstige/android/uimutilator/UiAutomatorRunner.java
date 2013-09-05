package se.vidstige.android.uimutilator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	private static final String INSTRUMENTATION_STATUS = "INSTRUMENTATION_STATUS: ";
	
	public UiAutomatorRunner(Adb adb, String jarfile) {
		this.jarfile = jarfile;
		this.adb = adb;
	}

	public String run(String classname, String methodname, Map<String, String> parameters) throws UiMutilator {
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
			
			InputStream adbOutput = adb.sendCommand(true, arguments);
			BufferedReader input = new BufferedReader(new InputStreamReader(adbOutput));
			
			String result = parseTests(input, classname, methodname);
			
			input.close();
						
			return result;
		}
		catch (UnsupportedEncodingException  e) {
			throw new UiMutilator("Could not run test on device", e);
		} catch (AdbException e) {
			throw new UiMutilator("Could not run test on device", e);
		} catch (IOException e) {
			throw new UiMutilator("Could not run test on device", e);
		}
	}

	private String parseTests(BufferedReader input, String classname, String methodname) throws IOException, UiMutilator {
		String line;
		String lastExceptionMessage = null;
		String response = null;
		while ((line = input.readLine()) != null)
		{
			System.out.println("line: " + line);
			
			if (line.startsWith(INSTRUMENTATION_STATUS))
			{
				String rest = line.substring(INSTRUMENTATION_STATUS.length());
				if (rest.startsWith("class="))
				{
					String classname2 = rest.substring("class=".length());
					if (!classname2.equals(classname)) {
						throw new UiMutilator("Expected class " + classname + " to be run, but " + classname2 + " was run");
					}
				}
				int idx = rest.indexOf("UiObjectNotFoundException: ");
				if (idx >= 0)
				{
					lastExceptionMessage = rest.substring(idx + "UiObjectNotFoundException: ".length()); 
				}
			}
			if (line.startsWith("return:"))
			{				
				response = line.substring("return:".length());
				
			}
			if ("FAILURES!!!".equals(line))
			{
				if (lastExceptionMessage == null)
					throw new UiMutilator("Could not execute " + classname + "#" + methodname + " on device");
				else
					throw new UiMutilator("UiObject not found: " + lastExceptionMessage);
			}
		}	
		return response;
	}
}
