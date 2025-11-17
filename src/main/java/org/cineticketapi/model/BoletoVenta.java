package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "BOLETO_VENTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoletoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoleto;

    @Column(nullable = false)
    private Long idVenta;

    @Column(nullable = false)
    private BigDecimal precioUnitario;
}
