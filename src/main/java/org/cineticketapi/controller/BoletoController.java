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
    private BoletoService boletoService;

    /**
     * GET /boletos
     * Endpoint para obtener todos los boletos.
     */
    @GetMapping
    public List<Boleto> getAllBoletos() {
        return boletoService.getAllBoletos();
    }

    /**
     * GET /boletos/{id}
     * Endpoint para obtener un boleto específico por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Boleto> getBoletoById(@PathVariable Long id) {
        Optional<Boleto> boleto = boletoService.getBoletoById(id);
        return boleto.map(ResponseEntity::ok) // Forma corta de "if (presente) return 200 OK"
                     .orElseGet(() -> ResponseEntity.notFound().build()); // Forma corta de "else return 404 NOT FOUND"
    }

    /**
     * POST /boletos
     * Endpoint para crear un nuevo boleto.
     * El Body del JSON debe contener idFuncion, idCliente, idAsiento.
     */
    @PostMapping
    public ResponseEntity<Boleto> createBoleto(@RequestBody Boleto boleto) {
        // Nota: Faltaría validar que los IDs (funcion, cliente, asiento) sean válidos
        try {
            Boleto nuevoBoleto = boletoService.createBoleto(boleto);
            return new ResponseEntity<>(nuevoBoleto, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            // Esto atrapará errores si, por ejemplo, los IDs (cliente, funcion) no existen
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    /**
     * PUT /boletos/{id}
     * Endpoint para actualizar un boleto (ej. cambiar su estado).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Boleto> updateBoleto(@PathVariable Long id, @RequestBody Boleto boletoDetails) {
        Optional<Boleto> boletoActualizado = boletoService.updateBoleto(id, boletoDetails);
        return boletoActualizado.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE /boletos/{id}
     * Endpoint para borrar un boleto por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoleto(@PathVariable Long id) {
        if (boletoService.deleteBoleto(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
