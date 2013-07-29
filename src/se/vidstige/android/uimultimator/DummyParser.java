package se.vidstige.android.uimultimator;

import java.io.BufferedReader;
import java.io.IOException;

class DummyParser implements StreamParser {

	@Override
	public void parse(BufferedReader input) throws IOException {
//		String line;
//		while ((line = input.readLine()) != null)			
//		{
//			System.out.println("line: " + line);
//		}
	}
	
	@Override
	public void parseErrors(BufferedReader input) throws IOException, AdbException {
		String line;
		while ((line = input.readLine()) != null)			
		{
			//System.out.println("line: " + line);
			if (line.startsWith("error: "))
			{
				throw new AdbException(line.substring("error: ".length()));
			}
		}
	}
}
