package org.cineticketapi.repository;

import org.cineticketapi.model.Funcion;
import org.cineticketapi.view.FuncionDetalleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    // --- CORRECCIÓN AQUÍ ---
    @Query("SELECT new org.cineticketapi.view.FuncionDetalleView(" +
           "f.idFuncion, f.fechaHora, f.precioBoleto, " +
           "p.titulo, " +
           "s.nombre, " +
           "p.clasificacion" + // <-- 1. AÑADIDO ESTE CAMPO
           ") " +
           "FROM Funcion f, Pelicula p, SalaDeCine s " +
           "WHERE f.idpelicula = p.idPelicula " +
           "AND f.idSala = s.idSala " +
           "AND f.idFuncion = :id")
    Optional<FuncionDetalleView> findFuncionDetalleById(@Param("id") Long id);


    // --- CORRECCIÓN AQUÍ ---
    @Query("SELECT new org.cineticketapi.view.FuncionDetalleView(" +
           "f.idFuncion, f.fechaHora, f.precioBoleto, " +
           "p.titulo, " +
           "s.nombre, " +
           "p.clasificacion" + // <-- 2. AÑADIDO ESTE CAMPO
           ") " +
           "FROM Funcion f, Pelicula p, SalaDeCine s " +
           "WHERE f.idpelicula = p.idPelicula AND f.idSala = s.idSala")
    List<FuncionDetalleView> findAllFuncionDetalle();

}