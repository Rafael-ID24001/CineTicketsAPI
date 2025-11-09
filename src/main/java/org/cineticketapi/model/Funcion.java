package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

// --- NO SE DEBEN IMPORTAR LAS OTRAS ENTIDADES ---
// import org.cineticketapi.model.Pelicula;         <-- BORRA ESTO
// import org.cineticketapi.model.SalaDeCine;       <-- BORRA ESTO
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

    // --- ESTA ES LA VERSIÓN QUE DEBES DEJAR ---
    // Mantiene solo los IDs, sin @ManyToOne
    @Column(name = "id_pelicula", nullable = false)
    private Long idpelicula;

    @Column(name = "id_sala", nullable = false)
    private Long idSala;
    // --- FIN DE LA VERSIÓN CORRECTA ---

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "precio_boleto", nullable = false)
    private Double precioBoleto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ModelEnums.EstadoFuncion estado = ModelEnums.EstadoFuncion.PROGRAMADA;

}