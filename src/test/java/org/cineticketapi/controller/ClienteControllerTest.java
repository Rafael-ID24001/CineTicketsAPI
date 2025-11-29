package org.cineticketapi.controller;

import org.cineticketapi.dto.ClienteRequestDTO;
import org.cineticketapi.dto.ClienteResponseDTO;
import org.cineticketapi.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private ClienteResponseDTO clienteResponse;
    private ClienteRequestDTO clienteRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteResponse = ClienteResponseDTO.builder()
                .idCliente(1L)
                .nombre("Juan Pérez")
                .email("juan@example.com")
                .telefono("12345678")
                .fechaRegistro(LocalDateTime.now())
                .build();

        clienteRequest = ClienteRequestDTO.builder()
                .nombre("Juan Pérez")
                .email("juan@example.com")
                .telefono("12345678")
                .build();
    }

    @Test
    void testGetAllClientes() {
        when(clienteService.findAll()).thenReturn(List.of(clienteResponse));

        ResponseEntity<List<ClienteResponseDTO>> result = clienteController.getAllClientes();

        assertEquals(1, result.getBody().size());
        assertEquals("Juan Pérez", result.getBody().get(0).getNombre());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testGetClienteById() {
        when(clienteService.findById(1L)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponseDTO> result = clienteController.getClienteById(1L);

        assertEquals("Juan Pérez", result.getBody().getNombre());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testCreateCliente() {
        when(clienteService.create(any())).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponseDTO> result = clienteController.createCliente(clienteRequest);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals("juan@example.com", result.getBody().getEmail());
    }

    @Test
    void testUpdateCliente() {
        when(clienteService.update(eq(1L), any())).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponseDTO> result = clienteController.updateCliente(1L, clienteRequest);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1L, result.getBody().getIdCliente());
    }

    @Test
    void testDeleteCliente() {
        doNothing().when(clienteService).delete(1L);

        ResponseEntity<Void> result = clienteController.deleteCliente(1L);

        assertEquals(204, result.getStatusCodeValue());
        verify(clienteService, times(1)).delete(1L);
    }
}
