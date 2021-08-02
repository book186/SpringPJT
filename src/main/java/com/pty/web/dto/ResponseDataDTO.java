package com.pty.web.dto;

import lombok.ToString;

@ToString
public class ResponseDataDTO {

	public class ResponseDataCode {
		public static final String SUCCESS = "200";
		public static final String ERROR = "999";
	}

	public class ResponseDataStatus {
		public static final String SUCCESS = "200";
		public static final String ERROR = "999";
	}

	private String code;
	private String status;
	private String message;
	private Object item;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		this.item = item;
	}
}