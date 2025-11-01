package org.cineticketapi.repository;

import org.cineticketapi.model.boletoVenta.BoletoVentaId;
import org.cineticketapi.model.peliculaCatalogo.PeliculaCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaCatalogoRepository extends JpaRepository<PeliculaCatalogo, BoletoVentaId> {
}
