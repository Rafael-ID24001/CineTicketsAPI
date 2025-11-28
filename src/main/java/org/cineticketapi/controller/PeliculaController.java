package org.cineticketapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.ApiResponse;
import org.cineticketapi.dto.PeliculaDto;
import org.cineticketapi.service.PeliculaService;
import org.cineticketapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/pelicula")
@RequiredArgsConstructor
public class PeliculaController extends BaseController {
    @Autowired
    private PeliculaService service;

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<?>> listar() {
        try {
            List<PeliculaDto> lista = service.listar();
            return ResponseEntity.ok(okApiResponse(lista));
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ApiResponse<?>> obtener(@PathVariable Long id) {
        try {
            Optional<PeliculaDto> dto = service.obtenerPorId(id);
            if (dto.isEmpty()) {
                // En tu estilo: devolver cuerpo de error con 200 OK
                return ResponseEntity.ok(errorApiResponse("No se encontró la película con id " + id, null));
            }
            return ResponseEntity.ok(okApiResponse(dto.get()));
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<?>> crear(@Valid @RequestBody PeliculaDto dto) {
        try {
            PeliculaDto creado = service.crear(dto);
            return ResponseEntity.ok(okApiResponse(creado));
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ApiResponse<?>> actualizar(@PathVariable Long id,
                                                     @Valid @RequestBody PeliculaDto dto) {
        try {
            PeliculaDto actualizado = service.actualizar(id, dto);
            return ResponseEntity.ok(okApiResponse(actualizado));
        } catch (NoSuchElementException notFound) {
            // Mantengo el patrón de 200 OK + cuerpo de error, como tu AsientoController
            return ResponseEntity.ok(errorApiResponse(notFound.getMessage(), null));
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok(okApiResponse("Eliminado correctamente"));
        } catch (NoSuchElementException notFound) {
            return ResponseEntity.ok(errorApiResponse(notFound.getMessage(), null));
        } catch (Exception e) {
            String message = Constants.MSG_ERROR_500 + e.getMessage();
            return ResponseEntity.internalServerError().body(errorApiResponse(message, null));
        }
    }
}
