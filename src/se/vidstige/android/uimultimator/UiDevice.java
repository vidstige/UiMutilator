package se.vidstige.android.uimultimator;

import java.io.IOException;

public class UiDevice {

	private String serial;
	private final UiAutomatorRunner runner = new UiAutomatorRunner();
	
	UiDevice(String serial) throws IOException, InterruptedException, UiMultimatorException {
		this.serial = serial;
		
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

	public void pressHome() throws UiMultimatorException, IOException, InterruptedException {
		runner.run("se.vidstige.android.uimultimator.UiDeviceCommands", "testPressHome");
	}

	public void pressMenu() throws IOException, InterruptedException, UiMultimatorException {
		runner.run("se.vidstige.android.uimultimator.UiDeviceCommands", "testPressMenu");
	}
}
