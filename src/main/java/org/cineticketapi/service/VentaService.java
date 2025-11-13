package org.cineticketapi.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.cineticketapi.dto.VentaRequestDTO;
import org.cineticketapi.dto.VentaResponseDTO;
import org.cineticketapi.mapper.VentaMapper;
import org.cineticketapi.model.Venta;
import org.cineticketapi.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private VentaMapper ventaMapper;


    public List<VentaResponseDTO> findAll() {
        return ventaRepository.findAll()
                .stream()
                .map(ventaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public VentaResponseDTO findById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con ID: " + id));
        return ventaMapper.toResponse(venta);
    }

    public VentaResponseDTO create(VentaRequestDTO request) {
        Venta venta = ventaMapper.toEntity(request);
        Venta savedVenta = ventaRepository.save(venta);
        return ventaMapper.toResponse(savedVenta);
    }

    public VentaResponseDTO update(Long id, VentaRequestDTO request) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con ID: " + id));

        ventaMapper.updateEntityFromRequest(request, venta);
        Venta updatedVenta = ventaRepository.save(venta);
        return ventaMapper.toResponse(updatedVenta);
    }

    public void delete(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new EntityNotFoundException("Venta no encontrada con ID: " + id);
        }
        ventaRepository.deleteById(id);
    }

    public List<VentaResponseDTO> findByCliente(Long idCliente) {
        return ventaRepository.findAll()
                .stream()
                .filter(venta -> venta.getIdCliente().equals(idCliente))
                .map(ventaMapper::toResponse)
                .collect(Collectors.toList());
    }
}