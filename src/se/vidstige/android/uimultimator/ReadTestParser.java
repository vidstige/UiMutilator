package se.vidstige.android.uimultimator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.vidstige.android.adb.StreamParser;

class ReadTestParser implements StreamParser{

	private static final String INSTRUMENTATION_STATUS = "INSTRUMENTATION_STATUS: ";
	private String classname;
	private String methodname;
	private String response;
	
	private List<UiMultimatorException> errors = new ArrayList<UiMultimatorException>();
	
	public ReadTestParser(String classname, String methodname) {
		this.classname = classname;
		this.methodname = methodname;
	}
	
	public String getResponse() {
		return response;
	}

	@Override
	public void parse(BufferedReader input) throws IOException {

		String line;
		String lastExceptionMessage = null;
		response = null;
		while ((line = input.readLine()) != null)
		{
			System.out.println("line: " + line);
			
			if (line.startsWith(INSTRUMENTATION_STATUS))
			{
				String rest = line.substring(INSTRUMENTATION_STATUS.length());
				if (rest.startsWith("class="))
				{
					String classname2 = rest.substring("class=".length());
					if (!classname2.equals(classname)) {
						errors.add(new UiMultimatorException("Expected class " + classname + " to be run, but " + classname2 + " was run"));
					}
				}
				int idx = rest.indexOf("UiObjectNotFoundException: ");
				if (idx >= 0)
				{
					lastExceptionMessage = rest.substring(idx + "UiObjectNotFoundException: ".length()); 
				}
			}
			if (line.startsWith("return:"))
			{				
				response = line.substring("return:".length());
				
			}
			if ("FAILURES!!!".equals(line))
			{
				if (lastExceptionMessage == null)
					errors.add(new UiMultimatorException("Could not execute " + classname + "#" + methodname + " on device"));
				else
					errors.add(new UiMultimatorException("UiObject not found: " + lastExceptionMessage));
			}
		}
	}

	@Override
	public void parseErrors(BufferedReader input) throws IOException {
	}

	public void verify() throws UiMultimatorException {
		if (errors.size() > 0)
		{
			throw errors.get(0);
		}		
	}
}
