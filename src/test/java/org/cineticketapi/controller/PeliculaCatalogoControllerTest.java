package org.cineticketapi.controller;

import org.cineticketapi.dto.PeliculaCatalogoReqDto;
import org.cineticketapi.dto.PeliculaCatalogoRespDto;
import org.cineticketapi.service.PeliculaCatalogoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PeliculaCatalogoControllerTest {

    @Mock
    private PeliculaCatalogoService peliculaCatalogoService;

    @InjectMocks
    private PeliculaCatalogoController peliculaCatalogoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<PeliculaCatalogoRespDto> lista = Arrays.asList(new PeliculaCatalogoRespDto());
        when(peliculaCatalogoService.getPeliculas()).thenReturn(lista);

        ResponseEntity<List<PeliculaCatalogoRespDto>> response = peliculaCatalogoController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetByIdFound() {
        Long id = 1L;
        PeliculaCatalogoRespDto dto = new PeliculaCatalogoRespDto();

        when(peliculaCatalogoService.getById(id)).thenReturn(Optional.of(dto));

        ResponseEntity<PeliculaCatalogoRespDto> response = peliculaCatalogoController.getById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetByIdNotFound() {
        when(peliculaCatalogoService.getById(99L)).thenReturn(Optional.empty());

        ResponseEntity<PeliculaCatalogoRespDto> response = peliculaCatalogoController.getById(99L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreate() {
        PeliculaCatalogoReqDto req = new PeliculaCatalogoReqDto();
        PeliculaCatalogoRespDto dto = new PeliculaCatalogoRespDto();

        when(peliculaCatalogoService.create(req)).thenReturn(Optional.of(dto));

        ResponseEntity<PeliculaCatalogoRespDto> response = peliculaCatalogoController.create(req);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDelete() {
        when(peliculaCatalogoService.delete(1L)).thenReturn(1L);

        ResponseEntity<String> response = peliculaCatalogoController.delete(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Película eliminada con éxito", response.getBody());
    }

}
