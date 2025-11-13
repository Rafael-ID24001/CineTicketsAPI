package org.cineticketapi.mapper;

import org.cineticketapi.dto.ClienteRequestDTO;
import org.cineticketapi.dto.ClienteResponseDTO;
import org.cineticketapi.model.Cliente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteRequestDTO request) {
        return Cliente.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .fechaRegistro(LocalDateTime.now())
                .build();
    }

    public ClienteResponseDTO toResponse(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }

    public void updateEntityFromRequest(ClienteRequestDTO request, Cliente cliente) {
        if (request.getNombre() != null) {
            cliente.setNombre(request.getNombre());
        }
        if (request.getEmail() != null) {
            cliente.setEmail(request.getEmail());
        }
        if (request.getTelefono() != null) {
            cliente.setTelefono(request.getTelefono());
        }
    }
}
