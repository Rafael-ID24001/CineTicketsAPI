package org.cineticketapi.service;

import org.cineticketapi.dto.ClienteRequestDTO;
import org.cineticketapi.dto.ClienteResponseDTO;
import org.cineticketapi.mapper.ClienteMapper;
import org.cineticketapi.model.Cliente;
import org.cineticketapi.repository.ClienteRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Spy
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteRequestDTO request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = Cliente.builder()
                .idCliente(1L)
                .nombre("Juan Perez")
                .email("juan@test.com")
                .telefono("12345678")
                .fechaRegistro(LocalDateTime.now())
                .build();

        request = ClienteRequestDTO.builder()
                .nombre("Juan Perez")
                .email("juan@test.com")
                .telefono("12345678")
                .build();
    }

    @Test
    void testFindAll() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        var result = clienteService.findAll();

        Assertions.assertEquals(1, result.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void testFindById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        var result = clienteService.findById(1L);

        Assertions.assertEquals("Juan Perez", result.getNombre());
        verify(clienteRepository).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                () -> clienteService.findById(1L));

        Assertions.assertTrue(ex.getMessage().contains("Cliente no encontrado"));
    }

    @Test
    void testCreate() {
        when(clienteRepository.existsClienteByEmail("juan@test.com")).thenReturn(false);
        when(clienteRepository.save(any())).thenReturn(cliente);

        var result = clienteService.create(request);

        Assertions.assertEquals("Juan Perez", result.getNombre());
        verify(clienteRepository).save(any());
    }

    @Test
    void testCreate_EmailExists() {
        when(clienteRepository.existsClienteByEmail("juan@test.com")).thenReturn(true);

        Assertions.assertThrows(RuntimeException.class,
                () -> clienteService.create(request));
    }

    @Test
    void testUpdate() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.existsClienteByEmail(anyString())).thenReturn(false);
        when(clienteRepository.save(any())).thenReturn(cliente);

        var result = clienteService.update(1L, request);

        Assertions.assertEquals("Juan Perez", result.getNombre());
    }

    @Test
    void testUpdate_EmailExistsInAnother() {
        Cliente another = Cliente.builder()
                .idCliente(2L)
                .email("nuevo@test.com")
                .build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.existsClienteByEmail("nuevo@test.com")).thenReturn(true);

        ClienteRequestDTO req = ClienteRequestDTO.builder()
                .nombre("AAA")
                .email("nuevo@test.com")
                .telefono("111")
                .build();

        Assertions.assertThrows(RuntimeException.class,
                () -> clienteService.update(1L, req));
    }

    @Test
    void testDelete() {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        clienteService.delete(1L);

        verify(clienteRepository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class,
                () -> clienteService.delete(1L));
    }
}
