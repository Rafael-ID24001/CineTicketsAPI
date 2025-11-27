package org.cineticketapi.controller;

import org.cineticketapi.dto.BoletoVentaReqDto;
import org.cineticketapi.dto.BoletoVentaRespDto;
import org.cineticketapi.service.BoletoVentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BoletoVentaControllerTest {

    @Mock
    private BoletoVentaService boletoVentaService;

    @InjectMocks
    private BoletoVentaController boletoVentaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<BoletoVentaRespDto> lista = Arrays.asList(new BoletoVentaRespDto());
        when(boletoVentaService.getBoletos()).thenReturn(lista);

        ResponseEntity<List<BoletoVentaRespDto>> response = boletoVentaController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(lista, response.getBody());
    }

    @Test
    void testGetById() {
        Long idBoleto = 1L;
        Long idVenta = 1L;
        BoletoVentaRespDto dto = new BoletoVentaRespDto();

        when(boletoVentaService.getBoletoById(idBoleto, idVenta))
                .thenReturn(Optional.of(dto));

        ResponseEntity<BoletoVentaRespDto> response =
                boletoVentaController.getById(idBoleto, idVenta);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetById_NotFound() {
        when(boletoVentaService.getBoletoById(1L, 1L))
                .thenReturn(Optional.empty());

        ResponseEntity<BoletoVentaRespDto> response =
                boletoVentaController.getById(1L, 1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreate() {
        BoletoVentaReqDto req = new BoletoVentaReqDto();
        BoletoVentaRespDto dto = new BoletoVentaRespDto();

        when(boletoVentaService.createBoleto(req)).thenReturn(Optional.of(dto));

        ResponseEntity<BoletoVentaRespDto> response =
                boletoVentaController.create(req);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDelete() {
        Long idBoleto = 1L;
        Long idVenta = 1L;

        //  Para métodos void se usa doNothing()
        doNothing().when(boletoVentaService).deleteBoleto(idBoleto, idVenta);

        ResponseEntity<String> response =
                boletoVentaController.delete(idBoleto, idVenta);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Boleto eliminado con éxito", response.getBody());
        verify(boletoVentaService, times(1)).deleteBoleto(idBoleto, idVenta);
    }
}
