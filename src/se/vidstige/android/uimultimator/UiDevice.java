package se.vidstige.android.uimultimator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class UiDevice {

	private final UiAutomatorRunner runner;
	
	UiDevice(String serial) throws UiMultimatorException {
		try
		{
			runner = new UiAutomatorRunner(serial, "command-tests.jar");
			
			String deluxJar = null;
			InputStream input = getClass().getResourceAsStream("command-tests/bin/command-tests.jar");
			if (input == null)
			{
				deluxJar = System.getProperty("user.dir") + "/command-tests/bin/command-tests.jar";
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
		
			runner.sendRaw("push", deluxJar, "/data/local/tmp/command-tests.jar");
		}
		catch (IOException e)
		{
			throw new UiMultimatorException("Could not create UiDevice", e);			
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
	
	private void runTest(String methodname) throws UiMultimatorException
	{
		runner.run("se.vidstige.android.uimultimator.UiDeviceCommands", methodname);	
	}

	private static void copy(InputStream input, OutputStream out) throws IOException {
		byte[] buf = new byte[1024];		
		int len;
		while ((len = input.read(buf)) >= 0) {
		    out.write(buf, 0, len);
		}
	}
}
