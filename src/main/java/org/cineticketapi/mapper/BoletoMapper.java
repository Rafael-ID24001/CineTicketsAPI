package org.cineticketapi.mapper;

import org.cineticketapi.dto.BoletoRequestDTO;
import org.cineticketapi.dto.BoletoResponseDTO;
import org.cineticketapi.model.Boleto;
import org.cineticketapi.model.Funcion;
import org.cineticketapi.model.Cliente;
import org.cineticketapi.model.Asiento;
import org.cineticketapi.util.enums.ModelEnums;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BoletoMapper {

    public Boleto toEntity(BoletoRequestDTO dto) {
        return Boleto.builder()
                .idFuncion(dto.getIdFuncion())
                .idCliente(dto.getIdCliente())
                .idAsiento(dto.getIdAsiento())
                .fechaCompra(dto.getFechaCompra() != null ? dto.getFechaCompra() : LocalDate.now())
                .estado(dto.getEstado() != null ? dto.getEstado() : ModelEnums.EstadoBoleto.VALIDO)
                .build();
    }

    public BoletoResponseDTO toDTO(Boleto entity) {
        return new BoletoResponseDTO(
                entity.getIdBoleto(),
                entity.getIdFuncion(),
                entity.getIdCliente(),
                entity.getIdAsiento(),
                entity.getFechaCompra(),
                entity.getEstado()
        );
    }

    public void updateEntityFromDTO(BoletoRequestDTO dto, Boleto entity) {
        if (dto.getIdFuncion() != null) {
            entity.setIdFuncion(dto.getIdFuncion());
        }
        if (dto.getIdCliente() != null) {
            entity.setIdCliente(dto.getIdCliente());
        }
        if (dto.getIdAsiento() != null) {
            entity.setIdAsiento(dto.getIdAsiento());
        }
        if (dto.getFechaCompra() != null) {
            entity.setFechaCompra(dto.getFechaCompra());
        }
        if (dto.getEstado() != null) {
            entity.setEstado(dto.getEstado());
        }
    }
}
