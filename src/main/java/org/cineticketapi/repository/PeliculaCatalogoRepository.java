package org.cineticketapi.repository;

import org.cineticketapi.model.peliculaCatalogo.PeliculaCatalogo;
import org.cineticketapi.model.peliculaCatalogo.PeliculaCatalogoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaCatalogoRepository extends JpaRepository<PeliculaCatalogo, PeliculaCatalogoId> {
}
