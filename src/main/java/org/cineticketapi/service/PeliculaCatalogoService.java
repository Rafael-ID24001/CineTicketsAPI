package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.PeliculaCatalogoReqDto;
import org.cineticketapi.dto.PeliculaCatalogoRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.PeliculaCatalogoMapper;
import org.cineticketapi.model.peliculaCatalogo.PeliculaCatalogo;
import org.cineticketapi.model.peliculaCatalogo.PeliculaCatalogoId;
import org.cineticketapi.repository.PeliculaCatalogoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeliculaCatalogoService {

    private final PeliculaCatalogoRepository peliculaCatalogoRepository;
    private final PeliculaCatalogoMapper peliculaCatalogoMapper;

    public PeliculaCatalogoService(
            PeliculaCatalogoRepository peliculaCatalogoRepository,
            PeliculaCatalogoMapper peliculaCatalogoMapper
    ) {
        this.peliculaCatalogoRepository = peliculaCatalogoRepository;
        this.peliculaCatalogoMapper = peliculaCatalogoMapper;
    }

    // ===========================
    // GET ALL
    // ===========================
    public List<PeliculaCatalogoRespDto> getPeliculas() {
        return peliculaCatalogoRepository.findAll()
                .stream()
                .map(peliculaCatalogoMapper::toDto)
                .toList();
    }

    // ===========================
    // GET BY ID (COMPUESTO)
    // ===========================
    public Optional<PeliculaCatalogoRespDto> getById(Long idPelicula, Long idCatalogo) {

        PeliculaCatalogoId id = new PeliculaCatalogoId(idPelicula, idCatalogo);

        Optional<PeliculaCatalogo> item = peliculaCatalogoRepository.findById(id);

        if (item.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }

        return Optional.of(peliculaCatalogoMapper.toDto(item.get()));
    }

    // ===========================
    // CREATE
    // ===========================
    public Optional<PeliculaCatalogoRespDto> create(PeliculaCatalogoReqDto dto) {
        PeliculaCatalogo entity = peliculaCatalogoMapper.toEntity(dto);
        entity.setFechaAgregado(LocalDateTime.now());
        PeliculaCatalogo saved = peliculaCatalogoRepository.save(entity);
        return Optional.of(peliculaCatalogoMapper.toDto(saved));
    }

    // ===========================
    // DELETE
    // ===========================
    public void delete(Long idPelicula, Long idCatalogo) {

        PeliculaCatalogoId id = new PeliculaCatalogoId(idPelicula, idCatalogo);

        Optional<PeliculaCatalogo> entity = peliculaCatalogoRepository.findById(id);

        if (entity.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }

        peliculaCatalogoRepository.delete(entity.get());
    }
}
