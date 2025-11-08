package org.cineticketapi.repository;

import org.cineticketapi.model.boletoVenta.BoletoVenta;
import org.cineticketapi.model.boletoVenta.BoletoVentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoVentaRepository extends JpaRepository<BoletoVenta, BoletoVentaId> {
}
