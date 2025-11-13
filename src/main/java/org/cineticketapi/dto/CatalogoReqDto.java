package org.cineticketapi.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoReqDto {

    @NotBlank(message = "El tipo es obligatorio.")
    private String tipo;

    @NotBlank(message = "El código es obligatorio.")
    private String codigo;

    private String descripcion;

    /** Si es null, lo dejamos en true en el mapper (alta por defecto) */
    private Boolean activo;
}
