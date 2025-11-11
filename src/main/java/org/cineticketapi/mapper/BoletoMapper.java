package org.cineticketapi.mapper;

import org.cineticketapi.dto.BoletoDTO;
import org.cineticketapi.model.Boleto;
import org.cineticketapi.model.Funcion;
import org.cineticketapi.model.Cliente;
import org.cineticketapi.model.Asiento;
import org.springframework.stereotype.Component;

@Component
public class BoletoMapper {

    public BoletoDTO toDto(Boleto boleto) {
        if (boleto == null) return null;

        return BoletoDTO.builder()
                .idBoleto(boleto.getIdBoleto())
                .idFuncion(boleto.getIdFuncion())
                .idCliente(boleto.getIdCliente())
                .idAsiento(boleto.getIdAsiento())
                .fechaCompra(boleto.getFechaCompra())
                .estado(boleto.())
                .build();
    }

    public Boleto toEntity(BoletoDTO dto) {
        if (dto == null) return null;

        return Boleto.builder()
                .idBoleto(dto.getIdBoleto())
                .funcion(Funcion.builder().idFuncion(dto.getIdFuncion()).build())
                .cliente(Cliente.builder().idCliente(dto.getIdCliente()).build())
                .asiento(Asiento.builder().idAsiento(dto.getIdAsiento()).build())
                .fechaCompra(dto.getFechaCompra())
                .estado(dto.getEstado())
                .build();
    }
}
