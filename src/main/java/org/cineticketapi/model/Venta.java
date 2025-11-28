package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta = LocalDateTime.now();

    @Column(name = "total", nullable = false, precision = 8, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private ModelEnums.MetodoPago metodoPago;
}