package org.cineticketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaCatalogoRespDto {
    private Long idPelicula;
    private Long idCatalogo;
    private LocalDateTime fechaAgregado;
}
