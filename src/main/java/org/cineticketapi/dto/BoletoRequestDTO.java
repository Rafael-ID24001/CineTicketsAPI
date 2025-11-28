package org.cineticketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cineticketapi.util.enums.ModelEnums;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletoRequestDTO {
    private Long idFuncion;
    private Long idCliente;
    private Long idAsiento;
    private LocalDate fechaCompra;
    private ModelEnums.EstadoBoleto estado;
}
