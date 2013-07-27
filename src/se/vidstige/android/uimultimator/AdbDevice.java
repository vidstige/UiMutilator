package se.vidstige.android.uimultimator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AdbDevice {
	public void sendAdbCommand(StreamParser parser, List<String> arguments) throws IOException, InterruptedException
	{
		String android_home = System.getenv("ANDROID_HOME");
		List<String> command_and_arguments = new ArrayList<String>(arguments);
		command_and_arguments.add(0, android_home + "/platform-tools/adb");
		ProcessBuilder processBuilder = new ProcessBuilder(command_and_arguments);
		
		Process p = processBuilder.start();
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		String line;
		while ((line = input.readLine()) != null)
		{
			System.out.println("line: " + line);
		}
		
		input.close();	
	}
	
	public void sendAdbCommand(StreamParser parser, String ... arguments) throws IOException, InterruptedException
	{
		sendAdbCommand(parser, Arrays.asList(arguments));
	}
}
