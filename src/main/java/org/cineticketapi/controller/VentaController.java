package org.cineticketapi.controller;

import jakarta.validation.Valid;
import org.cineticketapi.dto.VentaRequestDTO;
import org.cineticketapi.dto.VentaResponseDTO;
import org.cineticketapi.model.Venta;
import org.cineticketapi.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importa todas las anotaciones web

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas") // <-- Ruta base para Ventas
public class VentaController {

    @Autowired
    private VentaService ventaService;


    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> getAllVentas() {
        List<VentaResponseDTO> ventas = ventaService.findAll();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getVentaById(@PathVariable Long id) {
        VentaResponseDTO venta = ventaService.findById(id);
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<VentaResponseDTO>> getVentasByCliente(@PathVariable Long idCliente) {
        List<VentaResponseDTO> ventas = ventaService.findByCliente(idCliente);
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> createVenta(@Valid @RequestBody VentaRequestDTO request) {
        VentaResponseDTO nuevaVenta = ventaService.create(request);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> updateVenta(
            @PathVariable Long id,
            @Valid @RequestBody VentaRequestDTO request) {
        VentaResponseDTO ventaActualizada = ventaService.update(id, request);
        return ResponseEntity.ok(ventaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
