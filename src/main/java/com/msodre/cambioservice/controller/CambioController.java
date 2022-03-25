package com.msodre.cambioservice.controller;

import com.msodre.cambioservice.model.Cambio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cambio-service")
public class CambioController {

  @GetMapping("/{amount}/{from}/{to}")
  public Cambio getCambio(
      @PathVariable BigDecimal amount, @PathVariable String from, @PathVariable String to) {
    return Cambio
            .builder()
            .id(1L)
            .from(from)
            .to(to)
            .conversionFactor(BigDecimal.ONE)
            .convertedValue(BigDecimal.ONE)
            .environment("PORT 8000")
            .build();
  }
}
