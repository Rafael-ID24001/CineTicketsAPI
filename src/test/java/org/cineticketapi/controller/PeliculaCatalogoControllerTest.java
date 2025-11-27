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

    // ------------------------------------------------------
    // GET ALL
    // ------------------------------------------------------
    @Test
    void testGetAll() {
        List<PeliculaCatalogoRespDto> lista =
                Arrays.asList(new PeliculaCatalogoRespDto());

        when(peliculaCatalogoService.getPeliculas()).thenReturn(lista);

        ResponseEntity<List<PeliculaCatalogoRespDto>> response =
                peliculaCatalogoController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    // ------------------------------------------------------
    // GET BY ID COMPUESTO
    // ------------------------------------------------------
    @Test
    void testGetByIdFound() {
        PeliculaCatalogoRespDto dto = new PeliculaCatalogoRespDto();

        when(peliculaCatalogoService.getById(1L, 2L))
                .thenReturn(Optional.of(dto));

        ResponseEntity<PeliculaCatalogoRespDto> response =
                peliculaCatalogoController.getById(1L, 2L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetByIdNotFound() {
        when(peliculaCatalogoService.getById(1L, 2L))
                .thenReturn(Optional.empty());

        ResponseEntity<PeliculaCatalogoRespDto> response =
                peliculaCatalogoController.getById(1L, 2L);

        assertEquals(404, response.getStatusCodeValue());
    }

    // ------------------------------------------------------
    // CREATE
    // ------------------------------------------------------
    @Test
    void testCreate() {
        PeliculaCatalogoReqDto req = new PeliculaCatalogoReqDto();
        PeliculaCatalogoRespDto dto = new PeliculaCatalogoRespDto();

        when(peliculaCatalogoService.create(req))
                .thenReturn(Optional.of(dto));

        ResponseEntity<PeliculaCatalogoRespDto> response =
                peliculaCatalogoController.create(req);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    // ------------------------------------------------------
    // DELETE (ID COMPUESTO)
    // ------------------------------------------------------
    @Test
    void testDelete() {

        // El delete ahora es void → solo verificamos el ResponseEntity
        ResponseEntity<String> response =
                peliculaCatalogoController.delete(1L, 2L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Película eliminada con éxito", response.getBody());
    }
}
