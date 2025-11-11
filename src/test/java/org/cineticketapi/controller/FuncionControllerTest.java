package org.cineticketapi.controller;

import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.FuncionReqDto;
import org.cineticketapi.dto.FuncionRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.service.FuncionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class FuncionControllerTest {
    @Mock
    FuncionService funcionService;

    @InjectMocks
    FuncionController funcionController;

    private FuncionRespDto mockFuncionRespDto;
    private FuncionReqDto mockFuncionReqDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockFuncionRespDto = FuncionRespDto.builder()
                .idFuncion(1L)
                .tituloPelicula("Titulo Pelicula")
                .nombreSala("Nombre Sala")
                .tipoSala("VIP")
                .fechaHora(LocalDateTime.now())
                .precioBoleto(new BigDecimal("200.00"))
                .estadoFuncion("PROGRAMADA")
                .build();

        mockFuncionReqDto = new FuncionReqDto();
        mockFuncionReqDto.setIdPelicula(3l);
        mockFuncionReqDto.setIdSala(2L);
        mockFuncionReqDto.setFechaHora(LocalDateTime.now());
        mockFuncionReqDto.setPrecioBoleto(new BigDecimal("7.55"));
    }

    @Test
    void testGetFunciones() {

        when(funcionService.getFunciones()).thenReturn(List.of(mockFuncionRespDto));

        ResponseEntity<ApiResponse<?>> result = funcionController.getFunciones();

        Object data = result.getBody().getData();
        List<?> dataList = (List<?>) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1, dataList.size());
    }

    @Test
    void testGetFunciones_Exception() {

        when(funcionService.getFunciones()).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = funcionController.getFunciones();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testGetFuncionByID() {
        when(funcionService.findFuncionById(anyLong())).thenReturn(Optional.of(mockFuncionRespDto));

        ResponseEntity<ApiResponse<?>> result = funcionController.getFuncionByID(Long.valueOf(1));
        Object data = result.getBody().getData();
        FuncionRespDto funcionRespDto = (FuncionRespDto) data;

        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(1, funcionRespDto.getIdFuncion());
    }

    @Test
    void testGetFuncionByID_APIException() {
        when(funcionService.findFuncionById(anyLong())).thenThrow(new ApiException(HttpStatus.BAD_REQUEST, "Dato no encontrado"));

        ResponseEntity<ApiResponse<?>> result = funcionController.getFuncionByID(Long.valueOf(1));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testGetFuncionByID_ServerException() {
        when(funcionService.findFuncionById(anyLong())).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = funcionController.getFuncionByID(Long.valueOf(1));

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testCreateSala() {
        when(funcionService.createFuncion(any(FuncionReqDto.class))).thenReturn(Optional.of(mockFuncionRespDto));

        ResponseEntity<ApiResponse<?>> result = funcionController.createSala(mockFuncionReqDto);

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void testCreateSala_APIException() {
        when(funcionService.createFuncion(any(FuncionReqDto.class))).thenThrow(new ApiException(HttpStatus.BAD_REQUEST, "Dato ya existente"));

        ResponseEntity<ApiResponse<?>> result = funcionController.createSala(mockFuncionReqDto);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testCreateSala_ServerException() {
        when(funcionService.createFuncion(any(FuncionReqDto.class))).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = funcionController.createSala(mockFuncionReqDto);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testUpdateSala() {
        when(funcionService.updateFuncion(anyLong(), anyString())).thenReturn(Optional.of(mockFuncionRespDto));

        ResponseEntity<ApiResponse<?>> result = funcionController.updateSala(Long.valueOf(1), "estadoFuncion");

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testUpdateSala_APIException() {
        when(funcionService.updateFuncion(anyLong(), anyString())).thenThrow(new ApiException(HttpStatus.BAD_REQUEST, "Estado no encontrado"));

        ResponseEntity<ApiResponse<?>> result = funcionController.updateSala(Long.valueOf(1), "estadoFuncion");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testUpdateSala_ServerException() {
        when(funcionService.updateFuncion(anyLong(), anyString())).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = funcionController.updateSala(Long.valueOf(1), "estadoFuncion");

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testDeleteSala() {
        when(funcionService.deleteFuncion(anyLong())).thenReturn(Long.valueOf(1));

        ResponseEntity<ApiResponse<?>> result = funcionController.deleteSala(Long.valueOf(1));
        Object data = result.getBody().getData();
        Long updatedId = (Long) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertTrue(1L == updatedId);
    }

    @Test
    void testDeleteSala_APIException() {
        when(funcionService.deleteFuncion(anyLong())).thenThrow(new ApiException(HttpStatus.BAD_REQUEST, "Estado no encontrado"));

        ResponseEntity<ApiResponse<?>> result = funcionController.deleteSala(Long.valueOf(1));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testDeleteSala_ServerException() {
        when(funcionService.deleteFuncion(anyLong())).thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = funcionController.deleteSala(Long.valueOf(1));

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

}

