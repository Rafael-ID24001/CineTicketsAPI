package org.cineticketapi.model.peliculaCatalogo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "PELICULA_CATALOGO")
@IdClass(PeliculaCatalogoId.class)
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaCatalogo {
    @Id
    @Column(name = "id_pelicula")
    private Long idPelicula;

    @Id
    @Column(name = "id_catalogo")
    private Long idCatalogo;

    @Column(name = "fecha_agregado", nullable = false)
    private LocalDateTime fechaAgregado = LocalDateTime.now();
}
