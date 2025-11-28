package org.cineticketapi.service;

import org.cineticketapi.dto.SalaCineDto;
import org.cineticketapi.mapper.SalaDeCineMapper;
import org.cineticketapi.model.SalaDeCine;
import org.cineticketapi.repository.SalaDeCineRepository;
import org.cineticketapi.util.enums.ModelEnums;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class SalaDeCineServiceTest {
    @Mock
    SalaDeCineRepository salaCineRepository;
    @Mock
    AsientoService asientoService;
    @Mock
    FuncionService funcionService;
    @Spy
    SalaDeCineMapper salaDeCineMapper;
    @InjectMocks
    SalaDeCineService salaDeCineService;

    private SalaDeCine mockSalaDeCine;
    private SalaCineDto mockSalaCineDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockSalaDeCine = SalaDeCine.builder()
                .idSala(1L)
                .nombre("Sala 1")
                .capacidadTotal(100)
                .tipoSala(ModelEnums.TipoSala.IMAX)
                .estado(ModelEnums.EstadoSala.DISPONIBLE)
                .build();

        mockSalaCineDto = SalaCineDto.builder()
                .idSala(1L)
                .nombre("Sala 1")
                .capacidadTotal(100)
                .tipoSala("IMAX")
                .estado("DISPONIBLE")
                .build();
    }

    @Test
    void testGetSalas() {
        when(salaCineRepository.findAll()).thenReturn(List.of(mockSalaDeCine));

        List<SalaCineDto> result = salaDeCineService.getSalas();
        Assertions.assertEquals("DISPONIBLE", result.get(0).getEstado());
    }

    @Test
    void testFindSalaByNombre() {
        when(salaCineRepository.findByNombre(anyString())).thenReturn(Optional.of(mockSalaDeCine));

        Optional<SalaCineDto> result = salaDeCineService.findSalaByNombre(mockSalaCineDto);
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindSalaById() {
        when(salaCineRepository.findByIdSala(anyLong())).thenReturn(Optional.of(mockSalaDeCine));

        Optional<SalaCineDto> result = salaDeCineService.findSalaById(1L);
        Assertions.assertNotNull( result);
    }

    @Test
    void testCreateSala() {
        when(salaCineRepository.findByNombre(anyString())).thenReturn(Optional.ofNullable(new SalaDeCine()));
        when(salaCineRepository.save(any(SalaDeCine.class))).thenReturn(mockSalaDeCine);
        doNothing().when(asientoService).createAsientoSalaCine(anyLong(), anyInt(), anyInt());

        Optional<SalaCineDto> result = salaDeCineService.createSala(mockSalaCineDto);

        verify(asientoService).createAsientoSalaCine(anyLong(), anyInt(), anyInt());
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdate() {
        when(salaCineRepository.findByIdSala(anyLong())).thenReturn(Optional.of(mockSalaDeCine));
        when(salaCineRepository.findByNombre(anyString())).thenReturn(Optional.of(mockSalaDeCine));
        when(salaCineRepository.save(any(SalaDeCine.class))).thenReturn(mockSalaDeCine);

        Optional<SalaCineDto> result = salaDeCineService.update(mockSalaCineDto);
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdate_MANTENIMIENTO() {
        mockSalaCineDto.setEstado("MANTENIMIENTO");

        when(salaCineRepository.findByIdSala(anyLong())).thenReturn(Optional.of(mockSalaDeCine));
        when(salaCineRepository.findByNombre(anyString())).thenReturn(Optional.of(mockSalaDeCine));
        when(salaCineRepository.save(any(SalaDeCine.class))).thenReturn(mockSalaDeCine);
        when(funcionService.actualizarEstadoFuncionFutura(anyLong(), any())).thenReturn(1);

        Optional<SalaCineDto> result = salaDeCineService.update(mockSalaCineDto);
        Assertions.assertNotNull(result);
    }

    @Test
    void testDeleteSala() {
        when(salaCineRepository.findByIdSala(anyLong())).thenReturn(Optional.of(mockSalaDeCine));
        when(funcionService.actualizarEstadoFuncionFutura(anyLong(), any())).thenReturn(1);
        when(salaCineRepository.save(any(SalaDeCine.class))).thenReturn(mockSalaDeCine);

        Long result = salaDeCineService.deleteSala(1L);

        Assertions.assertEquals(Long.valueOf(1), result);
    }
}

