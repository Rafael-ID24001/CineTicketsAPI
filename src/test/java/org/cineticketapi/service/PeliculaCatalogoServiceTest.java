package org.cineticketapi.service;

import org.cineticketapi.dto.PeliculaCatalogoReqDto;
import org.cineticketapi.dto.PeliculaCatalogoRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.PeliculaCatalogoMapper;
import org.cineticketapi.model.peliculaCatalogo.PeliculaCatalogo;
import org.cineticketapi.model.peliculaCatalogo.PeliculaCatalogoId;
import org.cineticketapi.repository.PeliculaCatalogoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PeliculaCatalogoServiceTest {

    @Mock
    private PeliculaCatalogoRepository peliculaCatalogoRepository;

    @Mock
    private PeliculaCatalogoMapper peliculaCatalogoMapper;

    @InjectMocks
    private PeliculaCatalogoService peliculaCatalogoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ------------------------------------------------------------
    // GET ALL
    // ------------------------------------------------------------
    @Test
    void testGetPeliculas() {

        PeliculaCatalogo entity = PeliculaCatalogo.builder()
                .idPelicula(1L)
                .idCatalogo(2L)
                .fechaAgregado(LocalDateTime.now())
                .build();

        when(peliculaCatalogoRepository.findAll()).thenReturn(Arrays.asList(entity));
        when(peliculaCatalogoMapper.toDto(any())).thenReturn(new PeliculaCatalogoRespDto());

        List<PeliculaCatalogoRespDto> result = peliculaCatalogoService.getPeliculas();

        assertEquals(1, result.size());
        verify(peliculaCatalogoRepository, times(1)).findAll();
    }

    // ------------------------------------------------------------
    // GET BY ID
    // ------------------------------------------------------------
    @Test
    void testGetById() {

        PeliculaCatalogoId id = new PeliculaCatalogoId(1L, 2L);

        PeliculaCatalogo entity = PeliculaCatalogo.builder()
                .idPelicula(1L)
                .idCatalogo(2L)
                .fechaAgregado(LocalDateTime.now())
                .build();

        PeliculaCatalogoRespDto dto = new PeliculaCatalogoRespDto();

        when(peliculaCatalogoRepository.findById(id)).thenReturn(Optional.of(entity));
        when(peliculaCatalogoMapper.toDto(entity)).thenReturn(dto);

        Optional<PeliculaCatalogoRespDto> result =
                peliculaCatalogoService.getById(1L, 2L);

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
    }

    @Test
    void testGetByIdNotFound() {

        PeliculaCatalogoId id = new PeliculaCatalogoId(1L, 2L);

        when(peliculaCatalogoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ApiException.class,
                () -> peliculaCatalogoService.getById(1L, 2L));
    }

    // ------------------------------------------------------------
    // CREATE
    // ------------------------------------------------------------
    @Test
    void testCreate() {

        PeliculaCatalogoReqDto req = new PeliculaCatalogoReqDto();
        req.setIdPelicula(1L);
        req.setIdCatalogo(2L);

        PeliculaCatalogo entity = new PeliculaCatalogo();
        PeliculaCatalogo saved = new PeliculaCatalogo();
        PeliculaCatalogoRespDto dto = new PeliculaCatalogoRespDto();

        when(peliculaCatalogoMapper.toEntity(req)).thenReturn(entity);
        when(peliculaCatalogoRepository.save(entity)).thenReturn(saved);
        when(peliculaCatalogoMapper.toDto(saved)).thenReturn(dto);

        Optional<PeliculaCatalogoRespDto> result =
                peliculaCatalogoService.create(req);

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
    }

    // ------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------
    @Test
    void testDelete() {

        PeliculaCatalogoId id = new PeliculaCatalogoId(1L, 2L);

        PeliculaCatalogo entity = PeliculaCatalogo.builder()
                .idPelicula(1L)
                .idCatalogo(2L)
                .fechaAgregado(LocalDateTime.now())
                .build();

        when(peliculaCatalogoRepository.findById(id)).thenReturn(Optional.of(entity));

        // Act - no debe lanzar excepción
        assertDoesNotThrow(() -> peliculaCatalogoService.delete(1L, 2L));

        // Verify delete called
        verify(peliculaCatalogoRepository, times(1)).delete(entity);
    }
}
