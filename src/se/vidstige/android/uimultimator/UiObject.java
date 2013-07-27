package se.vidstige.android.uimultimator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UiObject {
	private UiSelector selector;
	private final UiAutomatorRunner runner = new UiAutomatorRunner();
	
	public UiObject(UiSelector selector) {
		this.selector = selector;
	}

	public void clickAndWaitForNewWindow() throws UnsupportedEncodingException, IOException, InterruptedException, UiMultimatorException
	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("text_selector", selector.getText());
		runner.run(
				"se.vidstige.android.uimultimator.UiObjectCommands",
				"testClickAndWaitForNewWindow",
				parameters);				
	}

	public String getText() throws UnsupportedEncodingException, IOException, InterruptedException, UiMultimatorException
	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("text_selector", selector.getText());
		
		String result = runner.run(
				"se.vidstige.android.uimultimator.UiObjectCommands",
				"testGetText",
				parameters);
		return result;
	}
}
