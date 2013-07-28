package se.vidstige.android.uimultimator;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class UiSelector {
	
	private String type;
	private String parameter;

	public UiSelector text(String text) {
		type = "text";
		parameter = text;
		return this;
	}

	public UiSelector classNameMatches(String regexp) {
		type = "classNameMatches";
		parameter = regexp;
		return this;
	}

	public UiSelector className(String className) {
		type = "className";
		parameter = className;
		return this;
	}

	void serializeTo(Map<String, String> parameters) throws UnsupportedEncodingException {
		parameters.put("selector_type", type);
		parameters.put("selector_parameter", parameter);
	}

}
