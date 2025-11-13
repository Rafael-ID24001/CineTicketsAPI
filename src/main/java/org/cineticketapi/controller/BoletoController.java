package org.cineticketapi.controller;

import jakarta.validation.Valid;
import org.cineticketapi.dto.BoletoRequestDTO;
import org.cineticketapi.dto.BoletoResponseDTO;
import org.cineticketapi.model.Boleto;
import org.cineticketapi.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*; // Importa todas las anotaciones web

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boletos") // <-- Ruta base para Boletos
@Validated
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

    @GetMapping
    public ResponseEntity<List<BoletoResponseDTO>> getAllBoletos() {
        List<BoletoResponseDTO> boletos = boletoService.findAll();
        return ResponseEntity.ok(boletos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletoResponseDTO> getBoletoById(@PathVariable Long id) {
        BoletoResponseDTO boleto = boletoService.findById(id);
        return ResponseEntity.ok(boleto);
    }

    @PostMapping
    public ResponseEntity<BoletoResponseDTO> createBoleto(@Valid @RequestBody BoletoRequestDTO boletoRequestDTO) {
        BoletoResponseDTO createdBoleto = boletoService.create(boletoRequestDTO);
        return new ResponseEntity<>(createdBoleto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoletoResponseDTO> updateBoleto(
            @PathVariable Long id,
            @Valid @RequestBody BoletoRequestDTO boletoRequestDTO) {
        BoletoResponseDTO updatedBoleto = boletoService.update(id, boletoRequestDTO);
        return ResponseEntity.ok(updatedBoleto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoleto(@PathVariable Long id) {
        boletoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<BoletoResponseDTO>> getBoletosByCliente(@PathVariable Long idCliente) {
        List<BoletoResponseDTO> boletos = boletoService.findByCliente(idCliente);
        return ResponseEntity.ok(boletos);
    }

    @GetMapping("/funcion/{idFuncion}")
    public ResponseEntity<List<BoletoResponseDTO>> getBoletosByFuncion(@PathVariable Long idFuncion) {
        List<BoletoResponseDTO> boletos = boletoService.findByFuncion(idFuncion);
        return ResponseEntity.ok(boletos);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
