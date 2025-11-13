package org.cineticketapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cineticketapi.util.enums.ModelEnums;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDTO {
    @NotNull(message = "El ID del cliente es obligatorio")
    @Positive(message = "El ID del cliente debe ser un número positivo")
    private Long idCliente;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser un número positivo")
    private BigDecimal total;

    @NotNull(message = "El método de pago es obligatorio")
    private ModelEnums.MetodoPago metodoPago;
}
