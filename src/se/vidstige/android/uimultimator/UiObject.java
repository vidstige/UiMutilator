package se.vidstige.android.uimultimator;

import java.util.HashMap;
import java.util.Map;

public class UiObject {
	private UiSelector selector;
	private final UiAutomatorRunner runner;
	
	UiObject(UiAutomatorRunner runner, UiSelector selector) {
		this.runner = runner;
		this.selector = selector;
	}

	private String run(String methodname) throws UiMultimatorException {
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters, "s0_");
		return runner.run("se.vidstige.android.uimultimator.UiObjectCommands", methodname, parameters);
	}
	
	private boolean runBool(String methodname) throws UiMultimatorException {
		String result = run(methodname);
		return Boolean.parseBoolean(result);
	}
	
	public void click() throws UiMultimatorException {
		run("testClick");
	}
	
	public void testLongClick() throws UiMultimatorException {
		run("testLongClick");
	}
	
	public String getContentDescription() throws UiMultimatorException {	
		return run("testGetContentDescription");
	}
	
	public boolean isChecked() throws UiMultimatorException {		
		return runBool("testIsChecked");
	}
	
	public boolean isEnabled() throws UiMultimatorException {
		return runBool("testIsEnabled");
	}
	
	public void clickAndWaitForNewWindow() throws UiMultimatorException {
		run("testClickAndWaitForNewWindow");
	}

	public String getText() throws UiMultimatorException {
		return run("testGetText");
	}

	public void setText(String text) throws UiMultimatorException {
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters, "s0_");
		
		parameters.put("set_text", text);
		
		runner.run(
				"se.vidstige.android.uimultimator.UiObjectCommands",
				"testSetText",
				parameters);	
	}

	public void clearTextField() throws UiMultimatorException {
		run("testClearTextField");
	}
	
	public boolean exists() throws UiMultimatorException {
		return runBool("testExists");
	}
}
