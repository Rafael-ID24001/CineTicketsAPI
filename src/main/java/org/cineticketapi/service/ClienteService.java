package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.ClienteRequestDTO;
import org.cineticketapi.dto.ClienteResponseDTO;
import org.cineticketapi.mapper.ClienteMapper;
import org.cineticketapi.model.Cliente;
import org.cineticketapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    public List<ClienteResponseDTO> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponseDTO create(ClienteRequestDTO request) {
        // Verificar si el email ya existe
        if (clienteRepository.existsClienteByEmail(request.getEmail())) {
            throw new RuntimeException("Ya existe un cliente con el email: " + request.getEmail());
        }

        Cliente cliente = clienteMapper.toEntity(request);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.toResponse(savedCliente);
    }

    public ClienteResponseDTO update(Long id, ClienteRequestDTO request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        // Verificar si el email ya existe en otro cliente
        if (request.getEmail() != null &&
                !request.getEmail().equals(cliente.getEmail()) &&
                clienteRepository.existsClienteByEmail(request.getEmail())) {
            throw new RuntimeException("Ya existe un cliente con el email: " + request.getEmail());
        }

        clienteMapper.updateEntityFromRequest(request, cliente);
        Cliente updatedCliente = clienteRepository.save(cliente);
        return clienteMapper.toResponse(updatedCliente);
    }

    public void delete(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return clienteRepository.existsClienteByEmail(email);
    }

}