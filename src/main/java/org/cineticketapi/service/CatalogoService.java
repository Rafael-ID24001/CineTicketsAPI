package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.CatalogoReqDto;
import org.cineticketapi.dto.CatalogoRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.CatalogoMapper;
import org.cineticketapi.model.Catalogo;
import org.cineticketapi.repository.CatalogoRepository;
import org.cineticketapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private CatalogoMapper catalogoMapper;

    public List<CatalogoRespDto> getCatalogos() {
        return catalogoRepository.findAll().stream()
                .map(catalogoMapper::toResp)
                .toList();
    }

    public Optional<CatalogoRespDto> findCatalogoById(Long idCatalogo) {
        Catalogo e = catalogoRepository.findById(idCatalogo)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, Constants.MSG_REGISTRO_NO_ENCONTRADO));
        return Optional.of(catalogoMapper.toResp(e));
    }

    public Optional<CatalogoRespDto> createCatalogo(CatalogoReqDto req) {
        // Regla simple anti-duplicado por tipo + código
        Optional<Catalogo> dup = catalogoRepository.findAll().stream()
                .filter(c -> c.getTipo().equalsIgnoreCase(req.getTipo())
                        && c.getCodigo().equalsIgnoreCase(req.getCodigo()))
                .findFirst();
        if (dup.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Ya existe un catálogo con el mismo tipo y código");
        }

        Catalogo created = catalogoRepository.save(catalogoMapper.forCreation(req));
        return Optional.of(catalogoMapper.toResp(created));
    }

    public Optional<CatalogoRespDto> updateCatalogo(Long idCatalogo, CatalogoReqDto req) {
        Catalogo exist = catalogoRepository.findById(idCatalogo)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, Constants.MSG_REGISTRO_NO_ENCONTRADO));

        Catalogo updated = catalogoRepository.save(catalogoMapper.forUpdate(exist, req));
        return Optional.of(catalogoMapper.toResp(updated));
    }

    public Long deleteCatalogo(Long idCatalogo) {
        Catalogo e = catalogoRepository.findById(idCatalogo)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, Constants.MSG_REGISTRO_NO_ENCONTRADO));
        catalogoRepository.delete(e);
        return idCatalogo;
    }
}
