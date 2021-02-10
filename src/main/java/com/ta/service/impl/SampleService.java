package com.ta.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ta.model.SampleModel;
import com.ta.model.api.InternalActionResponse;
import com.ta.service.ISampleService;

@Service
public class SampleService implements ISampleService {
  public InternalActionResponse<SampleModel> getSampleModel() {
    return new InternalActionResponse<SampleModel>(new SampleModel("a", "1"));
  }

  public InternalActionResponse<List<SampleModel>> getSampleModels() {
    return new InternalActionResponse<>(Arrays.asList(new SampleModel("a", "1"), new SampleModel("b", "2")));
  }
}
