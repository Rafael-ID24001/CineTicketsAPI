package org.cineticketapi.mapper;

import org.cineticketapi.dto.AsientoDto;
import org.cineticketapi.projections.AsientoProjection;
import org.springframework.stereotype.Component;

@Component
public class AsientoMapper {

    public AsientoDto toAsientoDto(AsientoProjection asiento) {
        return AsientoDto.builder()
                .idAsiento(asiento.getIdAsiento())
                .nombreSala(asiento.getNombreSala())
                .filaAsiento(asiento.getFilaAsiento())
                .numeroAsiento(asiento.getNumeroAsiento())
                .tipoAsiento(asiento.getTipoAsiento())
                .estadoAsiento(asiento.getEstadoAsiento())
                .build();
    }
}
