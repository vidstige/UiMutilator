package se.vidstige.android.uimultimator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AdbDevice {
	public void sendAdbCommand(StreamParser parser, List<String> arguments) throws UiMultimatorException
	{
		if (arguments == null) throw new IllegalArgumentException("arguments cannot be null");
		if (parser == null) throw new IllegalArgumentException("parser cannot be null");
		
		try
		{
			String android_home = System.getenv("ANDROID_HOME");
			List<String> command_and_arguments = new ArrayList<String>(arguments);
			command_and_arguments.add(0, android_home + "/platform-tools/adb");
			ProcessBuilder processBuilder = new ProcessBuilder(command_and_arguments);
			
			Process p = processBuilder.start();
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			parser.parse(input);
			input.close();
		}
		catch (IOException e)
		{
			throw new UiMultimatorException("Could not send adb command", e);
		}
	}
	
	public void sendAdbCommand(StreamParser parser, String ... arguments) throws UiMultimatorException
	{
		sendAdbCommand(parser, Arrays.asList(arguments));
	}
}
