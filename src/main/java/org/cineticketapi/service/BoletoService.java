package org.cineticketapi.service;

import org.cineticketapi.model.Boleto;
import org.cineticketapi.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoletoService {

@Autowired                  
    private  BoletoRepository boletoRepository;
@Autowired     
    private  BoletoMapper boletoMapper;

    public List<BoletoDTO> findAll() {
        return boletoRepository.findAll()
                .stream()
                .map(boletoMapper::toDto)
                .collect(Collectors.toList());
    }

    public BoletoDTO findById(Long id) {
        return boletoRepository.findById(id)
                .map(boletoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Boleto no encontrado con ID: " + id));
    }

    public BoletoDTO create(BoletoDTO boletoDTO) {
        Boleto boleto = boletoMapper.toEntity(boletoDTO);
        boleto.setIdBoleto(null); // aseguramos que se cree nuevo
        return boletoMapper.toDto(boletoRepository.save(boleto));
    }

    public BoletoDTO update(Long id, BoletoDTO boletoDTO) {
        Boleto existente = boletoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleto no encontrado con ID: " + id));

        existente.setFuncion(boletoMapper.toEntity(boletoDTO).getFuncion());
        existente.setCliente(boletoMapper.toEntity(boletoDTO).getCliente());
        existente.setAsiento(boletoMapper.toEntity(boletoDTO).getAsiento());
        existente.setEstado(boletoDTO.getEstado());

        return boletoMapper.toDto(boletoRepository.save(existente));
    }

    public void delete(Long id) {
        if (!boletoRepository.existsById(id)) {
            throw new RuntimeException("Boleto no encontrado con ID: " + id);
        }
        boletoRepository.deleteById(id);
    }
}

