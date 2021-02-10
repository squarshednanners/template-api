package com.ta.service.impl;

import java.util.List;

import com.ta.service.ICrudService;
import com.ta.model.api.InternalActionResponse;
import com.ta.model.message.InternalMessage;
import com.ta.repository.ICrudRepository;

public abstract class AbstractCrudService<Request, Repository extends ICrudRepository<Request>>
    extends AbstractService<Request, Repository> implements ICrudService<Request> {

  @Override
  public Request fetchById(String id) {
    return repository.fetchById(id);
  }

  @Override
  public InternalActionResponse<Request> create(Request request) {
    List<InternalMessage> validationErrors = validateRequest(request);
    if (!isValid(validationErrors)) {
      return new InternalActionResponse<>(validationErrors, false);
    }
    return new InternalActionResponse<>(repository.create(request));
  }

  @Override
  public InternalActionResponse<Request> update(Request request) {
    List<InternalMessage> validationErrors = validateRequest(request);
    if (!isValid(validationErrors)) {
      return new InternalActionResponse<>(validationErrors, false);
    }
    return new InternalActionResponse<>(repository.update(request));
  }

  @Override
  public void delete(String id) {
    repository.deleteById(id);
  }

}
