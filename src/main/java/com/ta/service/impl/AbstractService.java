package com.ta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ta.model.message.InternalMessage;
import com.ta.repository.ICrudRepository;
import com.ta.validation.IValidator;
import com.ta.validation.ValidationUtil;

public class AbstractService<Request, Repository extends ICrudRepository<Request>> {
  @Autowired(required = false)
  protected IValidator<Request> validator;

  @Autowired
  protected Repository repository;

  protected List<InternalMessage> validateRequest(Request request) {
    if (validator != null) {
      return validator.validate(request);
    }
    return null;
  }

  protected boolean isValid(List<InternalMessage> validationErrors) {
    return ValidationUtil.isListEmpty(validationErrors);
  }
}
