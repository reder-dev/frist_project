package com.itwill.util;

import java.util.Map;

public class ResponseAPI {
	/*
	 * private String result; private String message;
	 * 
	 * public String getResult() { return result; }
	 * 
	 * public void setResult(String result) { this.result = result; }
	 * 
	 * public String getMessage() { return message; }
	 * 
	 * public void setMessage(String message) { this.message = message; }
	 */
	private Map<String, Object> result;

	public Map<String, Object> getResult() {
		return this.result;
	}

	public void setResult(Map<String, Object> result){
		this.result = result;
	}
}
