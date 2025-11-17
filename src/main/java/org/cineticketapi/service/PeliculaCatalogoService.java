package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.PeliculaCatalogoReqDto;
import org.cineticketapi.dto.PeliculaCatalogoRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.PeliculaCatalogoMapper;
import org.cineticketapi.model.PeliculaCatalogo;
import org.cineticketapi.repository.PeliculaCatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeliculaCatalogoService {

    @Autowired
    private PeliculaCatalogoRepository peliculaCatalogoRepository;

    @Autowired
    private PeliculaCatalogoMapper peliculaCatalogoMapper;

    public List<PeliculaCatalogoRespDto> getPeliculas() {

        List<PeliculaCatalogo> lista = peliculaCatalogoRepository.findAll();
        return lista.stream()
                .map(peliculaCatalogoMapper::toDto)
                .toList();
    }

    public Optional<PeliculaCatalogoRespDto> getById(Long idPelicula) {
        Optional<PeliculaCatalogo> item = peliculaCatalogoRepository.findById(idPelicula);
        if (!item.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return Optional.ofNullable(peliculaCatalogoMapper.toDto(item.get()));
    }

    public Optional<PeliculaCatalogoRespDto> create(PeliculaCatalogoReqDto dto) {
        PeliculaCatalogo entity = peliculaCatalogoMapper.toEntity(dto);
        PeliculaCatalogo saved = peliculaCatalogoRepository.save(entity);
        return Optional.ofNullable(peliculaCatalogoMapper.toDto(saved));
    }

    public Long delete(Long idPelicula) {
        Optional<PeliculaCatalogo> entity = peliculaCatalogoRepository.findById(idPelicula);
        if (!entity.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        peliculaCatalogoRepository.delete(entity.get());
        return idPelicula;
    }
}