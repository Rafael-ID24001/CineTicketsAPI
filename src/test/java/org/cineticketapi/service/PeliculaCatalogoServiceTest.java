package org.cineticketapi.service;

import org.cineticketapi.dto.PeliculaCatalogoReqDto;
import org.cineticketapi.dto.PeliculaCatalogoRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.PeliculaCatalogoMapper;
import org.cineticketapi.model.PeliculaCatalogo;
import org.cineticketapi.repository.PeliculaCatalogoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @Test
    void testGetPeliculas() {
        List<PeliculaCatalogo> lista = Arrays.asList(new PeliculaCatalogo());
        when(peliculaCatalogoRepository.findAll()).thenReturn(lista);
        when(peliculaCatalogoMapper.toDto(any())).thenReturn(new PeliculaCatalogoRespDto());

        List<PeliculaCatalogoRespDto> result = peliculaCatalogoService.getPeliculas();

        assertEquals(1, result.size());
        verify(peliculaCatalogoRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        Long id = 1L;
        PeliculaCatalogo entity = new PeliculaCatalogo();
        PeliculaCatalogoRespDto dto = new PeliculaCatalogoRespDto();

        when(peliculaCatalogoRepository.findById(id)).thenReturn(Optional.of(entity));
        when(peliculaCatalogoMapper.toDto(entity)).thenReturn(dto);

        Optional<PeliculaCatalogoRespDto> result = peliculaCatalogoService.getById(id);

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
    }

    @Test
    void testGetByIdNotFound() {
        when(peliculaCatalogoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> peliculaCatalogoService.getById(99L));
    }

    @Test
    void testCreate() {
        PeliculaCatalogoReqDto req = new PeliculaCatalogoReqDto();
        PeliculaCatalogo entity = new PeliculaCatalogo();
        PeliculaCatalogo saved = new PeliculaCatalogo();
        PeliculaCatalogoRespDto dto = new PeliculaCatalogoRespDto();

        when(peliculaCatalogoMapper.toEntity(req)).thenReturn(entity);
        when(peliculaCatalogoRepository.save(entity)).thenReturn(saved);
        when(peliculaCatalogoMapper.toDto(saved)).thenReturn(dto);

        Optional<PeliculaCatalogoRespDto> result = peliculaCatalogoService.create(req);

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
    }

    @Test
    void testDelete() {
        Long id = 1L;
        PeliculaCatalogo entity = new PeliculaCatalogo();

        when(peliculaCatalogoRepository.findById(id)).thenReturn(Optional.of(entity));

        Long result = peliculaCatalogoService.delete(id);

        assertEquals(id, result);
        verify(peliculaCatalogoRepository, times(1)).delete(entity);
    }
}
