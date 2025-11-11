package org.cineticketapi.service;

import org.cineticketapi.dto.AsientoDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.AsientoMapper;
import org.cineticketapi.model.Asiento;
import org.cineticketapi.projections.AsientoProjection;
import org.cineticketapi.repository.AsientoRepository;
import org.cineticketapi.util.enums.ModelEnums;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class AsientoServiceTest {
    @Mock
    AsientoRepository asientoRepository;
    @Spy
    AsientoMapper asientoMapper;
    @InjectMocks
    AsientoService asientoService;

    private Asiento asiento;
    private AsientoProjection mockProjection;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        asiento = Asiento.builder()
                .idAsiento(100L)
                .idSalaCine(1L)
                .fila("A")
                .numero(1)
                .tipoAsiento(ModelEnums.TipoAsiento.REGULAR)
                .estado(ModelEnums.EstadoAsiento.DISPONIBLE)
                .build();

        mockProjection = mock(AsientoProjection.class);
        when(mockProjection.getIdAsiento()).thenReturn(100L);
        when(mockProjection.getNombreSala()).thenReturn("Sala 1");
        when(mockProjection.getFilaAsiento()).thenReturn("A");
        when(mockProjection.getNumeroAsiento()).thenReturn(5);
        when(mockProjection.getTipoAsiento()).thenReturn("VIP");
        when(mockProjection.getEstadoAsiento()).thenReturn("OCUPADO");
    }


    @Test
    void testGetAsientos() {
        when(asientoRepository.listAsientosDetails(anyLong())).thenReturn(List.of(mockProjection, mockProjection));

        List<AsientoDto> result = asientoService.getAsientos(Long.valueOf(1));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testFindAsientoById() {
        Long idAsiento = 1L;

        when(asientoRepository.findById(anyLong())).thenReturn(Optional.of(asiento));
        when(asientoRepository.getAsientosDetailsByID(anyLong())).thenReturn(Optional.of(mockProjection));

        Optional<AsientoDto> result = asientoService.findAsientoById(idAsiento);
        verify(asientoRepository).getAsientosDetailsByID(idAsiento);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void testFindAsientoById_Fails() {
        when(asientoRepository.findById(anyLong())).thenReturn(Optional.empty());

        ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
            asientoService.findAsientoById(1L);
        });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        Assertions.assertEquals("Registro no encontrada", exception.getMessage());
    }

    @Test
    void testCreateAsientoSalaCine() {
        when(asientoRepository.save(any())).thenReturn(new Asiento());

        asientoService.createAsientoSalaCine(1L, 12, 200);

        verify(asientoRepository, atLeastOnce()).save(any());
    }

    @Test
    void testUpdateAsiento() {
        when(asientoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(asiento));
        when(asientoRepository.save(any())).thenReturn(asiento);
        when(asientoRepository.getAsientosDetailsByID(anyLong())).thenReturn(Optional.ofNullable(mockProjection));

        Optional<AsientoDto> result = asientoService.updateAsiento(1L, "OCUPADO");
        Assertions.assertNotEquals(null, result.get());
    }

    @Test
    void testDeleteAsiento() {
        Long  idAsiento = 100L;
        when(asientoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(asiento));
        when(asientoRepository.save(any())).thenReturn(asiento);
        when(asientoRepository.getAsientosDetailsByID(anyLong())).thenReturn(Optional.ofNullable(mockProjection));

        Long result = asientoService.deleteAsiento(idAsiento);
        Assertions.assertEquals(idAsiento, result);
    }

}
