package org.cineticketapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaCatalogoReqDto {

    @NotNull @Positive
    private Long idPelicula;

    @NotNull @Positive
    private Long idCatalogo;
}
