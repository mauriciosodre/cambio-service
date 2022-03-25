package com.msodre.cambioservice.controller;

import com.msodre.cambioservice.model.Cambio;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cambio-service")
public class CambioController {

  private final Environment environment;

  @GetMapping("/{amount}/{from}/{to}")
  public Cambio getCambio(
      @PathVariable BigDecimal amount, @PathVariable String from, @PathVariable String to) {
    var port = environment.getProperty("local.server.port");
    return Cambio
            .builder()
            .id(1L)
            .from(from)
            .to(to)
            .conversionFactor(BigDecimal.ONE)
            .convertedValue(BigDecimal.ONE)
            .environment("PORT " + port)
            .build();
  }
}
