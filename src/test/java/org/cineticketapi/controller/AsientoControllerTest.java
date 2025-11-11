package org.cineticketapi.controller;

import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.AsientoDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.service.AsientoService;
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

import static org.mockito.Mockito.*;

class AsientoControllerTest {
    @Mock
    AsientoService asientoService;

    @InjectMocks
    AsientoController asientoController;

    private AsientoDto mockAsientoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockAsientoDto = AsientoDto.builder()
                .idAsiento(1L)
                .nombreSala("Sala Principal")
                .filaAsiento("A")
                .numeroAsiento(5)
                .tipoAsiento("VIP")
                .estadoAsiento("DISPONIBLE")
                .build();
    }

    @Test
    void testGetAsientosBySalaCine() {
        when(asientoService.getAsientos(anyLong())).thenReturn(List.of(mockAsientoDto));

        ResponseEntity<ApiResponse<?>> result = asientoController.getAsientosBySalaCine(1L);

        Object data = result.getBody().getData();
        List<?> dataList = (List<?>) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1, dataList.size());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testGetAsientosBySalaCine_Exception() {
        when(asientoService.getAsientos(anyLong())).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = asientoController.getAsientosBySalaCine(1L);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testGetAsientoById() {
        when(asientoService.findAsientoById(anyLong())).thenReturn(Optional.of(mockAsientoDto));

        ResponseEntity<ApiResponse<?>> result = asientoController.getAsientoById(1L);
        Object data = result.getBody().getData();
        AsientoDto asientoDto = (AsientoDto) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1L, asientoDto.getIdAsiento());
        Assertions.assertEquals("Sala Principal", asientoDto.getNombreSala());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testGetAsientoById_APIException() {
        when(asientoService.findAsientoById(anyLong())).thenThrow(new ApiException(HttpStatus.NOT_FOUND, "Asiento no encontrado"));

        ResponseEntity<ApiResponse<?>> result = asientoController.getAsientoById(1L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testGetAsientoById_ServerException() {
        when(asientoService.findAsientoById(anyLong())).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = asientoController.getAsientoById(1L);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }


    @Test
    void testUpdateAsiento() {
        when(asientoService.updateAsiento(anyLong(), anyString())).thenReturn(Optional.of(mockAsientoDto));

        ResponseEntity<ApiResponse<?>> result = asientoController.updateAsiento(1L, "DISPONIBLE");
        Object data = result.getBody().getData();
        AsientoDto asientoDto = (AsientoDto) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1L, asientoDto.getIdAsiento());
        Assertions.assertEquals("DISPONIBLE", asientoDto.getEstadoAsiento());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testUpdateAsiento_APIException() {
        when(asientoService.updateAsiento(anyLong(), anyString())).thenThrow(new ApiException(HttpStatus.BAD_REQUEST, "Estado no válido"));

        ResponseEntity<ApiResponse<?>> result = asientoController.updateAsiento(1L, "INVALIDO");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testUpdateAsiento_ServerException() {
        when(asientoService.updateAsiento(anyLong(), anyString())).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = asientoController.updateAsiento(1L, "DISPONIBLE");

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testDeleteAsiento() {
        when(asientoService.deleteAsiento(anyLong())).thenReturn(1L);

        ResponseEntity<ApiResponse<?>> result = asientoController.deleteAsiento(1L);
        Object data = result.getBody().getData();
        Long idEliminado = (Long) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1L, idEliminado);
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testDeleteAsiento_APIException() {
        when(asientoService.deleteAsiento(anyLong())).thenThrow(new ApiException(HttpStatus.NOT_FOUND, "Asiento no encontrado"));

        ResponseEntity<ApiResponse<?>> result = asientoController.deleteAsiento(1L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testDeleteAsiento_ServerException() {
        when(asientoService.deleteAsiento(anyLong())).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = asientoController.deleteAsiento(1L);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }
}
