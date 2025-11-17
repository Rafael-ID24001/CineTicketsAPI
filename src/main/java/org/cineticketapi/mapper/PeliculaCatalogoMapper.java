package org.cineticketapi.mapper;

import org.cineticketapi.dto.PeliculaCatalogoReqDto;
import org.cineticketapi.dto.PeliculaCatalogoRespDto;
import org.cineticketapi.model.PeliculaCatalogo;
import org.springframework.stereotype.Component;

@Component
public class PeliculaCatalogoMapper {

    public PeliculaCatalogo toEntity(PeliculaCatalogoReqDto dto) {
        return PeliculaCatalogo.builder()
                .idPelicula(dto.getIdPelicula())
                .idCatalogo(dto.getIdCatalogo())
                .build();
    }

    public PeliculaCatalogoRespDto toDto(PeliculaCatalogo entity) {
        return PeliculaCatalogoRespDto.builder()
                .idPelicula(entity.getIdPelicula())
                .idCatalogo(entity.getIdCatalogo())
                .fechaAgregado(entity.getFechaAgregado())
                .build();
    }
}
