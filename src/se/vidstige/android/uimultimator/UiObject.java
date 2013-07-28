package se.vidstige.android.uimultimator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UiObject {
	private UiSelector selector;
	private final UiAutomatorRunner runner;
	
	UiObject(UiAutomatorRunner runner, UiSelector selector) {
		this.runner = runner;
		this.selector = selector;
	}

	public void clickAndWaitForNewWindow() throws UnsupportedEncodingException, IOException, InterruptedException, UiMultimatorException
	{
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters);
		runner.run(
				"se.vidstige.android.uimultimator.UiObjectCommands",
				"testClickAndWaitForNewWindow",
				parameters);				
	}

	public String getText() throws UnsupportedEncodingException, IOException, InterruptedException, UiMultimatorException
	{
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters);		
		
		String result = runner.run(
				"se.vidstige.android.uimultimator.UiObjectCommands",
				"testGetText",
				parameters);
		return result;
	}

	public void setText(String text) throws IOException, InterruptedException, UiMultimatorException {
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters);
		
		parameters.put("set_text", text);
		
		runner.run(
				"se.vidstige.android.uimultimator.UiObjectCommands",
				"testSetText",
				parameters);	
	}

	public void clearTextField() throws IOException, InterruptedException, UiMultimatorException {
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters);
				
		runner.run(
				"se.vidstige.android.uimultimator.UiObjectCommands",
				"testClearTextField",
				parameters);
	}
}
