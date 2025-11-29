package org.cineticketapi.service;

import org.cineticketapi.dto.VentaRequestDTO;
import org.cineticketapi.mapper.VentaMapper;
import org.cineticketapi.model.Venta;
import org.cineticketapi.repository.VentaRepository;
import org.cineticketapi.util.enums.ModelEnums;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;

class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @Spy
    private VentaMapper ventaMapper;

    @InjectMocks
    private VentaService ventaService;

    private Venta venta;
    private VentaRequestDTO request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        venta = Venta.builder()
                .idVenta(1L)
                .idCliente(10L)
                .fechaVenta(LocalDateTime.now())
                .total(new BigDecimal("15.00"))
                .metodoPago(ModelEnums.MetodoPago.TARJETA)
                .build();

        request = VentaRequestDTO.builder()
                .idCliente(10L)
                .total(new BigDecimal("15.00"))
                .metodoPago(ModelEnums.MetodoPago.TARJETA)
                .build();
    }

    @Test
    void testFindAll() {
        when(ventaRepository.findAll()).thenReturn(List.of(venta));

        var result = ventaService.findAll();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testFindById() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));

        var result = ventaService.findById(1L);

        Assertions.assertEquals(1L, result.getIdVenta());
    }

    @Test
    void testFindById_NotFound() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,
                () -> ventaService.findById(1L));
    }

    @Test
    void testCreate() {
        when(ventaRepository.save(any())).thenReturn(venta);

        var result = ventaService.create(request);

        Assertions.assertEquals(1L, result.getIdVenta());
    }

    @Test
    void testUpdate() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));
        when(ventaRepository.save(any())).thenReturn(venta);

        var result = ventaService.update(1L, request);

        Assertions.assertNotNull(result);
    }

    @Test
    void testDelete() {
        when(ventaRepository.existsById(1L)).thenReturn(true);

        ventaService.delete(1L);

        verify(ventaRepository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(ventaRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThrows(Exception.class,
                () -> ventaService.delete(1L));
    }

    @Test
    void testFindByCliente() {
        when(ventaRepository.findAll()).thenReturn(List.of(venta));

        var result = ventaService.findByCliente(10L);

        Assertions.assertEquals(1, result.size());
    }
}
