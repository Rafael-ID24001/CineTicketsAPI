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

    @GetMapping
    public ResponseEntity<List<PeliculaCatalogoRespDto>> getAll() {
        return ResponseEntity.ok(peliculaCatalogoService.getPeliculas());
    }

    @GetMapping("/{idPelicula}/{idCatalogo}")
    public ResponseEntity<PeliculaCatalogoRespDto> getById(
            @PathVariable Long idPelicula,
            @PathVariable Long idCatalogo
    ) {
        return peliculaCatalogoService.getById(idPelicula, idCatalogo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PeliculaCatalogoRespDto> create(
            @RequestBody PeliculaCatalogoReqDto dto
    ) {
        return peliculaCatalogoService.create(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{idPelicula}/{idCatalogo}")
    public ResponseEntity<String> delete(
            @PathVariable Long idPelicula,
            @PathVariable Long idCatalogo
    ) {
        peliculaCatalogoService.delete(idPelicula, idCatalogo);
        return ResponseEntity.ok("Película eliminada con éxito");

    }
}
