package com.ta.validation;

import java.util.List;

import com.ta.model.message.InternalMessage;

/**
 * Used to perform request validation
 * 
 * If validation was successful an empty list will be returned. If validation
 * fails one ore more InternalMessages will be present to state what validation
 * errors occurred
 * 
 * @param <ValidationCandidate>
 *          request object to validate
 * 
 */
public interface IValidator<ValidationCandidate> {
  List<InternalMessage> validate(ValidationCandidate request);
}
