package org.cineticketapi.service;

import org.cineticketapi.model.Cliente;
import org.cineticketapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * (GET /clientes)
     * Obtiene todos los clientes de la base de datos.
     */
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    /**
     * (POST /clientes)
     * Guarda un nuevo cliente en la base de datos.
     */
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * (GET /clientes/{id})
     * Busca un cliente por su ID.
     */
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * (PUT /clientes/{id})
     * Actualiza un cliente existente por su ID.
     */
    public Optional<Cliente> updateCliente(Long id, Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente clienteExistente = optionalCliente.get();
            clienteExistente.setNombre(clienteDetails.getNombre());
            clienteExistente.setEmail(clienteDetails.getEmail());
            clienteExistente.setTelefono(clienteDetails.getTelefono());

            Cliente clienteActualizado = clienteRepository.save(clienteExistente);
            return Optional.of(clienteActualizado);
        } else {
            return Optional.empty();
        }
    }

    /**
     * (DELETE /clientes/{id})
     * Borra un cliente por su ID.
     * @return true si fue borrado, false si no se encontró.
     */
    public boolean deleteCliente(Long id) {
        // 1. Verificar si el cliente existe
        if (clienteRepository.existsById(id)) {
            // 2. Si existe, borrarlo
            clienteRepository.deleteById(id);
            return true;
        } else {
            // 3. Si no existe, devolver 'false'
            return false;
        }
    }

} // <-- ¡Asegúrate de que solo haya UNO de estos al final!