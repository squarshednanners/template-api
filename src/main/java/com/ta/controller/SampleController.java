package com.ta.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.model.SampleModel;
import com.ta.model.api.ApiResponse;
import com.ta.service.ISampleService;

@RestController
@RequestMapping("/api/v1/sample")
public class SampleController extends BaseController {

  @Autowired
  private ISampleService sampleService;

  @GetMapping("/")
  public ApiResponse<SampleModel> getSample(Principal principal) {
    System.out.println(principal.getName());
    return processAction(() -> sampleService.getSampleModel(), null, "sample.retrieval.error");
  }

  @GetMapping("/all")
  public ApiResponse<List<SampleModel>> getSamples() {
    return processListAction(() -> sampleService.getSampleModels(), null, "sample.retrieval.error");
  }
}
