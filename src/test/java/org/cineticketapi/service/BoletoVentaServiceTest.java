package org.cineticketapi.service;

import org.cineticketapi.dto.BoletoVentaReqDto;
import org.cineticketapi.dto.BoletoVentaRespDto;
import org.cineticketapi.mapper.BoletoVentaMapper;
import org.cineticketapi.model.boletoVenta.BoletoVenta;
import org.cineticketapi.model.boletoVenta.BoletoVentaId;
import org.cineticketapi.repository.BoletoVentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        BoletoVenta entity = new BoletoVenta();
        when(boletoVentaRepository.findAll()).thenReturn(List.of(entity));
        when(boletoVentaMapper.toDto(entity)).thenReturn(new BoletoVentaRespDto());

        List<BoletoVentaRespDto> result = boletoVentaService.getBoletos();

        assertEquals(1, result.size());
        verify(boletoVentaRepository, times(1)).findAll();
    }

    @Test
    void testGetBoletoById() {
        BoletoVentaId id = new BoletoVentaId(1L, 2L);
        BoletoVenta entity = new BoletoVenta();
        BoletoVentaRespDto dto = new BoletoVentaRespDto();

        when(boletoVentaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(boletoVentaMapper.toDto(entity)).thenReturn(dto);

        Optional<BoletoVentaRespDto> result = boletoVentaService.getBoletoById(1L, 2L);

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
    }

    @Test
    void testCreateBoleto() {
        BoletoVentaReqDto req = new BoletoVentaReqDto();
        BoletoVenta entity = new BoletoVenta();
        BoletoVenta saved = new BoletoVenta();
        BoletoVentaRespDto dto = new BoletoVentaRespDto();

        when(boletoVentaMapper.toEntity(req)).thenReturn(entity);
        when(boletoVentaRepository.save(entity)).thenReturn(saved);
        when(boletoVentaMapper.toDto(saved)).thenReturn(dto);

        Optional<BoletoVentaRespDto> result = boletoVentaService.createBoleto(req);

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
    }

    @Test
    void testDeleteBoleto() {
        BoletoVentaId id = new BoletoVentaId(1L, 2L);
        BoletoVenta entity = new BoletoVenta();

        when(boletoVentaRepository.findById(id)).thenReturn(Optional.of(entity));

        assertDoesNotThrow(() -> boletoVentaService.deleteBoleto(1L, 2L));

        verify(boletoVentaRepository, times(1)).delete(entity);
    }
}
