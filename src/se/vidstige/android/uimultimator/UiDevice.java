package se.vidstige.android.uimultimator;


public class UiDevice {

	private final UiAutomatorRunner runner;
	
	UiDevice(String serial) throws UiMultimatorException {
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
}
