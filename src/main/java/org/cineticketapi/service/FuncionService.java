package org.cineticketapi.service;

import org.cineticketapi.model.Funcion; // <-- Necesario para el CRUD
import org.cineticketapi.repository.FuncionRepository;
import org.cineticketapi.view.FuncionDetalleView; // <-- 1. Importa tu View
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    // --- MÉTODOS DE LECTURA (GET) USANDO EL VIEW ---

    /**
     * Obtiene una función con detalles (película y sala) por su ID.
     */
    public FuncionDetalleView obtenerFuncionDetallePorId(Long funcionId) {
        
        // 2. Llama al nuevo método del repositorio
        return funcionRepository.findFuncionDetalleById(funcionId)
            .orElseThrow(() -> new RuntimeException(
                "Función no encontrada con id: " + funcionId));
    }
    
    /**
     * Obtiene TODAS las funciones con sus detalles.
     */
    public List<FuncionDetalleView> obtenerTodasLasFuncionesDetalle() {
        
        // 3. Llama al otro método nuevo del repositorio
        return funcionRepository.findAllFuncionDetalle();
    }
    
    // --- MÉTODOS DE ESCRITURA (CRUD) USANDO LA ENTIDAD ---
    // (Estos métodos de crear, actualizar o borrar
    //  siguen usando la entidad 'Funcion' normal)

    /**
     * Crea una nueva función.
     * (Aquí recibirías un DTO/View de entrada, pero 
     * guardas la entidad 'Funcion')
     */
    public Funcion crearFuncion(Funcion funcion) {
        // Aquí iría tu lógica para guardar...
        // Por ejemplo, recibir un 'FuncionCreateDTO',
        // convertirlo a 'Funcion', y guardarlo.
        return funcionRepository.save(funcion);
    }
    
    /**
     * Borra una función por ID.
     */
    public void borrarFuncion(Long funcionId) {
        funcionRepository.deleteById(funcionId);
    }

    // ... etc. para tus otros métodos CRUD ...
}