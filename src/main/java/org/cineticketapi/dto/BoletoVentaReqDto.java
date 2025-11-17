package org.cineticketapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoletoVentaReqDto {

    @NotNull @Positive
    private Long idBoleto;

    @NotNull @Positive
    private Long idVenta;

    @NotNull @Positive
    private BigDecimal precioUnitario;
}
