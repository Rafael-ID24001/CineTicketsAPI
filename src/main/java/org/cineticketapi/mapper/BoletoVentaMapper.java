package org.cineticketapi.mapper;

import org.cineticketapi.dto.BoletoVentaReqDto;
import org.cineticketapi.dto.BoletoVentaRespDto;
import org.cineticketapi.model.BoletoVenta;
import org.springframework.stereotype.Component;

@Component
public class BoletoVentaMapper {

    public BoletoVenta toEntity(BoletoVentaReqDto dto) {
        return BoletoVenta.builder()
                .idBoleto(dto.getIdBoleto())
                .idVenta(dto.getIdVenta())
                .precioUnitario(dto.getPrecioUnitario())
                .build();
    }

    public BoletoVentaRespDto toDto(BoletoVenta entity) {
        return BoletoVentaRespDto.builder()
                .idBoleto(entity.getIdBoleto())
                .idVenta(entity.getIdVenta())
                .precioUnitario(entity.getPrecioUnitario())
                .build();
    }
}
