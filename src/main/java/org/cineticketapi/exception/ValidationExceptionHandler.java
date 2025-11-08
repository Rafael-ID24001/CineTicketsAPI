package org.cineticketapi.exception;

import org.cineticketapi.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // Lógica de extracción de errores
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldError) {
                errors.put(fieldError.getField(), error.getDefaultMessage());
            } else {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
        });

        ApiResponse<Map<String, String>> apiResponse = validationErrorApiResponse(errors);

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    public ApiResponse<Map<String, String>> validationErrorApiResponse(Map<String, String> errors) {
        return ApiResponse.<Map<String, String>>builder()
                .success(false)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("La solicitud contiene errores de validación.")
                .data(errors) // mapa de errores
                .build();
    }
}
