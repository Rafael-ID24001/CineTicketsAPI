package org.cineticketapi.mapper;

import org.cineticketapi.dto.VentaRequestDTO;
import org.cineticketapi.dto.VentaResponseDTO;
import org.cineticketapi.model.Venta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VentaMapper {
    public Venta toEntity(VentaRequestDTO request) {
        if (request == null) {
            return null;
        }

        return Venta.builder()
                .idCliente(request.getIdCliente())
                .total(request.getTotal())
                .metodoPago(request.getMetodoPago())
                .fechaVenta(LocalDateTime.now())
                .build();
    }

    public VentaResponseDTO toResponse(Venta venta) {
        if (venta == null) {
            return null;
        }

        return VentaResponseDTO.builder()
                .idVenta(venta.getIdVenta())
                .idCliente(venta.getIdCliente())
                .fechaVenta(venta.getFechaVenta())
                .total(venta.getTotal())
                .metodoPago(venta.getMetodoPago())
                .build();
    }

    public void updateEntityFromRequest(VentaRequestDTO request, Venta venta) {
        if (request == null || venta == null) {
            return;
        }

        venta.setIdCliente(request.getIdCliente());
        venta.setTotal(request.getTotal());
        venta.setMetodoPago(request.getMetodoPago());
    }
}
