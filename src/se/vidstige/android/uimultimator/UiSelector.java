package se.vidstige.android.uimultimator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UiSelector {

	private Map<String, String> methods = new HashMap<String, String>();

	public UiSelector checkable(boolean val) {
		methods.put("checkable", "b;"+val);
		return this;
	}

	public UiSelector checked(boolean val) {
		methods.put("checked", "b;"+val);
		return this;
	}

//	public UiSelector childSelector(UiSelector selector) {
//		parameters.put("childSelector", "s;"+selector);
//		return this;
//	}

	public UiSelector className(String className) {
		methods.put("className", "s;"+className);
		return this;
	}

	public <T> UiSelector className(Class<T> type) {
		methods.put("className", "s;"+type.getName());
		return this;
	}

	public UiSelector classNameMatches(String regex) {
		methods.put("classNameMatches", "s;"+regex);
		return this;
	}

	public UiSelector clickable(boolean val) {
		methods.put("clickable", "b;"+val);
		return this;
	}

	public UiSelector description(String desc) {
		methods.put("description", "s;"+desc);
		return this;
	}

	public UiSelector descriptionContains(String desc) {
		methods.put("descriptionContains", "s;"+desc);
		return this;
	}

	public UiSelector descriptionMatches(String regex) {
		methods.put("descriptionMatches", "s;"+regex);
		return this;
	}

	public UiSelector descriptionStartsWith(String desc) {
		methods.put("descriptionStartsWith", "s;"+desc);
		return this;
	}

	public UiSelector enabled(boolean val) {
		methods.put("enabled", "b;"+val);
		return this;
	}

	public UiSelector focusable(boolean val) {
		methods.put("focusable", "b;"+val);
		return this;
	}

	public UiSelector focused(boolean val) {
		methods.put("focused", "b;"+val);
		return this;
	}

//	public UiSelector fromParent(UiSelector selector) {
//		parameters.put("fromParent", "s;"+selector);
//		return this;
//	}

	public UiSelector index(int index) {
		methods.put("index", "i;"+index);
		return this;
	}

	public UiSelector instance(int instance) {
		methods.put("instance", "i;"+instance);
		return this;
	}

	public UiSelector longClickable(boolean val) {
		methods.put("longClickable", "b;"+val);
		return this;
	}

	public UiSelector packageName(String name) {
		methods.put("packageName", "s;"+name);
		return this;
	}

	public UiSelector packageNameMatches(String regex) {
		methods.put("packageNameMatches", "s;"+regex);
		return this;
	}

	public UiSelector resourceId(String id) {
		methods.put("resourceId", "s;"+id);
		return this;
	}

	public UiSelector resourceIdMatches(String regex) {
		methods.put("resourceIdMatches", "s;"+regex);
		return this;
	}

	public UiSelector scrollable(boolean val) {
		methods.put("scrollable", "b;"+val);
		return this;
	}

	public UiSelector selected(boolean val) {
		methods.put("selected", "b;"+val);
		return this;
	}

	public UiSelector text(String text) {
		methods.put("text", "s;"+text);
		return this;
	}

	public UiSelector textContains(String text) {
		methods.put("textContains", "s;"+text);
		return this;
	}

	public UiSelector textMatches(String regex) {
		methods.put("textMatches", "s;"+regex);
		return this;
	}

	public UiSelector textStartsWith(String text) {
		methods.put("textStartsWith", "s;"+text);
		return this;
	}
	
	void serializeTo(Map<String, String> parameters, String prefix) {
		for(Entry<String, String> entry: methods.entrySet()) {
			parameters.put(prefix + entry.getKey(), entry.getValue());			
		}
	}
}
