package org.cineticketapi.controller;

import jakarta.validation.Valid;
import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {
@Autowired
    private GeneroService service;

    @GetMapping public List<GeneroDto> listar(){ return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDto> uno(@PathVariable Long id){
        return service.obtenerPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneroDto> crear(@RequestBody @Valid GeneroDto g){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(g).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDto> actualizar(@PathVariable Long id, @RequestBody @Valid GeneroDto body){
        return ResponseEntity.ok(service.actualizar(body, id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (service.obtenerPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        service.eliminar(id); return ResponseEntity.noContent().build();
    }
}
