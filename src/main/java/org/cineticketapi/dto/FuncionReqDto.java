package org.cineticketapi.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionReqDto {
    @NotNull(message = "Es obligatorio.")
    @Positive(message = "Debe ser un valor positivo mayor a 0.")
    private Long idPelicula;

    @NotNull(message = "Es obligatorio.")
    @Positive(message = "Debe ser un valor positivo mayor a 0.")
    private Long idSala;

    @NotNull(message = "Es obligatorio.")
    @Future(message = "La fecha debe ser futura")
    private LocalDateTime fechaHora;

    @NotNull(message = "Es obligatorio.")
    @Positive(message = "Debe ser un valor positivo mayor a 0.00")
    private BigDecimal precioBoleto;
}
