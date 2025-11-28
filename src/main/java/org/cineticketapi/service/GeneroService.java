package org.cineticketapi.service;

import jakarta.transaction.Transactional;
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

@Service
@Transactional
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    @Autowired
    private GeneroMapper mapper;


    public List<GeneroDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
    public Optional<GeneroDto> obtenerPorId(Long id) {
        Optional<Genero> gen = repository.findById(id);
        return Optional.ofNullable(mapper.toDto(gen.get()));
    }

    public Optional<GeneroDto> guardar(GeneroDto g) {
        Genero gen = mapper.toEntity(g);
        return Optional.ofNullable(mapper.toDto(repository.save(gen)));
    }

    public Optional<GeneroDto> actualizar(GeneroDto g, Long idGen) {
        Genero genExist = repository.findById(idGen)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Genero gen = mapper.updateEntity(genExist, g);
        return Optional.ofNullable(mapper.toDto(repository.save(gen)));
    }

    public void eliminar(Long id) { repository.deleteById(id); }
}
