package se.vidstige.android.uimutilator;

import java.util.HashMap;
import java.util.Map;

public class UiObject {
	private UiSelector selector;
	private final UiAutomatorRunner runner;
	
	UiObject(UiAutomatorRunner runner, UiSelector selector) {
		this.runner = runner;
		this.selector = selector;
	}

	private String run(String methodname) throws UiMutilatorException {
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters, "s0_");
		return runner.run("se.vidstige.android.uimutilator.commandtests.UiObjectCommands", methodname, parameters);
	}
	
	private boolean runBool(String methodname) throws UiMutilatorException {
		String result = run(methodname);
		return Boolean.parseBoolean(result);
	}
	
	public void click() throws UiMutilatorException {
		run("testClick");
	}
	
	public void testLongClick() throws UiMutilatorException {
		run("testLongClick");
	}
	
	public String getContentDescription() throws UiMutilatorException {	
		return run("testGetContentDescription");
	}
	
	public boolean isChecked() throws UiMutilatorException {		
		return runBool("testIsChecked");
	}
	
	public boolean isEnabled() throws UiMutilatorException {
		return runBool("testIsEnabled");
	}
	
	public void clickAndWaitForNewWindow() throws UiMutilatorException {
		run("testClickAndWaitForNewWindow");
	}

	public String getText() throws UiMutilatorException {
		return run("testGetText");
	}

	public void setText(String text) throws UiMutilatorException {
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters, "s0_");
		
		parameters.put("set_text", text);
		
		runner.run(
				"se.vidstige.android.uimutilator.commandtests.UiObjectCommands",
				"testSetText",
				parameters);	
	}

	public void clearTextField() throws UiMutilatorException {
		run("testClearTextField");
	}
	
	public boolean exists() throws UiMutilatorException {
		return runBool("testExists");
	}
}
