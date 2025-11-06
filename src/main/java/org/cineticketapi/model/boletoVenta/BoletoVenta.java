package org.cineticketapi.model.boletoVenta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "BOLETO_VENTA")
@IdClass(BoletoVentaId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoletoVenta {

    @Id
    @Column(name = "id_boleto")
    private Long idBoleto;

    @Id
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "precio_unitario", nullable = false, precision = 6, scale = 2)
    private BigDecimal precioUnitario;
}
