package com.msodre.cambioservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info =
        @Info(
            title = "Cambio Service API",
            version = "V1",
            description = "Documentation of Cambio Service API"))
public class OpenAPIConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().components(new Components()).info(createInfo());
  }

  private io.swagger.v3.oas.models.info.Info createInfo() {
    return new io.swagger.v3.oas.models.info.Info()
        .title("Cambio Service API")
        .version("V1")
        .description("Documentation of Cambio Service API")
        .license(createLicense());
  }

  private License createLicense() {
    return new License().name("Apache 2.0").url("http://springdoc.org");
  }
}
