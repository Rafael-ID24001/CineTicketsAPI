package org.cineticketapi.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FuncionProjection {
    Long getIdFuncion();
    String getTituloPelicula();
    String getNombreSala();
    String getTipoSala();
    LocalDateTime getFechaHora();
    BigDecimal getPrecioBoleto();
    String getEstadoFuncion();
}
