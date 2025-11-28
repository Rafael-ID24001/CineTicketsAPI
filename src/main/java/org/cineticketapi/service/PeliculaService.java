package org.cineticketapi.service;

import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.PeliculaDto;
import org.cineticketapi.model.Pelicula;
import org.cineticketapi.repository.PeliculaRepository;
import org.cineticketapi.util.enums.ModelEnums;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PeliculaService {

    private final PeliculaRepository repository;

    public List<PeliculaDto> listar() {
        return repository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<PeliculaDto> obtenerPorId(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public PeliculaDto crear(PeliculaDto dto) {
        Pelicula entity = toEntity(dto);
        entity.setIdPelicula(null); // asegurar creación
        entity.setEstado(ModelEnums.EstadoPelicula.ACTIVA);
        Pelicula saved = repository.save(entity);
        return toDto(saved);
    }

    public PeliculaDto actualizar(Long id, PeliculaDto dto) {
        Pelicula existente = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pelicula no encontrada con id " + id));
        // actualizar campos
        existente.setTitulo(dto.getTitulo());
        existente.setDuracion(dto.getDuracion());
        existente.setIdGenero(dto.getIdGenero());
        existente.setClasificacion(dto.getClasificacion());
        existente.setSinopsis(dto.getSinopsis());
        existente.setFechaEstreno(dto.getFechaEstreno());

        Pelicula actualizado = repository.save(existente);
        return toDto(actualizado);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Pelicula no encontrada con id " + id);
        }
        repository.deleteById(id);
    }


    private PeliculaDto toDto(Pelicula p) {
        return PeliculaDto.builder()
                .idPelicula(p.getIdPelicula())
                .titulo(p.getTitulo())
                .duracion(p.getDuracion())
                .idGenero(p.getIdGenero())
                .clasificacion(p.getClasificacion())
                .sinopsis(p.getSinopsis())
                .fechaEstreno(p.getFechaEstreno())
                .build();
    }

    private Pelicula toEntity(PeliculaDto d) {
        return Pelicula.builder()
                .idPelicula(d.getIdPelicula())
                .titulo(d.getTitulo())
                .duracion(d.getDuracion())
                .idGenero(d.getIdGenero())
                .clasificacion(d.getClasificacion())
                .sinopsis(d.getSinopsis())
                .fechaEstreno(d.getFechaEstreno())
                .build();
    }
}
