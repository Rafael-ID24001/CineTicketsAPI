package org.cineticketapi.controller;

import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.SalaCineDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.service.SalaDeCineService;
import org.cineticketapi.util.Constants;
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

class SalaDeCineControllerTest {
    @Mock
    SalaDeCineService salaCineService;

    @InjectMocks
    SalaDeCineController salaDeCineController;

    private SalaCineDto mockSalaCineDto;
    private SalaCineDto mockSalaCineDtoCreate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockSalaCineDto = SalaCineDto.builder()
                .idSala(1L)
                .nombre("Sala Principal")
                .capacidadTotal(100)
                .tipoSala("VIP")
                .estado("ACTIVA")
                .build();

        mockSalaCineDtoCreate = SalaCineDto.builder()
                .idSala(null)
                .nombre("Sala Nueva")
                .capacidadTotal(80)
                .tipoSala("IMAX")
                .estado(null)
                .build();
    }

    @Test
    void testGetSalas() {
        when(salaCineService.getSalas()).thenReturn(List.of(mockSalaCineDto));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.getSalas();

        Object data = result.getBody().getData();
        List<?> dataList = (List<?>) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1, dataList.size());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testGetSalas_Exception() {
        when(salaCineService.getSalas()).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.getSalas();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertTrue(result.getBody().getMessage().contains(Constants.MSG_ERROR_500));
    }

    @Test
    void testCreateSala() {
        when(salaCineService.createSala(any(SalaCineDto.class))).thenReturn(Optional.of(mockSalaCineDto));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.createSala(mockSalaCineDtoCreate);

        Object data = result.getBody().getData();
        SalaCineDto salaCineDto = (SalaCineDto) data;

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(1L, salaCineDto.getIdSala());
        Assertions.assertEquals("Sala Principal", salaCineDto.getNombre());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testCreateSala_ApiException() {
        when(salaCineService.createSala(any(SalaCineDto.class)))
                .thenThrow(new ApiException(HttpStatus.BAD_REQUEST, "Error de validación", "Datos inválidos"));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.createSala(mockSalaCineDtoCreate);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testCreateSala_ServerException() {
        when(salaCineService.createSala(any(SalaCineDto.class)))
                .thenThrow(new RuntimeException("Error interno del servidor"));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.createSala(mockSalaCineDtoCreate);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testUpdateSala() {
        when(salaCineService.update(any(SalaCineDto.class))).thenReturn(Optional.of(mockSalaCineDto));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.updateSala(mockSalaCineDto);

        Object data = result.getBody().getData();
        SalaCineDto salaCineDto = (SalaCineDto) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1L, salaCineDto.getIdSala());
        Assertions.assertEquals("Sala Principal", salaCineDto.getNombre());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testUpdateSala_ApiException() {
        when(salaCineService.update(any(SalaCineDto.class)))
                .thenThrow(new ApiException(HttpStatus.NOT_FOUND, "Sala no encontrada", null));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.updateSala(mockSalaCineDto);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testUpdateSala_ServerException() {
        when(salaCineService.update(any(SalaCineDto.class)))
                .thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.updateSala(mockSalaCineDto);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testDeleteSala() {
        when(salaCineService.deleteSala(anyLong())).thenReturn(1L);

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.deleteSala(1L);

        Object data = result.getBody().getData();
        Long idDeleted = (Long) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1L, idDeleted);
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testDeleteSala_ApiException() {
        when(salaCineService.deleteSala(anyLong()))
                .thenThrow(new ApiException(HttpStatus.NOT_FOUND, "Sala no encontrada para eliminar", null));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.deleteSala(1L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testDeleteSala_ServerException() {
        when(salaCineService.deleteSala(anyLong()))
                .thenThrow(new RuntimeException("Error de conexión a base de datos"));

        ResponseEntity<ApiResponse<?>> result = salaDeCineController.deleteSala(1L);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

}

