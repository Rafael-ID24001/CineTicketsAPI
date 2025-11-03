package org.cineticketapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.service.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GeneroDto crear(@Valid @RequestBody GeneroDto dto) {
        return service.crear(dto);
    }

    @PutMapping("/{id}")
    public GeneroDto actualizar(@PathVariable Long id, @Valid @RequestBody GeneroDto dto) {
        return service.actualizar(id, dto);
    }

    @GetMapping("/{id}")
    public GeneroDto obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @GetMapping
    public List<GeneroDto> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
