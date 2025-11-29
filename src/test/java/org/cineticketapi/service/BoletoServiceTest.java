package org.cineticketapi.service;

import org.cineticketapi.dto.BoletoRequestDTO;
import org.cineticketapi.mapper.BoletoMapper;
import org.cineticketapi.model.Boleto;
import org.cineticketapi.repository.BoletoRepository;
import org.cineticketapi.util.enums.ModelEnums;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

class BoletoServiceTest {

    @Mock
    private BoletoRepository boletoRepository;

    @Spy
    private BoletoMapper boletoMapper;

    @InjectMocks
    private BoletoService boletoService;

    private Boleto boleto;
    private BoletoRequestDTO request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        boleto = Boleto.builder()
                .idBoleto(1L)
                .idCliente(10L)
                .idFuncion(20L)
                .idAsiento(30L)
                .fechaCompra(LocalDate.now())
                .estado(ModelEnums.EstadoBoleto.VALIDO)
                .build();

        request = new BoletoRequestDTO(
                20L, 10L, 30L, LocalDate.now(), ModelEnums.EstadoBoleto.VALIDO
        );
    }

    @Test
    void testFindAll() {
        when(boletoRepository.findAll()).thenReturn(List.of(boleto));

        var result = boletoService.findAll();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testFindById() {
        when(boletoRepository.findById(1L)).thenReturn(Optional.of(boleto));

        var result = boletoService.findById(1L);

        Assertions.assertEquals(1L, result.getIdBoleto());
    }

    @Test
    void testFindById_NotFound() {
        when(boletoRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class,
                () -> boletoService.findById(1L));
    }

    @Test
    void testCreate() {
        when(boletoRepository.save(any())).thenReturn(boleto);

        var result = boletoService.create(request);

        Assertions.assertEquals(1L, result.getIdBoleto());
    }

    @Test
    void testUpdate() {
        when(boletoRepository.findById(1L)).thenReturn(Optional.of(boleto));
        when(boletoRepository.save(any())).thenReturn(boleto);

        var result = boletoService.update(1L, request);

        Assertions.assertNotNull(result);
    }

    @Test
    void testDelete() {
        when(boletoRepository.findById(1L)).thenReturn(Optional.of(boleto));

        boletoService.delete(1L);

        verify(boletoRepository).delete(boleto);
    }

    @Test
    void testDelete_NotFound() {
        when(boletoRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class,
                () -> boletoService.delete(1L));
    }

    @Test
    void testFindByCliente() {
        when(boletoRepository.findAll()).thenReturn(List.of(boleto));

        var result = boletoService.findByCliente(10L);

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testFindByFuncion() {
        when(boletoRepository.findAll()).thenReturn(List.of(boleto));

        var result = boletoService.findByFuncion(20L);

        Assertions.assertEquals(1, result.size());
    }
}
