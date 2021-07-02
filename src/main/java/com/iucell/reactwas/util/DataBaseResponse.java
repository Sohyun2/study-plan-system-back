package com.iucell.reactwas.util;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * author: �̼���
 * date: 2021.06.07
 * ����: ��� response 
 * */
public class DataBaseResponse {
	private boolean status;
	private String errMsg;
	private HttpStatus httpStatus;

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
		convertResultToHttpStatus();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	private void convertResultToHttpStatus() {
		if(this.status) {
			this.httpStatus = HttpStatus.OK;
		} else {
			if(this.errMsg.equals("401")) {
				this.httpStatus = HttpStatus.UNAUTHORIZED; // ��������
			} else if (this.errMsg.equals("403")) {
				this.httpStatus = HttpStatus.FORBIDDEN; // �ΰ� ����(����ڰ� ���ҽ��� ���� �ʿ� ������ �������� ����)
			} 
		}
	}
	
	public JSONObject sendDBResult() {
		JSONObject resultObj = new JSONObject();
		
		resultObj.put("status", this.httpStatus);
		
		if(!status) {
			resultObj.put("errMsg", this.errMsg);
		}
		
		return resultObj;
	}

}
