package se.vidstige.android.uimultimator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.vidstige.android.adb.AdbException;
import se.vidstige.android.adb.StreamParser;

public class DevicesParser implements StreamParser {
	private List<String> deviceSerials = new ArrayList<String>();
	
	@Override
	public void parse(BufferedReader input) throws IOException {
		String line;
		while ((line = input.readLine()) != null) {
			String[] parts = line.split("\t");
			if (parts.length >= 2)
			{
				deviceSerials.add(parts[0]);
			}
		}
	}

	@Override
	public void parseErrors(BufferedReader input) throws AdbException {		
	}
	
	public List<String> getDeviceSerials()
	{
		return deviceSerials;		
	}
}
