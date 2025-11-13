package org.cineticketapi.service;

import org.cineticketapi.dto.FuncionReqDto;
import org.cineticketapi.dto.FuncionRespDto;
import org.cineticketapi.mapper.FuncionMapper;
import org.cineticketapi.model.Funcion;
import org.cineticketapi.projections.FuncionProjection;
import org.cineticketapi.repository.FuncionRepository;
import org.cineticketapi.util.enums.ModelEnums;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class FuncionServiceTest {
    @Mock
    FuncionRepository funcionRepository;
    @Spy
    FuncionMapper funcionMapper;
    @InjectMocks
    FuncionService funcionService;

    private FuncionProjection  mockFuncionProjection;
    private Funcion mockFuncion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockFuncionProjection = Mockito.mock(FuncionProjection.class);
        mockFuncion = Funcion.builder()
                .idFuncion(1L)
                .idSala(1L)
                .estado(ModelEnums.EstadoFuncion.PROGRAMADA)
                .fechaHora(LocalDateTime.now())
                .precioBoleto(new BigDecimal("20.00"))
                .build();


        when(mockFuncionProjection.getIdFuncion()).thenReturn(1L);
        when(mockFuncionProjection.getTituloPelicula()).thenReturn("Titulo Pelicula");
        when(mockFuncionProjection.getNombreSala()).thenReturn("Nombre Sala");
        when(mockFuncionProjection.getTipoSala()).thenReturn("REGULAR");
        when(mockFuncionProjection.getPrecioBoleto()).thenReturn(new BigDecimal("20.00"));
        when(mockFuncionProjection.getFechaHora()).thenReturn(LocalDateTime.now());
        when(mockFuncionProjection.getEstadoFuncion()).thenReturn("PROGRAMADA");
    }

    @Test
    void testGetFunciones() {
        when(funcionRepository.getFuncionesDetalles()).thenReturn(List.of(mockFuncionProjection));

        List<FuncionRespDto> result = funcionService.getFunciones();
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testFindFuncionById() {
        when(funcionRepository.findById(anyLong())).thenReturn(Optional.of(mockFuncion));
        when(funcionRepository.getFuncionDetallesByIdFuncion(anyLong())).thenReturn(Optional.of(mockFuncionProjection));

        Optional<FuncionRespDto> result = funcionService.findFuncionById(Long.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateFuncion() {

        FuncionReqDto mockFuncionReqDto = new FuncionReqDto();
        mockFuncion.setIdFuncion(1L);
        mockFuncion.setIdSala(1L);
        mockFuncion.setFechaHora(LocalDateTime.of(2025, Month.NOVEMBER, 10, 15, 52, 21));
        mockFuncion.setPrecioBoleto(new BigDecimal("20.00"));

        when(funcionRepository.findByIdPeliculaAndIdSalaEqualsAndFechaHora(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(Optional.of(mockFuncion));
        when(funcionRepository.save(any(Funcion.class))).thenReturn(mockFuncion);
        when(funcionRepository.getFuncionDetallesByIdFuncion(anyLong())).thenReturn(Optional.of(mockFuncionProjection));

        Optional<FuncionRespDto> result = funcionService.createFuncion(mockFuncionReqDto);

        Assertions.assertNotNull( result);
    }

    @Test
    void testUpdateFuncion() {
        when(funcionRepository.findById(anyLong())).thenReturn(Optional.of(mockFuncion));
        when(funcionRepository.save(any(Funcion.class))).thenReturn(mockFuncion);
        when(funcionRepository.getFuncionDetallesByIdFuncion(anyLong())).thenReturn(Optional.of(mockFuncionProjection));

        Optional<FuncionRespDto> result = funcionService.updateFuncion(Long.valueOf(1), "EN_CURSO");

        Assertions.assertNotNull(result);
    }

    @Test
    void testDeleteFuncion() {
        when(funcionRepository.findById(anyLong())).thenReturn(Optional.of(mockFuncion));
        when(funcionRepository.save(any(Funcion.class))).thenReturn(mockFuncion);
        when(funcionRepository.getFuncionDetallesByIdFuncion(anyLong())).thenReturn(Optional.of(mockFuncionProjection));

        Long result = funcionService.deleteFuncion(Long.valueOf(1));
        Assertions.assertEquals(1L, result);
    }
}
