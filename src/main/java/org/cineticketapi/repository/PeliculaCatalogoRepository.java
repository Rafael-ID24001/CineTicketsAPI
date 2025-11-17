package org.cineticketapi.repository;

import org.cineticketapi.model.PeliculaCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaCatalogoRepository extends JpaRepository<PeliculaCatalogo, Long> {
}
