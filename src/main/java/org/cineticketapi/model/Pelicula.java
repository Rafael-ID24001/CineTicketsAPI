package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "PELICULA")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pelicula")
    @SequenceGenerator(name = "seq_pelicula", sequenceName = "seq_pelicula", allocationSize = 1)
    @Column(name = "id_pelicula")
    private Long idPelicula;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "id_genero", nullable = false)
    private Long idGenero;

    @Column(name = "clasificacion", nullable = false)
    private String clasificacion;

    @Column(name = "sinopsis")
    private String sinopsis;

    @Column(name = "fecha_estreno", nullable = false)
    private LocalDate fechaEstreno;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ModelEnums.EstadoPelicula estado = ModelEnums.EstadoPelicula.ACTIVA;

}
