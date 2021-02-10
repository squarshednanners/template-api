package com.ta.model.api;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse<Response> {

	private Response response;
	private boolean success = true;
	private List<ApiMessage> messages = new ArrayList<>();

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public List<ApiMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ApiMessage> messages) {
		this.messages = messages;
	}

	public void addMessage(ApiMessage message) {
		this.messages.add(message);
	}
}
