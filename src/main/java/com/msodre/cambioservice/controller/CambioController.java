package com.msodre.cambioservice.controller;

import com.msodre.cambioservice.model.Cambio;
import com.msodre.cambioservice.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio Endpoint")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cambio-service")
public class CambioController {

  private final Environment environment;
  private final Logger logger = LoggerFactory.getLogger(CambioController.class);

  private final CambioRepository repository;

  @Operation(summary = "Get Cambio from Currency")
  @GetMapping("/{amount}/{from}/{to}")
  public Cambio getCambio(
      @PathVariable BigDecimal amount, @PathVariable String from, @PathVariable String to) {

    logger.info("getCambio is called with -> {}, {} e {}", amount, from, to);
    var cambio =
        repository
            .findByFromAndTo(from, to)
            .orElseThrow(() -> new RuntimeException("Currency Unsupported"));

    var port = environment.getProperty("local.server.port");
    cambio.setEnvironment(port);
    cambio.setConvertedValue(currencyConversion(amount, cambio.getConversionFactor()));
    return cambio;
  }

  private BigDecimal currencyConversion(BigDecimal amount, BigDecimal factor) {
    return amount.multiply(factor).setScale(2, RoundingMode.CEILING);
  }
}
