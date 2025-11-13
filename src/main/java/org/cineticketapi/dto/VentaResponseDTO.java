package org.cineticketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cineticketapi.util.enums.ModelEnums;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponseDTO {
    private Long idVenta;
    private Long idCliente;
    private LocalDateTime fechaVenta;
    private BigDecimal total;
    private ModelEnums.MetodoPago metodoPago;
}

