package com.iucell.reactwas.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;

public class Response {
	private boolean status;
	private String errMsg;
	private HttpStatus httpStatus;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}	
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}	
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	public JSONObject getResponse() {
		JSONObject response = new JSONObject();
		
		response.put("status", this.status);
		response.put("httpStatus", this.httpStatus);
		if(!this.status) {
			response.put("errMsg", this.errMsg);
		}
		if(this.jsonObject != null) {
			response.put("result", this.jsonObject);
		}
		if(this.jsonArray != null) {
			response.put("result", this.jsonArray);
		}
		if(this.jsonObject == null && this.jsonArray == null) {
			response.put("result", "");
		}
		
		return response;
	}
}