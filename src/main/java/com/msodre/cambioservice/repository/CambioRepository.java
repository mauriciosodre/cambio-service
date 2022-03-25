package com.msodre.cambioservice.repository;

import com.msodre.cambioservice.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

    Optional<Cambio> findByFromAndTo(String from, String to);
}
