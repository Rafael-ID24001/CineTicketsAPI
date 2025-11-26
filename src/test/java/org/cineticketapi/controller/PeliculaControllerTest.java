package org.cineticketapi.controller;

import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.PeliculaDto;
import org.cineticketapi.service.PeliculaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class PeliculaControllerTest {

    @Mock
    private PeliculaService peliculaService;

    @InjectMocks
    private PeliculaController peliculaController;

    private PeliculaDto mockPeliculaDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockPeliculaDto = PeliculaDto.builder()
                .idPelicula(1L)
                .titulo("Matrix")
                .duracion(120)
                .idGenero(1L)
                .clasificacion("PG-13")
                .sinopsis("Un clásico de ciencia ficción")
                .fechaEstreno(LocalDate.of(1999, 3, 31))
                .build();
    }


    @Test
    void testListarPeliculas_OK() {
        when(peliculaService.listar()).thenReturn(List.of(mockPeliculaDto));

        ResponseEntity<ApiResponse<?>> result = peliculaController.listar();
        Object data = result.getBody().getData();
        List<?> lista = (List<?>) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1, lista.size());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testListarPeliculas_ServerException() {
        when(peliculaService.listar()).thenThrow(new RuntimeException("Error de BD"));

        ResponseEntity<ApiResponse<?>> result = peliculaController.listar();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testObtenerPeliculaPorId_OK() {
        when(peliculaService.obtenerPorId(anyLong())).thenReturn(Optional.of(mockPeliculaDto));

        ResponseEntity<ApiResponse<?>> result = peliculaController.obtener(1L);
        Object data = result.getBody().getData();
        PeliculaDto dto = (PeliculaDto) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1L, dto.getIdPelicula());
        Assertions.assertEquals("Matrix", dto.getTitulo());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testObtenerPeliculaPorId_NoEncontrada() {
        when(peliculaService.obtenerPorId(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<ApiResponse<?>> result = peliculaController.obtener(1L);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode()); // 200, pero success = false
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testObtenerPeliculaPorId_ServerException() {
        when(peliculaService.obtenerPorId(anyLong())).thenThrow(new RuntimeException("Error de BD"));

        ResponseEntity<ApiResponse<?>> result = peliculaController.obtener(1L);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testCrearPelicula_OK() {
        when(peliculaService.crear(any(PeliculaDto.class))).thenReturn(mockPeliculaDto);

        ResponseEntity<ApiResponse<?>> result = peliculaController.crear(mockPeliculaDto);
        Object data = result.getBody().getData();
        PeliculaDto dto = (PeliculaDto) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("Matrix", dto.getTitulo());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testCrearPelicula_ServerException() {
        when(peliculaService.crear(any(PeliculaDto.class))).thenThrow(new RuntimeException("Error de BD"));

        ResponseEntity<ApiResponse<?>> result = peliculaController.crear(mockPeliculaDto);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testActualizarPelicula_OK() {
        when(peliculaService.actualizar(anyLong(), any(PeliculaDto.class))).thenReturn(mockPeliculaDto);

        ResponseEntity<ApiResponse<?>> result = peliculaController.actualizar(1L, mockPeliculaDto);
        Object data = result.getBody().getData();
        PeliculaDto dto = (PeliculaDto) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1L, dto.getIdPelicula());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testActualizarPelicula_NoEncontrada() {
        when(peliculaService.actualizar(anyLong(), any(PeliculaDto.class)))
                .thenThrow(new NoSuchElementException("No encontrada"));

        ResponseEntity<ApiResponse<?>> result = peliculaController.actualizar(1L, mockPeliculaDto);

        // El controlador devuelve 200 OK con success = false
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testActualizarPelicula_ServerException() {
        when(peliculaService.actualizar(anyLong(), any(PeliculaDto.class)))
                .thenThrow(new RuntimeException("Error de BD"));

        ResponseEntity<ApiResponse<?>> result = peliculaController.actualizar(1L, mockPeliculaDto);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testEliminarPelicula_OK() {
        doNothing().when(peliculaService).eliminar(anyLong());

        ResponseEntity<ApiResponse<?>> result = peliculaController.eliminar(1L);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testEliminarPelicula_NoEncontrada() {
        // el service lanza NoSuchElementException
        org.mockito.Mockito.doThrow(new NoSuchElementException("No encontrada"))
                .when(peliculaService).eliminar(anyLong());

        ResponseEntity<ApiResponse<?>> result = peliculaController.eliminar(1L);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode()); // 200 + errorApiResponse
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testEliminarPelicula_ServerException() {
        org.mockito.Mockito.doThrow(new RuntimeException("Error de BD"))
                .when(peliculaService).eliminar(anyLong());

        ResponseEntity<ApiResponse<?>> result = peliculaController.eliminar(1L);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }
}
