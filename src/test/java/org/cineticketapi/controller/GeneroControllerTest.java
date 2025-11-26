package org.cineticketapi.controller;

import org.cineticketapi.model.Genero;
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

    private Genero generoBase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        generoBase = Genero.builder()
                .idGenero(1L)
                .nombre("Acción")
                .descripcion("Películas de acción")
                .build();
    }

    // ========= LISTAR =========

    @Test
    void testListarGeneros() {
        when(generoService.listar()).thenReturn(List.of(generoBase));

        List<Genero> result = generoController.listar();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Acción", result.get(0).getNombre());
    }

    // ========= OBTENER UNO =========

    @Test
    void testObtenerGeneroPorId_OK() {
        when(generoService.obtenerPorId(anyLong())).thenReturn(Optional.of(generoBase));

        ResponseEntity<Genero> response = generoController.uno(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Acción", response.getBody().getNombre());
    }

    @Test
    void testObtenerGeneroPorId_NoEncontrado() {
        when(generoService.obtenerPorId(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Genero> response = generoController.uno(1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    // ========= CREAR =========

    @Test
    void testCrearGenero() {
        when(generoService.guardar(any(Genero.class))).thenReturn(generoBase);

        ResponseEntity<Genero> response = generoController.crear(generoBase);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Acción", response.getBody().getNombre());
    }

    // ========= ACTUALIZAR =========

    @Test
    void testActualizarGenero_OK() {
        Genero body = Genero.builder()
                .nombre("Drama")
                .descripcion("Películas dramáticas")
                .build();

        when(generoService.obtenerPorId(1L)).thenReturn(Optional.of(generoBase));
        // devolver lo que se guarda (ya con los cambios del body)
        when(generoService.guardar(any(Genero.class)))
                .thenAnswer(inv -> inv.getArgument(0, Genero.class));

        ResponseEntity<Genero> response = generoController.actualizar(1L, body);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Drama", response.getBody().getNombre());
        Assertions.assertEquals("Películas dramáticas", response.getBody().getDescripcion());
    }

    @Test
    void testActualizarGenero_NoEncontrado() {
        Genero body = Genero.builder()
                .nombre("Drama")
                .descripcion("Películas dramáticas")
                .build();

        when(generoService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Genero> response = generoController.actualizar(1L, body);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
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
