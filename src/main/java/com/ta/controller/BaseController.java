package com.ta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.StringUtils;

import com.ta.exception.ApiException;
import com.ta.model.api.ApiMessage;
import com.ta.model.api.ApiResponse;
import com.ta.model.api.InternalActionResponse;
import com.ta.model.message.InternalMessage;
import com.ta.model.message.MessageLevel;

public class BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

  @Autowired
  private MessageSourceAccessor messageSourceAccessor;

  private ApiMessage getMessage(InternalMessage message) {
    return new ApiMessage(message.getCode(), messageSourceAccessor.getMessage(message.getCode(), message.getArgs()),
        message.getLevel());
  }

  protected <ApiObject> ApiResponse<ApiObject> processAction(ControllerAction<InternalActionResponse<ApiObject>> action,
      String successMessageCode, String errorMessageCode) {
    ApiResponse<ApiObject> response = new ApiResponse<ApiObject>();
    try {
      InternalActionResponse<ApiObject> internalResponse = action.runAction();
      response.setResponse(internalResponse.getResults());
      formatResponse(response, internalResponse, successMessageCode);
    } catch (Exception e) {
      LOGGER.error("An unexpected error occurred", e);
      formatErrorResponse(response, e, errorMessageCode);
    }
    return response;
  }

  protected <ApiObject> ApiResponse<List<ApiObject>> processListAction(
      ControllerAction<InternalActionResponse<List<ApiObject>>> action, String successMessageCode,
      String errorMessageCode) {
    ApiResponse<List<ApiObject>> response = new ApiResponse<List<ApiObject>>();
    try {
      InternalActionResponse<List<ApiObject>> internalResponse = action.runAction();
      response.setResponse(internalResponse.getResults());
      formatResponse(response, internalResponse, successMessageCode);
    } catch (Exception e) {
      LOGGER.error("An unexpected error occurred", e);
      formatErrorResponse(response, e, errorMessageCode);
    }
    return response;
  }

  protected <ApiObject> ApiResponse<ApiObject> processFetchAction(ControllerAction<ApiObject> action,
      String successMessageCode, String errorMessageCode) {
    ApiResponse<ApiObject> response = new ApiResponse<ApiObject>();
    try {
      ApiObject internalResponse = action.runAction();
      response.setResponse(internalResponse);
      formatSimpleResponse(response, successMessageCode);
    } catch (Exception e) {
      LOGGER.error("An unexpected error occurred", e);
      formatErrorResponse(response, e, errorMessageCode);
    }
    return response;
  }

  protected <ApiObject> ApiResponse<List<ApiObject>> processFetchListAction(ControllerAction<List<ApiObject>> action,
      String successMessageCode, String errorMessageCode) {
    ApiResponse<List<ApiObject>> response = new ApiResponse<List<ApiObject>>();
    try {
      List<ApiObject> internalResponse = action.runAction();
      response.setResponse(internalResponse);
      formatSimpleResponse(response, successMessageCode);
    } catch (Exception e) {
      LOGGER.error("An unexpected error occurred", e);
      formatErrorResponse(response, e, errorMessageCode);
    }
    return response;
  }

  protected <ApiObject> ApiResponse<ApiObject> processVoidAction(ControllerVoidAction action, String successMessageCode,
      String errorMessageCode) {
    ApiResponse<ApiObject> response = new ApiResponse<ApiObject>();
    try {
      action.runAction();
      formatSimpleResponse(response, successMessageCode);
    } catch (Exception e) {
      LOGGER.error("An unexpected error occurred", e);
      formatErrorResponse(response, e, errorMessageCode);
    }
    return response;
  }

  protected void formatErrorResponse(ApiResponse<?> response, Exception e, String errorMessageCode) {
    response.setSuccess(false);
    if (errorMessageCode != null) {
      response.addMessage(getMessage(new InternalMessage(errorMessageCode, MessageLevel.ERROR)));
    }
    if (e instanceof ApiException) {
      for (InternalMessage message : ((ApiException) e).getErrorMessages()) {
        response.addMessage(getMessage(message));
      }
    } else {
      response.addMessage(new ApiMessage("controller.unexpected.error", e.getMessage(), MessageLevel.ERROR));
    }
  }

  private void formatResponse(ApiResponse<?> response, InternalActionResponse<?> internalResults,
      String successMessage) {
    response.setSuccess(internalResults.isSuccessful());
    if (internalResults.isSuccessful() && StringUtils.hasText(successMessage)) {
      response.addMessage(getMessage(new InternalMessage(successMessage, MessageLevel.SUCCESS)));
    }
    if (internalResults.getMessages() != null) {
      for (InternalMessage message : (List<InternalMessage>) internalResults.getMessages()) {
        response.addMessage(getMessage(message));
      }
    }
  }

  private void formatSimpleResponse(ApiResponse<?> response, String successMessage) {
    response.setSuccess(true);
    if (StringUtils.hasText(successMessage)) {
      response.addMessage(getMessage(new InternalMessage(successMessage, MessageLevel.SUCCESS)));
    }
  }

  protected interface ControllerAction<ApiObject> {
    ApiObject runAction();
  }

  protected interface ControllerVoidAction {
    void runAction();
  }
}
