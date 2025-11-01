package org.cineticketapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cineticketapi.dto.validationTypes.CreateValidation;
import org.cineticketapi.dto.validationTypes.UpdateValidation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaCineDto {

    @Null(message = "El ID debe ser nulo para creación", groups = CreateValidation.class)
    @NotNull(message = "El ID es obligatorio para actualización", groups = UpdateValidation.class)
    public Long idSala;

    @NotBlank(message = "Es obligatorio y no puede estar vacío.",  groups = {CreateValidation.class, UpdateValidation.class})
    public String nombre;

    @NotNull(message = "Es obligatoria.", groups = CreateValidation.class)
    @Positive(message = "Debe ser un valor positivo mayor a 0.", groups = CreateValidation.class)
    public Integer capacidadTotal;

    @NotBlank(message = "Es sala obligatorio y no puede estar vacío. Valores permitidos: REGULAR, VIP, 3D, IMAX, 4DX", groups = CreateValidation.class)
    public String tipoSala;

    @Null(message = "El estado debe ser nulo para creación", groups = CreateValidation.class)
    public String estado;
}
