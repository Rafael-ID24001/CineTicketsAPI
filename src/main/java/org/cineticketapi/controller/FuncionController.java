package org.cineticketapi.controller;

import org.cineticketapi.service.FuncionService;
import org.cineticketapi.view.FuncionDetalleView; // <-- 1. Importa tu View
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funciones") // <-- Ruta base para todas las funciones
public class FuncionController {

    @Autowired
    private FuncionService funcionService; // <-- 2. Inyecta el Servicio

    /**
     * Endpoint para OBTENER una función por ID con sus detalles.
     * EJ: GET http://localhost:8080/api/funciones/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<FuncionDetalleView> getFuncionDetallePorId(@PathVariable Long id) {
        
        // 3. Llama al método del servicio
        FuncionDetalleView funcionView = funcionService.obtenerFuncionDetallePorId(id);
        
        // 4. Retorna el View. Spring Boot lo convertirá a JSON automáticamente.
        return ResponseEntity.ok(funcionView);
    }

    /**
     * Endpoint para OBTENER TODAS las funciones con sus detalles.
     * EJ: GET http://localhost:8080/api/funciones
     */
    @GetMapping
    public ResponseEntity<List<FuncionDetalleView>> getAllFuncionesDetalle() {
        
        // 5. Llama al otro método del servicio
        List<FuncionDetalleView> funciones = funcionService.obtenerTodasLasFuncionesDetalle();
        
        return ResponseEntity.ok(funciones);
    }

    // ... Aquí irían tus otros endpoints (POST, PUT, DELETE)
    // Por ejemplo, para crear una función:
    /*
    @PostMapping
    public ResponseEntity<Funcion> createFuncion(@RequestBody FuncionCreateDTO dto) {
        // ... lógica para convertir DTO a entidad y llamar al servicio ...
        Funcion nuevaFuncion = funcionService.crearFuncion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFuncion);
    }
    */
}