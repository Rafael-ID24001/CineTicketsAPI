package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.BoletoVentaReqDto;
import org.cineticketapi.dto.BoletoVentaRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.BoletoVentaMapper;
import org.cineticketapi.model.BoletoVenta;
import org.cineticketapi.repository.BoletoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoletoVentaService {

    @Autowired
    private BoletoVentaRepository boletoVentaRepository;

    @Autowired
    private BoletoVentaMapper boletoVentaMapper;

    public List<BoletoVentaRespDto> getBoletos() {
        List<BoletoVenta> boletos = boletoVentaRepository.findAll();
        return boletos.stream()
                .map(boletoVentaMapper::toDto)
                .toList();
    }

    public Optional<BoletoVentaRespDto> getBoletoById(Long idBoleto) {
        Optional<BoletoVenta> boleto = boletoVentaRepository.findById(idBoleto);
        if (!boleto.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Boleto no encontrado");
        }
        return Optional.ofNullable(boletoVentaMapper.toDto(boleto.get()));
    }

    public Optional<BoletoVentaRespDto> createBoleto(BoletoVentaReqDto dto) {
        BoletoVenta entity = boletoVentaMapper.toEntity(dto);
        BoletoVenta saved = boletoVentaRepository.save(entity);
        return Optional.ofNullable(boletoVentaMapper.toDto(saved));
    }

    public Long deleteBoleto(Long idBoleto) {
        Optional<BoletoVenta> boleto = boletoVentaRepository.findById(idBoleto);
        if (!boleto.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Boleto no encontrado");
        }
        boletoVentaRepository.delete(boleto.get());
        return idBoleto;
    }
}
