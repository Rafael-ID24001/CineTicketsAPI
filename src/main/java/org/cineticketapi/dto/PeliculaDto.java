package org.cineticketapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaDto {

    private Long idPelicula;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser mayor a 0")
    private Integer duracion; // en minutos

    @NotNull(message = "El id de género es obligatorio")
    private Long idGenero;

    @NotBlank(message = "La clasificación es obligatoria")
    private String clasificacion; // por ejemplo: G, PG, PG-13, R, etc.

    private String sinopsis;

    @NotNull(message = "La fecha de estreno es obligatoria")
    private LocalDate fechaEstreno;
}
