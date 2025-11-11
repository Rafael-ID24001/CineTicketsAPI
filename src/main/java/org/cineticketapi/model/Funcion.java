package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "FUNCION")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcion")
    @SequenceGenerator(name = "seq_funcion", sequenceName = "seq_funcion", allocationSize = 1)
    @Column(name = "id_funcion")
    private Long idFuncion;

    @Column(name = "id_pelicula", nullable = false)
    private Long idPelicula;

    @Column(name = "id_sala", nullable = false)
    private Long idSala;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "precio_boleto", nullable = false, precision = 6, scale = 2)
    private BigDecimal precioBoleto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ModelEnums.EstadoFuncion estado;

}