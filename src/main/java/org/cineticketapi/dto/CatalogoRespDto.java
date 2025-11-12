package org.cineticketapi.dto;

import lombok.*;

@Data
@Builder
public class CatalogoRespDto {
    private Long idCatalogo;
    private String tipo;
    private String codigo;
    private String descripcion;
    private Boolean activo;
}
