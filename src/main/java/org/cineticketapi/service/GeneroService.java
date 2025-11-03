package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.mapper.GeneroMapper;
import org.cineticketapi.model.Genero;
import org.cineticketapi.repository.GeneroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroDto crear(GeneroDto dto) {

        try {
            Optional<Genero> existente = generoRepository.findByNombre(dto.getNombre());
            if (existente.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El género ya existe con ese nombre");
            }
        } catch (NoSuchMethodError | UnsupportedOperationException e) {
            // Si el repo no tiene findByNombre, ignoramos el check
        }
        Genero entity = GeneroMapper.toEntity(dto);
        Genero saved = generoRepository.save(entity);
        return GeneroMapper.toDto(saved);
    }

    public GeneroDto actualizar(Long id, GeneroDto dto) {
        Genero entity = generoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Género no encontrado"));
        GeneroMapper.updateEntity(entity, dto);
        Genero saved = generoRepository.save(entity);
        return GeneroMapper.toDto(saved);
    }

    public void eliminar(Long id) {
        Genero entity = generoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Género no encontrado"));
        generoRepository.delete(entity);
    }

    public GeneroDto obtener(Long id) {
        Genero entity = generoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Género no encontrado"));
        return GeneroMapper.toDto(entity);
    }

    public List<GeneroDto> listar() {
        return generoRepository.findAll()
                .stream()
                .map(GeneroMapper::toDto)
                .collect(Collectors.toList());
    }
}
