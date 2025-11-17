package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PELICULA_CATALOGO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPelicula;

    @Column(nullable = false)
    private Long idCatalogo;

    @Column(nullable = false)
    private LocalDateTime fechaAgregado = LocalDateTime.now();
}
