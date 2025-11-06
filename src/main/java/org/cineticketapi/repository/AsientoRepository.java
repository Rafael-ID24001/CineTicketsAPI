package org.cineticketapi.repository;

import org.cineticketapi.model.Asiento;
import org.cineticketapi.projections.AsientoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AsientoRepository extends JpaRepository<Asiento, Long> {

    @Query("""
        SELECT A.idAsiento as idAsiento, S.nombre as nombreSala, A.fila as filaAsiento,
        A.numero as numeroAsiento, A.tipoAsiento as tipoAsiento, A.estado as estadoAsiento
        FROM Asiento A
        JOIN SalaDeCine S ON A.idSalaCine = S.idSala
        WHERE S.idSala = :idSalaCine
    """)
    List<AsientoProjection> listAsientosDetails(@Param("idSalaCine") Long idSalaCine);

    @Query("""
        SELECT A.idAsiento as idAsiento, S.nombre as nombreSala, A.fila as filaAsiento,
        A.numero as numeroAsiento, A.tipoAsiento as tipoAsiento, A.estado as estadoAsiento
        FROM Asiento A
        JOIN SalaDeCine S ON A.idSalaCine = S.idSala
        WHERE A.idAsiento = :idAsiento
    """)
    Optional<AsientoProjection> getAsientosDetailsByID(@Param("idAsiento") Long idAsiento);

}
