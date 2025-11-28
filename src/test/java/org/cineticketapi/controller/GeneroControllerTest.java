package org.cineticketapi.controller;

import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.service.GeneroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class GeneroControllerTest {

    @Mock
    private GeneroService generoService;

    @InjectMocks
    private GeneroController generoController;

    private GeneroDto generoBase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        generoBase = GeneroDto.builder()
                .id(1L)
                .nombre("Acción")
                .descripcion("Películas de acción")
                .build();
    }

    // ========= LISTAR =========

    @Test
    void testListarGeneros() {
        when(generoService.listar()).thenReturn(List.of(generoBase));

        List<GeneroDto> result = generoController.listar();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Acción", result.get(0).getNombre());
    }

    // ========= OBTENER UNO =========

    @Test
    void testObtenerGeneroPorId_OK() {
        when(generoService.obtenerPorId(anyLong())).thenReturn(Optional.of(generoBase));

        ResponseEntity<GeneroDto> response = generoController.uno(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Acción", response.getBody().getNombre());
    }

    @Test
    void testObtenerGeneroPorId_NoEncontrado() {
        when(generoService.obtenerPorId(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<GeneroDto> response = generoController.uno(1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    // ========= CREAR =========

    @Test
    void testCrearGenero() {
        when(generoService.guardar(any())).thenReturn(Optional.of(generoBase));

        ResponseEntity<GeneroDto> response = generoController.crear(generoBase);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Acción", response.getBody().getNombre());
    }

    // ========= ACTUALIZAR =========

    @Test
    void testActualizarGenero_OK() {

        when(generoService.actualizar(any(), anyLong())).thenReturn(Optional.of(generoBase));
        ResponseEntity<GeneroDto> response = generoController.actualizar(1L, generoBase);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    // ========= ELIMINAR =========

    @Test
    void testEliminarGenero_OK() {
        when(generoService.obtenerPorId(1L)).thenReturn(Optional.of(generoBase));
        doNothing().when(generoService).eliminar(1L);

        ResponseEntity<Void> response = generoController.eliminar(1L);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(generoService).eliminar(1L);
    }

    @Test
    void testEliminarGenero_NoEncontrado() {
        when(generoService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = generoController.eliminar(1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(generoService, never()).eliminar(anyLong());
    }
}
