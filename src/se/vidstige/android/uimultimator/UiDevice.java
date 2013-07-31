package se.vidstige.android.uimultimator;

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
	
	UiDevice(AdbDevice device) throws UiMultimatorException {
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
				File tmpFile = File.createTempFile("uimultimator-command-tests", ".jar");
				OutputStream out = new FileOutputStream(tmpFile);
				copy(input, out);
				input.close();
				out.close();
			}		
			
			if (deluxJar == null) throw new IllegalStateException("Could not find command-tests.jar in jar-file");
		
			adb.push(deluxJar, "/data/local/tmp/command-tests.jar");
		}
		catch (IOException e) {
			throw new UiMultimatorException("Could not create UiDevice", e);			
		} catch (AdbException e) {
			throw new UiMultimatorException("Could not create UiDevice", e);
		}
	}
	
	public void takeScreenshot(File destination) throws UiMultimatorException
	{
		try
		{
			String tmpfile = "/data/local/tmp/screen-capture.png";
			adb.sendAdbCommand("shell", "screencap", "-p", tmpfile);
			adb.sendAdbCommand(false, "pull", tmpfile, destination.getPath()); // ignore errors as pull prints transfer rates to stderr
			adb.sendAdbCommand("shell", "rm", tmpfile);
		}
		catch (AdbException e)
		{
			throw new UiMultimatorException("Could save take screenshot to " + destination.getPath(), e);
		}
	}

	public UiObject newUiObject(UiSelector selector) {
		return new UiObject(runner, selector);
	}

	public void pressHome() throws UiMultimatorException {
		runTest("testPressHome");
	}

	public void pressMenu() throws UiMultimatorException {
		runTest("testPressMenu");
	}
	
	public void click(int x, int y) throws UiMultimatorException	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("x", Integer.toString(x));
		parameters.put("y", Integer.toString(y));
		runTest("testClick", parameters);				
	}
	
	public void freezeRotation() throws UiMultimatorException {
		runTest("testFreezeRotation");
	}
	
	public void unfreezeRotation() throws UiMultimatorException
	{
		runTest("testUnfreezeRotation");
	}

	public int getDisplayHeight() throws UiMultimatorException {
		String result = runTest("testGetDisplayHeight", new HashMap<String, String>(0));
		return Integer.parseInt(result);
	}
	
	public int getDisplayWidth() throws UiMultimatorException {
		String result = runTest("testGetDisplayWidth", new HashMap<String, String>(0));
		return Integer.parseInt(result);
	}
	
	public int getDisplayRotation() throws UiMultimatorException
	{
		String result = runTest("testGetDisplayRotation", new HashMap<String, String>(0));
		return Integer.parseInt(result);
	}
	
	public String getLastTraversedText() throws UiMultimatorException
	{
		String result = runTest("testGetLastTraversedText", new HashMap<String, String>(0));
		return result;
	}
	
	public boolean isScreenOn() throws UiMultimatorException
	{
		String result = runTest("testIsScreenOn", new HashMap<String, String>(0));
		return Boolean.parseBoolean(result);
	}
	
	public void pressBack() throws UiMultimatorException	{
		runTest("testPressBack");
	}
	
	public void pressSearch() throws UiMultimatorException {
		runTest("testPressSearch");
	}		
		
	public void sleep() throws UiMultimatorException {
		runTest("testSleep");
	}

	public void wakeUp() throws UiMultimatorException {
		runTest("testWakeUp");
	}
	
	public void waitForIdle() throws UiMultimatorException
	{
		runTest("testWaitForIdle");
	}
	
	public void swipe(int startX, int startY, int endX, int endY, int steps) throws UiMultimatorException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("startX", Integer.toString(startX));
		parameters.put("startY", Integer.toString(startY));
		parameters.put("endX", Integer.toString(endX));
		parameters.put("endY", Integer.toString(endY));
		parameters.put("steps", Integer.toString(steps));
		runTest("testSwipe", parameters);		
	}
	
	private String runTest(String methodname, Map<String, String> parameters) throws UiMultimatorException {
		return runner.run("se.vidstige.android.uimultimator.UiDeviceCommands", methodname, parameters);	
	}
	
	private void runTest(String methodname) throws UiMultimatorException {
		runner.run("se.vidstige.android.uimultimator.UiDeviceCommands", methodname, new HashMap<String, String>(0));
	}

	private static void copy(InputStream input, OutputStream out) throws IOException {
		byte[] buf = new byte[1024];		
		int len;
		while ((len = input.read(buf)) >= 0) {
		    out.write(buf, 0, len);
		}
	}
}
