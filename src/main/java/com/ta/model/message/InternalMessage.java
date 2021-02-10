package com.ta.model.message;

public class InternalMessage {
	private String code;
	private MessageLevel level;
	private Object[] args;

	public InternalMessage(String code, MessageLevel level, Object... args) {
		setCode(code);
		setLevel(level);
		setArgs(args);
	}

	public String getCode() {
		return code;
	}

	public MessageLevel getLevel() {
		return level;
	}

	public void setLevel(MessageLevel level) {
		this.level = level;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}
