package org.cineticketapi.controller;

import org.cineticketapi.model.Boleto;
import org.cineticketapi.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importa todas las anotaciones web

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boletos") // <-- Ruta base para Boletos
public class BoletoController {

    @Autowired
 private final BoletoService boletoService;

    @GetMapping
    public ResponseEntity<List<BoletoDTO>> getAll() {
        return ResponseEntity.ok(boletoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(boletoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BoletoDTO> create(@RequestBody BoletoDTO dto) {
        return ResponseEntity.ok(boletoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoletoDTO> update(@PathVariable Long id, @RequestBody BoletoDTO dto) {
        return ResponseEntity.ok(boletoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boletoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
