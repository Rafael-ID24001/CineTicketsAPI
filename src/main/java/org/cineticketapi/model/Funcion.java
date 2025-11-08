package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

// --- IMPORTS CRÍTICOS ---
import org.cineticketapi.model.Pelicula;
import org.cineticketapi.model.SalaDeCine;
// --- FIN DE LA CORRECCIÓN ---

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

    // --- CAMBIO IMPORTANTE 1 ---
    // Corregir el mapeo de Pelicula
    @ManyToOne
    @JoinColumn(name = "ID_PELICULA", nullable = false)
    private Pelicula pelicula;

    // --- CAMBIO IMPORTANTE 2 ---
    // Corregir el mapeo de SalaDeCine (Este era el error)
    @ManyToOne
    @JoinColumn(name = "ID_SALA", nullable = false)
    private SalaDeCine salaDeCine;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "precio_boleto", nullable = false)
    private Double precioBoleto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ModelEnums.EstadoFuncion estado = ModelEnums.EstadoFuncion.PROGRAMADA;

}