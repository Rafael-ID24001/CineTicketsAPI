package org.cineticketapi.controller;

import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.AsientoDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.service.AsientoService;
import org.cineticketapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asiento")
public class AsientoController extends BaseController{

    @Autowired
    private AsientoService asientoService;

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<?>> getAsientosBySalaCine(@RequestParam Long idSalaCine) {
        try {
            List<AsientoDto> asientoList = asientoService.getAsientos(idSalaCine);
            return ResponseEntity.ok(okApiResponse(asientoList));
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @GetMapping("/buscar/{idAsiento}")
    public ResponseEntity<ApiResponse<?>> getAsientoById(@PathVariable("idAsiento") Long idAsiento) {
        try {
            Optional<AsientoDto> asiento = asientoService.findAsientoById(idAsiento);
            return ResponseEntity.ok(okApiResponse(asiento.get()));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse<?>> updateAsiento(@RequestParam Long idAsiento, String estadoAsiento) {
        try {
            Optional<AsientoDto> asiento = asientoService.updateAsiento(idAsiento, estadoAsiento.toUpperCase());
            return ResponseEntity.ok(okApiResponse(asiento.get()));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/eliminar/{idAsiento}")
    public ResponseEntity<ApiResponse<?>> deleteAsiento(@PathVariable("idAsiento")  Long idAsiento) {
        try {
            Long asientoDeleted = asientoService.deleteAsiento(idAsiento);
            return ResponseEntity.ok(okApiResponse(asientoDeleted));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorApiResponse(e.getMessage(), null));
        }
    }
}
