package com.iucell.reactwas.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class Util {
	String stringArray;
	JSONArray arr;

	public JSONArray getArr() {
		return arr;
	}

	public void setArr(JSONArray arr) {
		this.arr = arr;
	}

	public String getStringArray() {
		return stringArray;
	}

	public void setStringArray(String stringArray) {
		this.stringArray = "[" + stringArray + "]";
		this.arr = new JSONArray(this.stringArray);
	}
	
	/**
	 * 
	 * @param index: array index
	 * @param parameter : JSONObject parameter name
	 * @return
	 */
	public String getValue(int index, String parameter) {
		return this.getJSONObject(index).getString(parameter);
	}
	
	public JSONObject getJSONObject(int index) {
		if(this.arr == null) return null;
		return this.arr.getJSONObject(index);
	}

	public int getSize() {
		if(this.arr == null) return 0;
		return this.arr.length();
	}
	
}
