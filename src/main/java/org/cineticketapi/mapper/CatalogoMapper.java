package org.cineticketapi.mapper;

import org.cineticketapi.dto.CatalogoReqDto;
import org.cineticketapi.dto.CatalogoRespDto;
import org.cineticketapi.model.Catalogo;
import org.springframework.stereotype.Component;

@Component
public class CatalogoMapper {

    public CatalogoRespDto toResp(Catalogo e) {
        if (e == null) return null;
        return CatalogoRespDto.builder()
                .idCatalogo(e.getIdCatalogo())
                .tipo(e.getTipo())
                .codigo(e.getCodigo())
                .descripcion(e.getDescripcion())
                .activo(e.getActivo())
                .build();
    }

    public Catalogo forCreation(CatalogoReqDto d) {
        Catalogo e = new Catalogo();
        e.setTipo(d.getTipo());
        e.setCodigo(d.getCodigo());
        e.setDescripcion(d.getDescripcion());
        e.setActivo(d.getActivo() != null ? d.getActivo() : true);
        return e;
    }

    public Catalogo forUpdate(Catalogo target, CatalogoReqDto d) {
        if (d.getTipo() != null)        target.setTipo(d.getTipo());
        if (d.getCodigo() != null)      target.setCodigo(d.getCodigo());
        if (d.getDescripcion() != null) target.setDescripcion(d.getDescripcion());
        if (d.getActivo() != null)      target.setActivo(d.getActivo());
        return target;
    }
}
