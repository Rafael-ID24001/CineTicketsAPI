package org.cineticketapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.model.Genero;
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

    @GetMapping public List<Genero> listar(){ return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> uno(@PathVariable Long id){
        return service.obtenerPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Genero> crear(@RequestBody @Valid Genero g){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(g));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> actualizar(@PathVariable Long id, @RequestBody @Valid Genero body){
        return service.obtenerPorId(id).map(db -> {
            db.setNombre(body.getNombre());
            db.setDescripcion(body.getDescripcion());
            return ResponseEntity.ok(service.guardar(db));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (service.obtenerPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        service.eliminar(id); return ResponseEntity.noContent().build();
    }
}
