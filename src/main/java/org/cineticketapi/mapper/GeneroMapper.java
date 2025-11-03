package org.cineticketapi.mapper;

import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.model.Genero;

public class GeneroMapper {

    public static GeneroDto toDto(Genero entity) {
        if (entity == null) return null;
        return new GeneroDto(entity.getId(), entity.getNombre(), entity.getDescripcion());
    }

    public static Genero toEntity(GeneroDto dto) {
        if (dto == null) return null;
        Genero g = new Genero();
        g.setId(dto.getId());
        g.setNombre(dto.getNombre());
        g.setDescripcion(dto.getDescripcion());
        return g;
    }

    public static void updateEntity(Genero entity, GeneroDto dto) {
        if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null) entity.setDescripcion(dto.getDescripcion());
    }
}
