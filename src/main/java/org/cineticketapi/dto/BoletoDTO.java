package org.cineticketapi.dto;

import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoletoDTO {

    private Long idBoleto;
    private Long idFuncion;
    private Long idCliente;
    private Long idAsiento;
    private LocalDate fechaCompra;
    private ModelEnums.EstadoBoleto estado;
}

