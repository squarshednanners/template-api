package com.ta.config;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        // .apis(RequestHandlerSelectors.any())
        .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
        .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
        .paths(PathSelectors.any()).build();
  }
}
