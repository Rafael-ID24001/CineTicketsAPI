package org.cineticketapi.repository;

import org.cineticketapi.model.Funcion;
import org.cineticketapi.projections.FuncionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    Optional<Funcion> findByIdPeliculaAndIdSalaEqualsAndFechaHora
            (Long idPelicula, Long idSala, LocalDateTime fechaHora);

    @Query("""
        SELECT F.idFuncion as idFuncion, P.titulo as tituloPelicula, S.nombre as nombreSala,
                S.tipoSala as tipoSala, F.fechaHora as fechaHora, F.precioBoleto as precioBoleto
        FROM Funcion F
        JOIN Pelicula P ON F.idPelicula = P.idPelicula
        JOIN SalaDeCine S ON F.idSala = S.idSala
        """)
    List<FuncionProjection> getFuncionesDetalles();

    @Query("""
        SELECT  F.idFuncion as idFuncion, P.titulo as tituloPelicula, S.nombre as nombreSala,
                S.tipoSala as tipoSala, F.fechaHora as fechaHora, F.precioBoleto as precioBoleto, F.estado as estadoFuncion
        FROM Funcion F
        JOIN Pelicula P ON F.idPelicula = P.idPelicula
        JOIN SalaDeCine S ON F.idSala = S.idSala
        WHERE F.idFuncion = :idFuncion      
        """)
    Optional<FuncionProjection> getFuncionDetallesByIdFuncion(@Param("idFuncion") Long idFuncion);
}
