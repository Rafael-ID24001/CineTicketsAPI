package org.cineticketapi.model.peliculaCatalogo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaCatalogoId implements Serializable {
    private Long idPelicula;
    private Long idCatalogo;
}
