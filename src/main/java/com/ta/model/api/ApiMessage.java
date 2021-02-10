package com.ta.model.api;

import com.ta.model.message.MessageLevel;

public class ApiMessage {

	private String code;
	private String message;
	private MessageLevel level;

	public ApiMessage(String code, String message, MessageLevel level) {
		setCode(code);
		setMessage(message);
		setLevel(level);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageLevel getLevel() {
		return level;
	}

	public void setLevel(MessageLevel level) {
		this.level = level;
	}
}
