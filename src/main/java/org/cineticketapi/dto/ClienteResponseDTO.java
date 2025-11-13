package org.cineticketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
    private Long idCliente;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDateTime fechaRegistro;
}
