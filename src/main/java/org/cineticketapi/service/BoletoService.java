package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.BoletoRequestDTO;
import org.cineticketapi.dto.BoletoResponseDTO;
import org.cineticketapi.mapper.BoletoMapper;
import org.cineticketapi.model.Boleto;
import org.cineticketapi.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;
    @Autowired
    private BoletoMapper boletoMapper;

    public List<BoletoResponseDTO> findAll() {
        return boletoRepository.findAll()
                .stream()
                .map(boletoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BoletoResponseDTO findById(Long id) {
        Boleto boleto = boletoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleto no encontrado con id: " + id));
        return boletoMapper.toDTO(boleto);
    }

    public BoletoResponseDTO create(BoletoRequestDTO boletoRequestDTO) {
        Boleto boleto = boletoMapper.toEntity(boletoRequestDTO);
        Boleto savedBoleto = boletoRepository.save(boleto);
        return boletoMapper.toDTO(savedBoleto);
    }

    public BoletoResponseDTO update(Long id, BoletoRequestDTO boletoRequestDTO) {
        Boleto existingBoleto = boletoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleto no encontrado con id: " + id));

        boletoMapper.updateEntityFromDTO(boletoRequestDTO, existingBoleto);
        Boleto updatedBoleto = boletoRepository.save(existingBoleto);
        return boletoMapper.toDTO(updatedBoleto);
    }

    public void delete(Long id) {
        Boleto boleto = boletoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleto no encontrado con id: " + id));
        boletoRepository.delete(boleto);
    }

    public List<BoletoResponseDTO> findByCliente(Long idCliente) {
        return boletoRepository.findAll()
                .stream()
                .filter(boleto -> boleto.getIdCliente().equals(idCliente))
                .map(boletoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BoletoResponseDTO> findByFuncion(Long idFuncion) {
        return boletoRepository.findAll()
                .stream()
                .filter(boleto -> boleto.getIdFuncion().equals(idFuncion))
                .map(boletoMapper::toDTO)
                .collect(Collectors.toList());
    }
}

