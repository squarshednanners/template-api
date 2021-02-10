package com.ta.service;

import java.util.List;

import com.ta.model.SampleModel;
import com.ta.model.api.InternalActionResponse;

public interface ISampleService {
  InternalActionResponse<SampleModel> getSampleModel();

  InternalActionResponse<List<SampleModel>> getSampleModels();
}
