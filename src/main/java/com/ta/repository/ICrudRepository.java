package com.ta.repository;

public interface ICrudRepository<Request> {
  Request fetchById(String id);

  Request create(Request request);

  Request update(Request request);

  void deleteById(String id);
}
