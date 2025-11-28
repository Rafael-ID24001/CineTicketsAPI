package org.cineticketapi.controller;

import jakarta.validation.Valid;
import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.CatalogoReqDto;
import org.cineticketapi.dto.CatalogoRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.service.CatalogoService;
import org.cineticketapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogos")
public class CatalogoController extends BaseController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<?>> listar() {
        try {
            List<CatalogoRespDto> data = catalogoService.getCatalogos();
            return ResponseEntity.ok(okApiResponse(data));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(errorApiResponse(Constants.MSG_ERROR_500 + e.getMessage(), null));
        }
    }

    @GetMapping("/buscar/{idCatalogo}")
    public ResponseEntity<ApiResponse<?>> buscar(@PathVariable Long idCatalogo) {
        try {
            return ResponseEntity.ok(okApiResponse(catalogoService.findCatalogoById(idCatalogo)));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(errorApiResponse(Constants.MSG_ERROR_500 + e.getMessage(), null));
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<?>> crear(@Valid @RequestBody CatalogoReqDto req) {
        try {
            return ResponseEntity.ok(okApiResponse(catalogoService.createCatalogo(req)));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(errorApiResponse(Constants.MSG_ERROR_500 + e.getMessage(), null));
        }
    }

    @PutMapping("/actualizar/{idCatalogo}")
    public ResponseEntity<ApiResponse<?>> actualizar(@PathVariable Long idCatalogo,
                                                     @Valid @RequestBody CatalogoReqDto req) {
        try {
            return ResponseEntity.ok(okApiResponse(catalogoService.updateCatalogo(idCatalogo, req)));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(errorApiResponse(Constants.MSG_ERROR_500 + e.getMessage(), null));
        }
    }

    @DeleteMapping("/eliminar/{idCatalogo}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable Long idCatalogo) {
        try {
            Long deletedId = catalogoService.deleteCatalogo(idCatalogo);
            return ResponseEntity.ok(okApiResponse(deletedId));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(errorApiResponse(Constants.MSG_ERROR_500 + e.getMessage(), null));
        }
    }
}
