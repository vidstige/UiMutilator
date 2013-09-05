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

	private String run(String methodname) throws UiMutilator {
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters, "s0_");
		return runner.run("se.vidstige.android.uimutilator.commandtests.UiObjectCommands", methodname, parameters);
	}
	
	private boolean runBool(String methodname) throws UiMutilator {
		String result = run(methodname);
		return Boolean.parseBoolean(result);
	}
	
	public void click() throws UiMutilator {
		run("testClick");
	}
	
	public void testLongClick() throws UiMutilator {
		run("testLongClick");
	}
	
	public String getContentDescription() throws UiMutilator {	
		return run("testGetContentDescription");
	}
	
	public boolean isChecked() throws UiMutilator {		
		return runBool("testIsChecked");
	}
	
	public boolean isEnabled() throws UiMutilator {
		return runBool("testIsEnabled");
	}
	
	public void clickAndWaitForNewWindow() throws UiMutilator {
		run("testClickAndWaitForNewWindow");
	}

	public String getText() throws UiMutilator {
		return run("testGetText");
	}

	public void setText(String text) throws UiMutilator {
		Map<String, String> parameters = new HashMap<String, String>();
		selector.serializeTo(parameters, "s0_");
		
		parameters.put("set_text", text);
		
		runner.run(
				"se.vidstige.android.uimutilator.commandtests.UiObjectCommands",
				"testSetText",
				parameters);	
	}

	public void clearTextField() throws UiMutilator {
		run("testClearTextField");
	}
	
	public boolean exists() throws UiMutilator {
		return runBool("testExists");
	}
}
