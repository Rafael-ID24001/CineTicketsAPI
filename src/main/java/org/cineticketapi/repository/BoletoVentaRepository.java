package org.cineticketapi.repository;

import org.cineticketapi.model.BoletoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoVentaRepository extends JpaRepository<BoletoVenta, Long> {
}
