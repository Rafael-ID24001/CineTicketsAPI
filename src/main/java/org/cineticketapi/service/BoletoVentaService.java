package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.BoletoVentaReqDto;
import org.cineticketapi.dto.BoletoVentaRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.BoletoVentaMapper;
import org.cineticketapi.model.boletoVenta.BoletoVenta;
import org.cineticketapi.model.boletoVenta.BoletoVentaId;
import org.cineticketapi.repository.BoletoVentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoletoVentaService {

    private final BoletoVentaRepository boletoVentaRepository;
    private final BoletoVentaMapper boletoVentaMapper;

    public BoletoVentaService(
            BoletoVentaRepository boletoVentaRepository,
            BoletoVentaMapper boletoVentaMapper
    ) {
        this.boletoVentaRepository = boletoVentaRepository;
        this.boletoVentaMapper = boletoVentaMapper;
    }

    public List<BoletoVentaRespDto> getBoletos() {
        return boletoVentaRepository.findAll()
                .stream()
                .map(boletoVentaMapper::toDto)
                .toList();
    }

    public Optional<BoletoVentaRespDto> getBoletoById(Long idBoleto, Long idVenta) {

        BoletoVentaId id = new BoletoVentaId(idBoleto, idVenta);

        Optional<BoletoVenta> entity = boletoVentaRepository.findById(id);

        if (entity.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Boleto no encontrado");
        }

        return Optional.of(boletoVentaMapper.toDto(entity.get()));
    }

    public Optional<BoletoVentaRespDto> createBoleto(BoletoVentaReqDto dto) {
        BoletoVenta entity = boletoVentaMapper.toEntity(dto);
        BoletoVenta saved = boletoVentaRepository.save(entity);
        return Optional.of(boletoVentaMapper.toDto(saved));
    }

    public void deleteBoleto(Long idBoleto, Long idVenta) {

        BoletoVentaId id = new BoletoVentaId(idBoleto, idVenta);

        Optional<BoletoVenta> entity = boletoVentaRepository.findById(id);

        if (entity.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Boleto no encontrado");
        }

        boletoVentaRepository.delete(entity.get());
    }
}
