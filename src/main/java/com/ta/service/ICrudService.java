package com.ta.service;

import com.ta.model.api.InternalActionResponse;

public interface ICrudService<Request> {
	Request fetchById(String id);

	InternalActionResponse<Request> create(Request request);

	InternalActionResponse<Request> update(Request request);

	void delete(String id);
}
