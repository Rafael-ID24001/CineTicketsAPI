package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.mapper.GeneroMapper;
import org.cineticketapi.model.Genero;
import org.cineticketapi.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeneroService {
@Autowired
    private final GeneroRepository repository;
    public GeneroService(GeneroRepository repository) { this.repository = repository; }
    public List<Genero> listar() { return repository.findAll(); }
    public Optional<Genero> obtenerPorId(Long id) { return repository.findById(id); }
    public Genero guardar(Genero g) { return repository.save(g); }
    public void eliminar(Long id) { repository.deleteById(id); }
}
