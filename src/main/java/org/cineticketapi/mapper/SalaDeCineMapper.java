package org.cineticketapi.mapper;

import org.cineticketapi.dto.SalaCineDto;
import org.cineticketapi.model.SalaDeCine;
import org.cineticketapi.util.enums.ModelEnums;
import org.springframework.stereotype.Component;

@Component
public class SalaDeCineMapper {

    public SalaDeCine forCreation(SalaCineDto salaDtoReq ){
        return SalaDeCine.builder()
                .nombre(salaDtoReq.getNombre())
                .capacidadTotal(salaDtoReq.getCapacidadTotal())
                .tipoSala(ModelEnums.TipoSala.fromDbValue(salaDtoReq.getTipoSala().toUpperCase()))
                .estado(ModelEnums.EstadoSala.DISPONIBLE)
                .build();
    }

    public SalaCineDto DomainToDto(SalaDeCine salaDeCine){
        return SalaCineDto.builder()
                .idSala(salaDeCine.getIdSala())
                .nombre(salaDeCine.getNombre())
                .capacidadTotal(salaDeCine.getCapacidadTotal())
                .tipoSala(String.valueOf(salaDeCine.getTipoSala()))
                .estado(String.valueOf(salaDeCine.getEstado()))
                .build();
    }

    public SalaDeCine DtoToDomain(SalaCineDto salaDtoReq ){
        return SalaDeCine.builder()
                .idSala(salaDtoReq.getIdSala())
                .nombre(salaDtoReq.getNombre())
                .capacidadTotal(salaDtoReq.getCapacidadTotal())
                .tipoSala(ModelEnums.TipoSala.fromDbValue(salaDtoReq.getTipoSala().toUpperCase()))
                .estado(ModelEnums.EstadoSala.valueOf(salaDtoReq.getEstado().toUpperCase()))
                .build();
    }

    public void forUpdate(SalaCineDto salaDtoReq , SalaDeCine salaDeCine){
        if(salaDtoReq.getNombre() != null) salaDeCine.setNombre(salaDtoReq.getNombre());
        if (salaDtoReq.getCapacidadTotal() != null) salaDeCine.setCapacidadTotal(salaDtoReq.getCapacidadTotal());
        if (salaDtoReq.getTipoSala() != null) salaDeCine.setTipoSala(ModelEnums.TipoSala.fromDbValue(salaDtoReq.getTipoSala()));
        if(salaDtoReq.getEstado() != null)salaDeCine.setEstado(ModelEnums.EstadoSala.valueOf(salaDtoReq.getEstado().toUpperCase()));
    }
}

