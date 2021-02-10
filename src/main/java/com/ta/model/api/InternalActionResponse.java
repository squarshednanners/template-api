package com.ta.model.api;

import java.util.ArrayList;
import java.util.List;

import com.ta.model.message.InternalMessage;

public class InternalActionResponse<Results> {
  private Results results;
  private List<InternalMessage> messages;
  private boolean successful = true;

  public InternalActionResponse() {
    this.messages = new ArrayList<>();
  }

  public InternalActionResponse(Results results) {
    this.results = results;
    this.messages = new ArrayList<>();
  }

  public InternalActionResponse(List<InternalMessage> messages, boolean successful) {
    this.messages = messages;
    this.successful = successful;
  }

  public InternalActionResponse(InternalMessage message, boolean successful) {
    this.messages = new ArrayList<>();
    this.messages.add(message);
    this.successful = successful;
  }

  public Results getResults() {
    return results;
  }

  public void setResults(Results results) {
    this.results = results;
  }

  public List<InternalMessage> getMessages() {
    return messages;
  }

  public void setMessages(List<InternalMessage> messages) {
    this.messages = messages;
  }

  public boolean isSuccessful() {
    return successful;
  }

  public void setSuccessful(boolean successful) {
    this.successful = successful;
  }

  public void merge(InternalActionResponse<?> mergeResponse) {
    if (!mergeResponse.isSuccessful()) {
      this.successful = mergeResponse.isSuccessful();
    }
    if (!mergeResponse.getMessages().isEmpty()) {
      this.messages.addAll(mergeResponse.getMessages());
    }
  }
}
