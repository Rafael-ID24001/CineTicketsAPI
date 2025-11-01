package org.cineticketapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.SalaCineDto;
import org.cineticketapi.dto.validationTypes.CreateValidation;
import org.cineticketapi.dto.validationTypes.UpdateValidation;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.service.SalaDeCineService;
import org.cineticketapi.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saladecine")
@RequiredArgsConstructor
public class SalaDeCineController extends BaseController {
    private final SalaDeCineService salaCineService;

    @GetMapping("/obtenerSalas")
    public ResponseEntity<ApiResponse<?>> getSalas() {
        try {
            List<SalaCineDto> salasList = salaCineService.getSalas();
            return ResponseEntity.ok(okApiResponse(salasList));
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @PostMapping("/crearSala")
    public ResponseEntity<ApiResponse<?>> createSala(@Validated(CreateValidation.class) @RequestBody SalaCineDto salaDto) {
        try {
            Optional<SalaCineDto> sala = salaCineService.createSala(salaDto);
            return ResponseEntity.created(null).body(createdApiResponse(sala.get()));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/actualizarSala")
    public ResponseEntity<ApiResponse<?>> updateSala(@Validated(UpdateValidation.class) @RequestBody SalaCineDto salaDto) {
        try {
            Optional<SalaCineDto> sala = salaCineService.update(salaDto);
            return ResponseEntity.ok(okApiResponse(sala.get()));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/eliminarSala/{salaCineId}")
    public ResponseEntity<ApiResponse<?>> deleteSala(@PathVariable("salaCineId")  Long salaCineId) {
        try {
            Long salaDeleted = salaCineService.deleteSala(salaCineId);
            return ResponseEntity.ok(okApiResponse(salaDeleted));
        } catch (ApiException ex) {
            return apiExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getData());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorApiResponse(e.getMessage(), null));
        }
    }

}
