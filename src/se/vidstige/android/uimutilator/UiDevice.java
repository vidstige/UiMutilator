package se.vidstige.android.uimutilator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import se.vidstige.android.adb.Adb;
import se.vidstige.android.adb.AdbDevice;
import se.vidstige.android.adb.AdbException;

public class UiDevice {

	private final Adb adb;
	private final UiAutomatorRunner runner;
	
	UiDevice(AdbDevice device) throws UiMutilator {
		try
		{
			adb = new Adb(device);
			runner = new UiAutomatorRunner(adb, "command-tests.jar");
			
			File deluxJar = null;
			InputStream input = getClass().getResourceAsStream("command-tests/bin/command-tests.jar");
			if (input == null)
			{
				deluxJar = new File(System.getProperty("user.dir") + "/command-tests/bin/command-tests.jar");
			}
			else
			{			
				File tmpFile = File.createTempFile("uimutilator-command-tests", ".jar");
				OutputStream out = new FileOutputStream(tmpFile);
				copy(input, out);
				input.close();
				out.close();
			}		
			
			if (deluxJar == null) throw new IllegalStateException("Could not find command-tests.jar in jar-file");
		
			adb.push(deluxJar, "/data/local/tmp/command-tests.jar");
		}
		catch (IOException e) {
			throw new UiMutilator("Could not create UiDevice", e);			
		} catch (AdbException e) {
			throw new UiMutilator("Could not create UiDevice", e);
		}
	}
	
	public void takeScreenshot(File destination) throws UiMutilator
	{
		try
		{
			String tmpfile = "/data/local/tmp/screen-capture.png";
			adb.sendCommand("shell", "screencap", "-p", tmpfile);
			adb.sendCommand(false, "pull", tmpfile, destination.getPath()); // ignore errors as pull prints transfer rates to stderr
			adb.sendCommand("shell", "rm", tmpfile);
		}
		catch (AdbException e)
		{
			throw new UiMutilator("Could save take screenshot to " + destination.getPath(), e);
		}
	}

	public UiObject newUiObject(UiSelector selector) {
		return new UiObject(runner, selector);
	}
	
	public UiScrollable newUiScrollable(UiSelector selector) {
		return new UiScrollable(runner, selector);
	}

	public void pressHome() throws UiMutilator {
		runTest("testPressHome");
	}

	public void pressMenu() throws UiMutilator {
		runTest("testPressMenu");
	}
	
	public void click(int x, int y) throws UiMutilator	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("x", Integer.toString(x));
		parameters.put("y", Integer.toString(y));
		runTest("testClick", parameters);				
	}
	
	public void freezeRotation() throws UiMutilator {
		runTest("testFreezeRotation");
	}
	
	public void unfreezeRotation() throws UiMutilator
	{
		runTest("testUnfreezeRotation");
	}

	public int getDisplayHeight() throws UiMutilator {
		String result = runTest("testGetDisplayHeight", new HashMap<String, String>(0));
		return Integer.parseInt(result);
	}
	
	public int getDisplayWidth() throws UiMutilator {
		String result = runTest("testGetDisplayWidth", new HashMap<String, String>(0));
		return Integer.parseInt(result);
	}
	
	public int getDisplayRotation() throws UiMutilator
	{
		String result = runTest("testGetDisplayRotation", new HashMap<String, String>(0));
		return Integer.parseInt(result);
	}
	
	public String getLastTraversedText() throws UiMutilator
	{
		String result = runTest("testGetLastTraversedText", new HashMap<String, String>(0));
		return result;
	}
	
	public boolean isScreenOn() throws UiMutilator
	{
		String result = runTest("testIsScreenOn", new HashMap<String, String>(0));
		return Boolean.parseBoolean(result);
	}
	
	public void pressBack() throws UiMutilator	{
		runTest("testPressBack");
	}
	
	public void pressSearch() throws UiMutilator {
		runTest("testPressSearch");
	}		
		
	public void sleep() throws UiMutilator {
		runTest("testSleep");
	}

	public void wakeUp() throws UiMutilator {
		runTest("testWakeUp");
	}
	
	public void waitForIdle() throws UiMutilator
	{
		runTest("testWaitForIdle");
	}

	public void waitForIdle(int timeout) throws UiMutilator
	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("timeout", Integer.toString(timeout));
		runTest("testWaitForIdleTimeout", parameters);
	}
	
	public void swipe(int startX, int startY, int endX, int endY, int steps) throws UiMutilator {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startX", Integer.toString(startX));
		parameters.put("startY", Integer.toString(startY));
		parameters.put("endX", Integer.toString(endX));
		parameters.put("endY", Integer.toString(endY));
		parameters.put("steps", Integer.toString(steps));
		runTest("testSwipe", parameters);		
	}
	
	private String runTest(String methodname, Map<String, String> parameters) throws UiMutilator {
		return runner.run("se.vidstige.android.uimutilator.commandtests.UiDeviceCommands", methodname, parameters);	
	}
	
	private void runTest(String methodname) throws UiMutilator {
		runner.run("se.vidstige.android.uimutilator.commandtests.UiDeviceCommands", methodname, new HashMap<String, String>(0));
	}

	private static void copy(InputStream input, OutputStream out) throws IOException {
		byte[] buf = new byte[1024];		
		int len;
		while ((len = input.read(buf)) >= 0) {
		    out.write(buf, 0, len);
		}
	}
}
