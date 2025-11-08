package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

// --- IMPORTS CRÍTICOS QUE FALTABAN ---
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
// (Estas 3 líneas son la corrección. Java necesita saber dónde encontrar estas clases)
import org.cineticketapi.model.Funcion;
import org.cineticketapi.model.Cliente;
import org.cineticketapi.model.Asiento;
// --- FIN DE LA CORRECCIÓN ---


@Entity
@Table(name = "BOLETO")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_boleto")
    @SequenceGenerator(name = "seq_boleto", sequenceName = "seq_boleto", allocationSize = 1)
    @Column(name = "id_boleto")
    private Long idBoleto;

    // --- CAMBIO IMPORTANTE 1 ---
    @ManyToOne
    @JoinColumn(name = "ID_FUNCION", nullable = false)
    private Funcion funcion;

    // --- CAMBIO IMPORTANTE 2 ---
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente cliente;

    // --- CAMBIO IMPORTANTE 3 ---
    @ManyToOne
    @JoinColumn(name = "ID_ASIENTO", nullable = false)
    private Asiento asiento;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ModelEnums.EstadoBoleto estado = ModelEnums.EstadoBoleto.VALIDO;

}