package org.cineticketapi.controller;

import org.cineticketapi.dto.VentaRequestDTO;
import org.cineticketapi.dto.VentaResponseDTO;
import org.cineticketapi.service.VentaService;
import org.cineticketapi.util.enums.ModelEnums;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VentaControllerTest {

    @Mock
    private VentaService ventaService;

    @InjectMocks
    private VentaController ventaController;

    private VentaResponseDTO ventaResponse;
    private VentaRequestDTO ventaRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ventaResponse = VentaResponseDTO.builder()
                .idVenta(1L)
                .idCliente(5L)
                .total(BigDecimal.valueOf(8.50))
                .fechaVenta(LocalDateTime.now())
                .metodoPago(ModelEnums.MetodoPago.TARJETA)
                .build();

        ventaRequest = VentaRequestDTO.builder()
                .idCliente(5L)
                .total(BigDecimal.valueOf(8.50))
                .metodoPago(ModelEnums.MetodoPago.TARJETA)
                .build();
    }

    @Test
    void testGetAllVentas() {
        when(ventaService.findAll()).thenReturn(List.of(ventaResponse));

        ResponseEntity<List<VentaResponseDTO>> result = ventaController.getAllVentas();

        assertEquals(1, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testGetVentaById() {
        when(ventaService.findById(1L)).thenReturn(ventaResponse);

        ResponseEntity<VentaResponseDTO> result = ventaController.getVentaById(1L);

        assertEquals(1L, result.getBody().getIdVenta());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testGetVentasByCliente() {
        when(ventaService.findByCliente(5L)).thenReturn(List.of(ventaResponse));

        ResponseEntity<List<VentaResponseDTO>> result = ventaController.getVentasByCliente(5L);

        assertEquals(1, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testCreateVenta() {
        when(ventaService.create(any())).thenReturn(ventaResponse);

        ResponseEntity<VentaResponseDTO> result = ventaController.createVenta(ventaRequest);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals(5L, result.getBody().getIdCliente());
    }

    @Test
    void testUpdateVenta() {
        when(ventaService.update(eq(1L), any())).thenReturn(ventaResponse);

        ResponseEntity<VentaResponseDTO> result = ventaController.updateVenta(1L, ventaRequest);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testDeleteVenta() {
        doNothing().when(ventaService).delete(1L);

        ResponseEntity<Void> result = ventaController.deleteVenta(1L);

        assertEquals(204, result.getStatusCodeValue());
        verify(ventaService, times(1)).delete(1L);
    }
}
