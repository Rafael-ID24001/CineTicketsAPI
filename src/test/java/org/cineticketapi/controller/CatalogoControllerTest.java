package org.cineticketapi.controller;

import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.CatalogoReqDto;
import org.cineticketapi.dto.CatalogoRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.service.CatalogoService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CatalogoControllerTest {

    @Mock
    private CatalogoService catalogoService;

    @InjectMocks
    private CatalogoController catalogoController;

    private CatalogoRespDto mockCatalogoRespDto;
    private CatalogoReqDto mockCatalogoReqDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockCatalogoRespDto = CatalogoRespDto.builder()
                .idCatalogo(1L)
                .tipo("TIPO")
                .codigo("COD001")
                .descripcion("Descripción de prueba")
                .activo(true)
                .build();

        mockCatalogoReqDto = new CatalogoReqDto(
                "TIPO",
                "COD001",
                "Descripción de prueba",
                true
        );
    }

    @Test
    void testListarCatalogos() {
        when(catalogoService.getCatalogos()).thenReturn(List.of(mockCatalogoRespDto));

        ResponseEntity<ApiResponse<?>> result = catalogoController.listar();

        Object data = result.getBody().getData();
        List<?> dataList = (List<?>) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1, dataList.size());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testListarCatalogos_ApiException() {
        when(catalogoService.getCatalogos())
                .thenThrow(new ApiException(HttpStatus.BAD_REQUEST, "Error en la solicitud"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.listar();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testListarCatalogos_ServerException() {
        when(catalogoService.getCatalogos())
                .thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.listar();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertTrue(result.getBody().getMessage().startsWith(Constants.MSG_ERROR_500));
    }

    @Test
    void testBuscarCatalogoPorId() {
        when(catalogoService.findCatalogoById(anyLong()))
                .thenReturn(Optional.of(mockCatalogoRespDto));

        ResponseEntity<ApiResponse<?>> result = catalogoController.buscar(1L);

        Object data = result.getBody().getData();
        @SuppressWarnings("unchecked")
        Optional<CatalogoRespDto> opt = (Optional<CatalogoRespDto>) data;
        CatalogoRespDto catalogo = opt.orElse(null);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(catalogo);
        Assertions.assertEquals(1L, catalogo.getIdCatalogo());
        Assertions.assertEquals("TIPO", catalogo.getTipo());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testBuscarCatalogoPorId_ApiException() {
        when(catalogoService.findCatalogoById(anyLong()))
                .thenThrow(new ApiException(HttpStatus.NOT_FOUND, "Catálogo no encontrado"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.buscar(1L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testBuscarCatalogoPorId_ServerException() {
        when(catalogoService.findCatalogoById(anyLong()))
                .thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.buscar(1L);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }


    @Test
    void testCrearCatalogo() {
        when(catalogoService.createCatalogo(any(CatalogoReqDto.class)))
                .thenReturn(Optional.of(mockCatalogoRespDto));

        ResponseEntity<ApiResponse<?>> result = catalogoController.crear(mockCatalogoReqDto);

        Object data = result.getBody().getData();
        @SuppressWarnings("unchecked")
        Optional<CatalogoRespDto> opt = (Optional<CatalogoRespDto>) data;
        CatalogoRespDto catalogo = opt.orElse(null);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(catalogo);
        Assertions.assertEquals("COD001", catalogo.getCodigo());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testCrearCatalogo_ApiException() {
        when(catalogoService.createCatalogo(any(CatalogoReqDto.class)))
                .thenThrow(new ApiException(HttpStatus.BAD_REQUEST, "Ya existe catálogo"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.crear(mockCatalogoReqDto);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testCrearCatalogo_ServerException() {
        when(catalogoService.createCatalogo(any(CatalogoReqDto.class)))
                .thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.crear(mockCatalogoReqDto);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testActualizarCatalogo() {
        when(catalogoService.updateCatalogo(anyLong(), any(CatalogoReqDto.class)))
                .thenReturn(Optional.of(mockCatalogoRespDto));

        ResponseEntity<ApiResponse<?>> result = catalogoController.actualizar(1L, mockCatalogoReqDto);

        Object data = result.getBody().getData();
        @SuppressWarnings("unchecked")
        Optional<CatalogoRespDto> opt = (Optional<CatalogoRespDto>) data;
        CatalogoRespDto catalogo = opt.orElse(null);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(catalogo);
        Assertions.assertEquals(1L, catalogo.getIdCatalogo());
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testActualizarCatalogo_ApiException() {
        when(catalogoService.updateCatalogo(anyLong(), any(CatalogoReqDto.class)))
                .thenThrow(new ApiException(HttpStatus.NOT_FOUND, "No existe catálogo"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.actualizar(1L, mockCatalogoReqDto);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testActualizarCatalogo_ServerException() {
        when(catalogoService.updateCatalogo(anyLong(), any(CatalogoReqDto.class)))
                .thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.actualizar(1L, mockCatalogoReqDto);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }

    @Test
    void testEliminarCatalogo() {
        when(catalogoService.deleteCatalogo(anyLong())).thenReturn(1L);

        ResponseEntity<ApiResponse<?>> result = catalogoController.eliminar(1L);

        Object data = result.getBody().getData();
        Long idEliminado = (Long) data;

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1L, idEliminado);
        Assertions.assertTrue(result.getBody().isSuccess());
    }

    @Test
    void testEliminarCatalogo_ApiException() {
        when(catalogoService.deleteCatalogo(anyLong()))
                .thenThrow(new ApiException(HttpStatus.NOT_FOUND, "Catálogo no encontrado"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.eliminar(1L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
        Assertions.assertEquals(400, result.getBody().getStatusCode());
    }

    @Test
    void testEliminarCatalogo_ServerException() {
        when(catalogoService.deleteCatalogo(anyLong()))
                .thenThrow(new RuntimeException("Error de base de datos"));

        ResponseEntity<ApiResponse<?>> result = catalogoController.eliminar(1L);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertFalse(result.getBody().isSuccess());
    }
}

