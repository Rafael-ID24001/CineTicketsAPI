package org.cineticketapi.mapper;

import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.model.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public GeneroDto toDto(Genero entity) {
        if (entity == null) return null;


        return  GeneroDto.builder()
                .id(entity.getIdGenero())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public Genero toEntity(GeneroDto dto) {
        if (dto == null) return null;
        Genero g = new Genero();
        g.setIdGenero(dto.getId());
        g.setNombre(dto.getNombre());
        g.setDescripcion(dto.getDescripcion());
        return g;
    }

    public Genero updateEntity(Genero entity, GeneroDto dto) {
        if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null) entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
}
