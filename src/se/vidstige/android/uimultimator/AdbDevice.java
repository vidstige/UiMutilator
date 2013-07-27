package se.vidstige.android.uimultimator;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AdbDevice {
	public void sendAdbCommand(String ... arguments) throws IOException, InterruptedException
	{
		String android_home = System.getenv("ANDROID_HOME");
		List<String> command_and_arguments = new ArrayList<String>(Arrays.asList(arguments));
		command_and_arguments.add(0, android_home + "/platform-tools/adb");
		ProcessBuilder processBuilder = new ProcessBuilder(command_and_arguments);
		
		processBuilder.redirectError(Redirect.INHERIT);
		processBuilder.redirectOutput(Redirect.INHERIT);
		Process p = processBuilder.start();
		p.waitFor();
	}
}
