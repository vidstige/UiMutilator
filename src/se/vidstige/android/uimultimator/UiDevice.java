package se.vidstige.android.uimultimator;

import java.io.IOException;

public class UiDevice {

	private final UiAutomatorRunner runner;
	
	UiDevice(String serial) throws UiMultimatorException {
		try
		{
			runner = new UiAutomatorRunner(serial);
			
	//		File tmpFile = File.createTempFile("uimultimator-command-tests", ".jar");
	//		OutputStream out = new FileOutputStream(tmpFile);
	//		InputStream in = getClass().getResourceAsStream("command-tests/bin/command-tests.jar");
	//		
	//		byte[] buf = new byte[1024];		
	//		int len;
	//		while ((len = in.read(buf)) >= 0) {
	//	        out.write(buf, 0, len);
	//		}
	//		in.close();
	//		out.close();
			
			String deluxJar = System.getProperty("user.dir") + "/command-tests/bin/command-tests.jar";
			runner.sendRaw("push", deluxJar, "/data/local/tmp/");
		}
		catch (IOException e)
		{
			throw new UiMultimatorException("Could not create UiDevice", e);
		}
		catch (InterruptedException e)
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
		try
		{
			runner.run("se.vidstige.android.uimultimator.UiDeviceCommands", methodname);
		}
		catch (IOException e)
		{
			throw new UiMultimatorException("Could not create UiDevice", e);
		}
		catch (InterruptedException e)
		{
			throw new UiMultimatorException("Could not create UiDevice", e);
		}	
	}
}
