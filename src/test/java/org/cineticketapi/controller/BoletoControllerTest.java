package org.cineticketapi.controller;

import org.cineticketapi.dto.BoletoRequestDTO;
import org.cineticketapi.dto.BoletoResponseDTO;
import org.cineticketapi.service.BoletoService;
import org.cineticketapi.util.enums.ModelEnums;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BoletoControllerTest {

    @Mock
    private BoletoService boletoService;

    @InjectMocks
    private BoletoController boletoController;

    private BoletoResponseDTO boletoResponse;
    private BoletoRequestDTO boletoRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        boletoResponse = new BoletoResponseDTO(
                1L,
                10L,
                5L,
                7L,
                LocalDate.now(),
                ModelEnums.EstadoBoleto.VALIDO
        );

        boletoRequest = new BoletoRequestDTO(
                10L,
                5L,
                7L,
                LocalDate.now(),
                ModelEnums.EstadoBoleto.VALIDO
        );
    }

    @Test
    void testGetAllBoletos() {
        when(boletoService.findAll()).thenReturn(List.of(boletoResponse));

        ResponseEntity<List<BoletoResponseDTO>> result = boletoController.getAllBoletos();

        assertEquals(1, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testGetBoletoById() {
        when(boletoService.findById(1L)).thenReturn(boletoResponse);

        ResponseEntity<BoletoResponseDTO> result = boletoController.getBoletoById(1L);

        assertEquals(1L, result.getBody().getIdBoleto());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testCreateBoleto() {
        when(boletoService.create(any())).thenReturn(boletoResponse);

        ResponseEntity<BoletoResponseDTO> result = boletoController.createBoleto(boletoRequest);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals(10L, result.getBody().getIdFuncion());
    }

    @Test
    void testUpdateBoleto() {
        when(boletoService.update(eq(1L), any())).thenReturn(boletoResponse);

        ResponseEntity<BoletoResponseDTO> result = boletoController.updateBoleto(1L, boletoRequest);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(5L, result.getBody().getIdCliente());
    }

    @Test
    void testDeleteBoleto() {
        doNothing().when(boletoService).delete(1L);

        ResponseEntity<Void> result = boletoController.deleteBoleto(1L);

        assertEquals(204, result.getStatusCodeValue());
        verify(boletoService, times(1)).delete(1L);
    }

    @Test
    void testGetBoletosByCliente() {
        when(boletoService.findByCliente(5L)).thenReturn(List.of(boletoResponse));

        ResponseEntity<List<BoletoResponseDTO>> result = boletoController.getBoletosByCliente(5L);

        assertEquals(1, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testGetBoletosByFuncion() {
        when(boletoService.findByFuncion(10L)).thenReturn(List.of(boletoResponse));

        ResponseEntity<List<BoletoResponseDTO>> result = boletoController.getBoletosByFuncion(10L);

        assertEquals(1, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }
}
