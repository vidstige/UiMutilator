package se.vidstige.android.uimultimator;

import java.util.HashMap;
import java.util.Map;

public class UiSelector {

	private Map<String, String> parameters = new HashMap<String, String>();

	public UiSelector checkable(boolean val) {
		parameters.put("checkable", "b;"+val);
		return this;
	}

	public UiSelector checked(boolean val) {
		parameters.put("checked", "b;"+val);
		return this;
	}

//	public UiSelector childSelector(UiSelector selector) {
//		parameters.put("childSelector", "s;"+selector);
//		return this;
//	}

	public UiSelector className(String className) {
		parameters.put("className", "s;"+className);
		return this;
	}

	public <T> UiSelector className(Class<T> type) {
		parameters.put("className", "s;"+type.getName());
		return this;
	}

	public UiSelector classNameMatches(String regex) {
		parameters.put("classNameMatches", "s;"+regex);
		return this;
	}

	public UiSelector clickable(boolean val) {
		parameters.put("clickable", "b;"+val);
		return this;
	}

	public UiSelector description(String desc) {
		parameters.put("description", "s;"+desc);
		return this;
	}

	public UiSelector descriptionContains(String desc) {
		parameters.put("descriptionContains", "s;"+desc);
		return this;
	}

	public UiSelector descriptionMatches(String regex) {
		parameters.put("descriptionMatches", "s;"+regex);
		return this;
	}

	public UiSelector descriptionStartsWith(String desc) {
		parameters.put("descriptionStartsWith", "s;"+desc);
		return this;
	}

	public UiSelector enabled(boolean val) {
		parameters.put("enabled", "b;"+val);
		return this;
	}

	public UiSelector focusable(boolean val) {
		parameters.put("focusable", "b;"+val);
		return this;
	}

	public UiSelector focused(boolean val) {
		parameters.put("focused", "b;"+val);
		return this;
	}

//	public UiSelector fromParent(UiSelector selector) {
//		parameters.put("fromParent", "s;"+selector);
//		return this;
//	}

	public UiSelector index(int index) {
		parameters.put("index", "i;"+index);
		return this;
	}

	public UiSelector instance(int instance) {
		parameters.put("instance", "i;"+instance);
		return this;
	}

	public UiSelector longClickable(boolean val) {
		parameters.put("longClickable", "b;"+val);
		return this;
	}

	public UiSelector packageName(String name) {
		parameters.put("packageName", "s;"+name);
		return this;
	}

	public UiSelector packageNameMatches(String regex) {
		parameters.put("packageNameMatches", "s;"+regex);
		return this;
	}

	public UiSelector resourceId(String id) {
		parameters.put("resourceId", "s;"+id);
		return this;
	}

	public UiSelector resourceIdMatches(String regex) {
		parameters.put("resourceIdMatches", "s;"+regex);
		return this;
	}

	public UiSelector scrollable(boolean val) {
		parameters.put("scrollable", "b;"+val);
		return this;
	}

	public UiSelector selected(boolean val) {
		parameters.put("selected", "b;"+val);
		return this;
	}

	public UiSelector text(String text) {
		parameters.put("text", "s;"+text);
		return this;
	}

	public UiSelector textContains(String text) {
		parameters.put("textContains", "s;"+text);
		return this;
	}

	public UiSelector textMatches(String regex) {
		parameters.put("textMatches", "s;"+regex);
		return this;
	}

	public UiSelector textStartsWith(String text) {
		parameters.put("textStartsWith", "s;"+text);
		return this;
	}










	Map<String, String> getParameters() {
		return parameters;
	}

//	void serializeTo(Map<String, String> parameters) {
//		parameters.put("selector_type", type);
//		parameters.put("selector_parameter", parameter);
//	}
}
