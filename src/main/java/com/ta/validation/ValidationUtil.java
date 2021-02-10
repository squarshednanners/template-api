package com.ta.validation;

import java.util.List;

import org.springframework.util.StringUtils;

import com.ta.model.message.InternalMessage;
import com.ta.model.message.MessageLevel;

public final class ValidationUtil {
	public static boolean validateStringExists(String requestValue, String errorCode,
			List<InternalMessage> validationErrors) {
		if (isStringEmpty(requestValue)) {
			addValidationError(validationErrors, errorCode);
			return false;
		}
		return true;
	}

	public static boolean validateNotNull(Object request, String errorCode, List<InternalMessage> validationErrors) {
		if (isNull(request)) {
			addValidationError(validationErrors, errorCode);
			return false;
		}
		return true;
	}

	public static boolean isStringEmpty(String string) {
		return StringUtils.hasText(string);
	}

	public static boolean isNull(Object request) {
		return request == null;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isListEmpty(List list) {
		return list == null || list.isEmpty();
	}

	public static void addValidationError(List<InternalMessage> validationErrors, String errorCode, Object... args) {
		validationErrors.add(new InternalMessage(errorCode, MessageLevel.ERROR, args));
	}
}
