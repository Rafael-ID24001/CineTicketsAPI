package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

// --- IMPORTS CRÍTICOS ---
import org.cineticketapi.model.Cliente;
// --- FIN DE LA CORRECCIÓN ---

@Entity
@Table(name = "VENTA")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venta")
    @SequenceGenerator(name = "seq_venta", sequenceName = "seq_venta", allocationSize = 1)
    @Column(name = "id_venta")
    private Long idVenta;

    // --- CAMBIO IMPORTANTE ---
    // En lugar de "private Long idCliente;"
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta = LocalDateTime.now();

    @Column(name = "total", nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private ModelEnums.MetodoPago metodoPago;
}
