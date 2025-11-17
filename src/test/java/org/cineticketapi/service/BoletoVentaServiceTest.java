package org.cineticketapi.service;

import org.cineticketapi.dto.BoletoVentaReqDto;
import org.cineticketapi.dto.BoletoVentaRespDto;
import org.cineticketapi.mapper.BoletoVentaMapper;
import org.cineticketapi.model.BoletoVenta;
import org.cineticketapi.repository.BoletoVentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BoletoVentaServiceTest {

    @Mock
    private BoletoVentaRepository boletoVentaRepository;

    @Mock
    private BoletoVentaMapper boletoVentaMapper;

    @InjectMocks
    private BoletoVentaService boletoVentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBoletos() {
        BoletoVenta boleto = new BoletoVenta();
        when(boletoVentaRepository.findAll()).thenReturn(List.of(boleto));
        when(boletoVentaMapper.toDto(boleto)).thenReturn(new BoletoVentaRespDto());

        List<BoletoVentaRespDto> result = boletoVentaService.getBoletos();

        assertEquals(1, result.size());
        verify(boletoVentaRepository, times(1)).findAll();
    }

    @Test
    void testGetBoletoById() {
        BoletoVenta boleto = new BoletoVenta();
        when(boletoVentaRepository.findById(1L)).thenReturn(Optional.of(boleto));
        when(boletoVentaMapper.toDto(boleto)).thenReturn(new BoletoVentaRespDto());

        Optional<BoletoVentaRespDto> result = boletoVentaService.getBoletoById(1L);

        assertTrue(result.isPresent());
        verify(boletoVentaRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateBoleto() {
        BoletoVentaReqDto dto = new BoletoVentaReqDto();
        BoletoVenta entity = new BoletoVenta();
        BoletoVenta saved = new BoletoVenta();

        when(boletoVentaMapper.toEntity(dto)).thenReturn(entity);
        when(boletoVentaRepository.save(entity)).thenReturn(saved);
        when(boletoVentaMapper.toDto(saved)).thenReturn(new BoletoVentaRespDto());

        Optional<BoletoVentaRespDto> result = boletoVentaService.createBoleto(dto);

        assertTrue(result.isPresent());
        verify(boletoVentaRepository, times(1)).save(entity);
    }

    @Test
    void testDeleteBoleto() {
        BoletoVenta boleto = new BoletoVenta();
        when(boletoVentaRepository.findById(1L)).thenReturn(Optional.of(boleto));

        Long deletedId = boletoVentaService.deleteBoleto(1L);

        assertEquals(1L, deletedId);
        verify(boletoVentaRepository, times(1)).delete(boleto);
    }
}

