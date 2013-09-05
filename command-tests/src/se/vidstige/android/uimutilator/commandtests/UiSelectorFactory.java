package se.vidstige.android.uimutilator.commandtests;

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

	private static final String INTEGER = "i;";
	private static final String BOOLEAN = "b;";
	private static final String STRING = "s;";

	private static Object getParameterAsObject(String value) {
		if(value == null) throw new IllegalArgumentException("value cannot be null");

		if(value.startsWith(INTEGER)) {
			return Integer.parseInt(value.substring(INTEGER.length()));
		} else if(value.startsWith(BOOLEAN)) {
			return Boolean.parseBoolean(value.substring(BOOLEAN.length()));
		} else if(value.startsWith(STRING)) {
			return value.substring(STRING.length());
		}
		throw new IllegalArgumentException("Could not parse value as parameter: " + value); 
	}

	private static Method getUiSelectorMethod(String name) {
		Method[] methods = UiSelector.class.getMethods();
		for(Method method : methods) {
			if(method.getName().equals(name)) {
				return method;
			}
		}
		throw new IllegalArgumentException("No such method");
	}

	public static UiSelector getUiSelector(Bundle bundle, String selectorId) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException, UnsupportedEncodingException {

		UiSelector selector = new UiSelector();
		boolean atLeastOneMethodFound = false;

		for(String key : bundle.keySet()) {
			
			String methodName = null;
			if (key.startsWith("s") && key.contains("_"))
			{
				int idx = key.indexOf('_');
				String id = key.substring("s".length(), idx);
				System.out.println("selector: " + id);
				System.out.println("target: " + selectorId);
				if (id.equals(selectorId))
				{
					methodName = key.substring(idx + 1);
				}
			}
			
			if(methodName != null) {
				Method method = getUiSelectorMethod(methodName);				

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
