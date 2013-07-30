package se.vidstige.android.uimultimator;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

import android.os.Bundle;

import com.android.uiautomator.core.UiSelector;

/**
 * Created by johannes.elgh on 2013-07-30.
 */
public class UiSelectorFactory {

	//Not used--using reflection to get all possible methods
	private static final String[] UISELECTOR_METHODS = {"checkable", "checked", "childSelector", "className",
			"className", "classNameMatches", "clickable", "description", "descriptionContains",
			"descriptionMatches", "descriptionStartsWith", "enabled", "focusable", "focused",
			"index", "instance", "longClickable", "packageName", "packageNameMatches", "resourceId",
			"resourceIdMatches", "scrollable", "selected", "text", "textContains", "textMatches",
			"textStartsWith" };

	private static final String INTEGER = "i;";
	private static final String BOOLEAN = "b;";
	private static final String STRING = "s;";


	private static Object getParameterAsObject(String value) {
		if(value == null) {
			return -1;
		}

		Object param = null;
		if(value.startsWith(INTEGER)) {
			param = Integer.parseInt(value.substring(2));
		} else if(value.startsWith(BOOLEAN)) {
			param = Boolean.parseBoolean(value.substring(2));
		} else if(value.startsWith(STRING)) {
			param = value.substring(2);
		}

		return param;
	}

	private static Method getUiSelectorMethod(String name) {
		Method[] methods = UiSelector.class.getMethods();
		for(Method method : methods) {
			if(method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}

	public static UiSelector getUiSelector(Bundle bundle) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException, UnsupportedEncodingException {

		UiSelector selector = new UiSelector();
		boolean atLeastOneMethodFound = false;

		for(String key : bundle.keySet()) {

			Method method = getUiSelectorMethod(key);
			if(method != null) {

				String value = URLDecoder.decode(bundle.getString(key), "utf-8");
				Object param = getParameterAsObject(value);
	
//				System.out.println("method = "+method.getName());
//				System.out.println("value = "+value);
//				System.out.println("param = "+param.toString());
				
				//special case
				if("className".equals(method.getName())) {
					selector = selector.className(param.toString());
					atLeastOneMethodFound = true;
				} else {
					selector = (UiSelector)method.invoke(selector, param);
					atLeastOneMethodFound = true;
				}
			}
		}

		if(!atLeastOneMethodFound) {
			throw new IllegalArgumentException("Couldn't find any matching method of the given parameters");
		}

		return selector;
	}
}
