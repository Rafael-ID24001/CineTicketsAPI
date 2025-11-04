package org.cineticketapi.controller;

import jakarta.validation.Valid;
import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.FuncionReqDto;
import org.cineticketapi.dto.FuncionRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.service.FuncionService;
import org.cineticketapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionescine")
public class FuncionController extends BaseController{

    @Autowired
    private FuncionService funcionService;

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<?>> getfunciones() {
        try {
            List<FuncionRespDto> funcionList = funcionService.getFunciones();
            return ResponseEntity.ok(okApiResponse(funcionList));
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @GetMapping("/buscar/{idFuncion}")
    public ResponseEntity<ApiResponse<?>> getfuncionbyid(@PathVariable("idFuncion") Long idFuncion) {
        try {
            Optional<FuncionRespDto> funcion = funcionService.finFunciondById(idFuncion);
            return ResponseEntity.ok(okApiResponse(funcion.get()));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<?>> createSala(@Valid @RequestBody FuncionReqDto reqDto) {
        try {
            Optional<FuncionRespDto> funcion = funcionService.createFuncion(reqDto);
            return ResponseEntity.created(null).body(createdApiResponse(funcion.get()));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse<?>> updateSala(@RequestParam Long idFuncion, String estadoFuncion) {
        try {
            Optional<FuncionRespDto> sala = funcionService.updateFuncion(idFuncion, estadoFuncion.toUpperCase());
            return ResponseEntity.ok(okApiResponse(sala.get()));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/eliminar/{idFuncion}")
    public ResponseEntity<ApiResponse<?>> deleteSala(@PathVariable("idFuncion")  Long idFuncion) {
        try {
            Long funcionDeleted = funcionService.deleteFuncion(idFuncion);
            return ResponseEntity.ok(okApiResponse(funcionDeleted));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorApiResponse(e.getMessage(), null));
        }
    }
}
