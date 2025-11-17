package org.cineticketapi.controller;

import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.PeliculaCatalogoReqDto;
import org.cineticketapi.dto.PeliculaCatalogoRespDto;
import org.cineticketapi.service.PeliculaCatalogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculaCatalogo")
@RequiredArgsConstructor
public class PeliculaCatalogoController {

    private final PeliculaCatalogoService peliculaCatalogoService;

    // Obtener todas las películas
    @GetMapping
    public ResponseEntity<List<PeliculaCatalogoRespDto>> getAll() {
        return ResponseEntity.ok(peliculaCatalogoService.getPeliculas());
    }

    // Obtener una película por ID
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaCatalogoRespDto> getById(@PathVariable Long id) {
        return peliculaCatalogoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva película
    @PostMapping
    public ResponseEntity<PeliculaCatalogoRespDto> create(@RequestBody PeliculaCatalogoReqDto dto) {
        return peliculaCatalogoService.create(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    // Eliminar una película por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        peliculaCatalogoService.delete(id);
        return ResponseEntity.ok("Película eliminada con éxito");
    }
}
