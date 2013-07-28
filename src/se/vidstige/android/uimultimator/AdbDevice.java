package se.vidstige.android.uimultimator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AdbDevice {
	
	private String serial;

	public AdbDevice() { this(null); }
	public AdbDevice(String serial)
	{
		this.serial = serial;		
	}
	
	public void sendAdbCommand(StreamParser parser, List<String> arguments) throws AdbException
	{
		if (arguments == null) throw new IllegalArgumentException("arguments cannot be null");
		if (parser == null) throw new IllegalArgumentException("parser cannot be null");
		
		String android_home = System.getenv("ANDROID_HOME");
		if (android_home == null) throw new AdbException("Environment variable ANDROID_HOME not set");
		
		try
		{
			List<String> command_and_arguments = new ArrayList<String>(arguments);
			command_and_arguments.add(0, android_home + "/platform-tools/adb");
			if (serial != null)
			{
				command_and_arguments.add(1, "-s");
				command_and_arguments.add(2, serial);
			}
			ProcessBuilder processBuilder = new ProcessBuilder(command_and_arguments);
						
			Process p = processBuilder.start();
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			parser.parse(input);
			parser.parseErrors(new BufferedReader(new InputStreamReader(p.getErrorStream())));
			input.close();
		}
		catch (Exception e)
		{
			throw new AdbException("Could not send adb command", e);
		}
	}
	
	public void sendAdbCommand(StreamParser parser, String ... arguments) throws AdbException 
	{
		sendAdbCommand(parser, Arrays.asList(arguments));
	}
}
