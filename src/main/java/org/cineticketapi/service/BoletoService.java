package org.cineticketapi.service;

import org.cineticketapi.model.Boleto;
import org.cineticketapi.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    /**
     * (GET /boletos)
     * Obtiene todos los boletos.
     */
    public List<Boleto> getAllBoletos() {
        return boletoRepository.findAll();
    }

    /**
     * (GET /boletos/{id})
     * Busca un boleto por su ID.
     */
    public Optional<Boleto> getBoletoById(Long id) {
        return boletoRepository.findById(id);
    }

    /**
     * (POST /boletos)
     * Guarda un nuevo boleto.
     * Nota: El JSON debe incluir IDs válidos para funcion, cliente y asiento.
     */
    public Boleto createBoleto(Boleto boleto) {
        // Aquí se podrían agregar validaciones (ej. verificar que el asiento esté disponible)
        return boletoRepository.save(boleto);
    }

    /**
     * (PUT /boletos/{id})
     * Actualiza un boleto existente.
     * (Solo actualiza los campos simples como 'estado', las llaves foráneas son más complejas)
     */
    public Optional<Boleto> updateBoleto(Long id, Boleto boletoDetails) {
        Optional<Boleto> optionalBoleto = boletoRepository.findById(id);

        if (optionalBoleto.isPresent()) {
            Boleto boletoExistente = optionalBoleto.get();
            
            // Actualizamos los campos que pueden cambiar
            boletoExistente.setEstado(boletoDetails.getEstado());
            // (Puedes agregar más setters aquí si es necesario)
            
            Boleto boletoActualizado = boletoRepository.save(boletoExistente);
            return Optional.of(boletoActualizado);
        } else {
            return Optional.empty(); // No se encontró el boleto
        }
    }

    /**
     * (DELETE /boletos/{id})
     * Borra un boleto por su ID.
     * @return true si fue borrado, false si no se encontró.
     */
    public boolean deleteBoleto(Long id) {
        if (boletoRepository.existsById(id)) {
            boletoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}