package org.cineticketapi.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class FuncionRespDto {
    private Long idFuncion;
    private String tituloPelicula;
    private String nombreSala;
    private String tipoSala;
    private LocalDateTime fechaHora;
    private BigDecimal precioBoleto;
    private String estadoFuncion;
}
