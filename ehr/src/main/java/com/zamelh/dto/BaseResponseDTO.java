package com.zamelh.dto;

public class BaseResponseDTO {

	// Attributes Definition
	private int code;
	private String result;
	private String message;
	
	// Getters & Setters
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
