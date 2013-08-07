package se.vidstige.android.uimultimator;

import java.util.HashMap;
import java.util.Map;

public class UiScrollable {
	private UiAutomatorRunner runner;
	private UiSelector selector;

	UiScrollable(UiAutomatorRunner runner, UiSelector selector) {
		this.runner = runner;
		this.selector = selector;
	}
	
	private String run(String methodname) throws UiMultimatorException {
		Map<String, String> parameters = new HashMap<String, String>(); 
		selector.serializeTo(parameters, "s0_");
		return runner.run("se.vidstige.android.uimultimator.UiScrollableCommands", methodname, parameters);
	}

	public void scrollIntoView(UiSelector selector) {
		//this.selector.getParameters("s0_");
	}

	public UiObject getChild(UiSelector selector) {
		return null;
	}
}
