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
import static org.mockito.Mockito.when;

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
        List<BoletoVentaRespDto> mockList = Arrays.asList(new BoletoVentaRespDto());
        when(boletoVentaService.getBoletos()).thenReturn(mockList);

        ResponseEntity<List<BoletoVentaRespDto>> response = boletoVentaController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockList, response.getBody());
    }

    @Test
    void testGetById() {
        Long id = 1L;
        BoletoVentaRespDto mockDto = new BoletoVentaRespDto();

        when(boletoVentaService.getBoletoById(id)).thenReturn(Optional.of(mockDto));

        ResponseEntity<BoletoVentaRespDto> response = boletoVentaController.getById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockDto, response.getBody());
    }

    @Test
    void testCreate() {
        BoletoVentaReqDto req = new BoletoVentaReqDto();
        BoletoVentaRespDto mockResp = new BoletoVentaRespDto();

        when(boletoVentaService.createBoleto(req)).thenReturn(Optional.of(mockResp));

        ResponseEntity<BoletoVentaRespDto> response = boletoVentaController.create(req);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResp, response.getBody());
    }

    @Test
    void testDelete() {
        when(boletoVentaService.deleteBoleto(1L)).thenReturn(1L);

        ResponseEntity<String> response = boletoVentaController.delete(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Boleto eliminado con éxito", response.getBody());
    }

}
