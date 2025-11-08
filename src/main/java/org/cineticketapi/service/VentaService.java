package org.cineticketapi.service;

import org.cineticketapi.model.Venta;
import org.cineticketapi.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    // GET (Todos)
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    // GET (Uno por ID)
    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }

    // POST (Crear)
    public Venta createVenta(Venta venta) {
        // Aquí se podrían agregar validaciones
        return ventaRepository.save(venta);
    }

    // PUT (Actualizar)
    public Optional<Venta> updateVenta(Long id, Venta ventaDetails) {
        Optional<Venta> optionalVenta = ventaRepository.findById(id);

        if (optionalVenta.isPresent()) {
            Venta ventaExistente = optionalVenta.get();
            
            // Actualizamos los campos (Nota: cambiar el cliente o el total es complejo)
            ventaExistente.setMetodoPago(ventaDetails.getMetodoPago());
            
            Venta ventaActualizada = ventaRepository.save(ventaExistente);
            return Optional.of(ventaActualizada);
        } else {
            return Optional.empty();
        }
    }

    // DELETE
    public boolean deleteVenta(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}