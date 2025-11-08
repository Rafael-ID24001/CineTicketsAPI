package org.cineticketapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AsientoDto {
    private Long idAsiento;
    private String nombreSala;
    private String filaAsiento;
    private Integer numeroAsiento;
    private String tipoAsiento;
    private String estadoAsiento;
}
