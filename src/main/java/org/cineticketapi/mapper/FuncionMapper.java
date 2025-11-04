package org.cineticketapi.mapper;

import org.cineticketapi.dto.FuncionReqDto;
import org.cineticketapi.dto.FuncionRespDto;
import org.cineticketapi.model.Funcion;
import org.cineticketapi.projections.FuncionProjection;
import org.cineticketapi.util.enums.ModelEnums;
import org.springframework.stereotype.Component;

@Component
public class FuncionMapper {

    public FuncionRespDto toFuncionRespDto(FuncionProjection funcionProj){
        return FuncionRespDto.builder()
                .idFuncion(funcionProj.getIdFuncion())
                .tituloPelicula(funcionProj.getTituloPelicula())
                .nombreSala(funcionProj.getNombreSala())
                .tipoSala(String.valueOf(ModelEnums.TipoSala.valueOf(funcionProj.getTipoSala())))
                .fechaHora(funcionProj.getFechaHora())
                .precioBoleto(funcionProj.getPrecioBoleto())
                .estadoFuncion(String.valueOf(ModelEnums.EstadoFuncion.valueOf(funcionProj.getEstadoFuncion())))
                .build();
    }

    public Funcion forCreation(FuncionReqDto funcionReqDto){
        return Funcion.builder()
                .idPelicula(funcionReqDto.getIdPelicula())
                .idSala(funcionReqDto.getIdSala())
                .fechaHora(funcionReqDto.getFechaHora())
                .precioBoleto(funcionReqDto.getPrecioBoleto())
                .estado(ModelEnums.EstadoFuncion.PROGRAMADA)
                .build();
    }
}
