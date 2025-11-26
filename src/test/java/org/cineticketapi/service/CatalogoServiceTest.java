package org.cineticketapi.service;

import org.cineticketapi.dto.CatalogoReqDto;
import org.cineticketapi.dto.CatalogoRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.CatalogoMapper;
import org.cineticketapi.model.Catalogo;
import org.cineticketapi.repository.CatalogoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogoServiceTest {

    @Mock
    private CatalogoRepository catalogoRepository;

    @Mock
    private CatalogoMapper catalogoMapper;

    @InjectMocks
    private CatalogoService catalogoService;

    private Catalogo entity;
    private CatalogoRespDto respDto;
    private CatalogoReqDto reqDto;

    @BeforeEach
    void setUp() {
        entity = Catalogo.builder()
                .idCatalogo(1L)
                .tipo("TIPO")
                .codigo("COD001")
                .descripcion("Descripción de prueba")
                .activo(true)
                .build();

        respDto = CatalogoRespDto.builder()
                .idCatalogo(1L)
                .tipo("TIPO")
                .codigo("COD001")
                .descripcion("Descripción de prueba")
                .activo(true)
                .build();

        reqDto = new CatalogoReqDto(
                "TIPO",
                "COD001",
                "Descripción de prueba",
                true
        );
    }

    @Test
    void getCatalogos_debeRetornarListaDeCatalogos() {
        when(catalogoRepository.findAll()).thenReturn(List.of(entity));
        when(catalogoMapper.toResp(entity)).thenReturn(respDto);

        List<CatalogoRespDto> result = catalogoService.getCatalogos();

        assertEquals(1, result.size());
        assertEquals("COD001", result.get(0).getCodigo());
        verify(catalogoRepository).findAll();
        verify(catalogoMapper).toResp(entity);
    }

    @Test
    void findCatalogoById_cuandoExiste_devuelveDto() {
        when(catalogoRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(catalogoMapper.toResp(entity)).thenReturn(respDto);

        Optional<CatalogoRespDto> result = catalogoService.findCatalogoById(1L);

        assertTrue(result.isPresent());
        assertEquals("TIPO", result.get().getTipo());
        verify(catalogoRepository).findById(1L);
    }

    @Test
    void findCatalogoById_cuandoNoExiste_lanzaApiException() {
        when(catalogoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ApiException.class,
                () -> catalogoService.findCatalogoById(99L));
    }

    @Test
    void createCatalogo_sinDuplicado_creaCatalogo() {
        when(catalogoRepository.findAll()).thenReturn(Collections.emptyList());
        when(catalogoMapper.forCreation(reqDto)).thenReturn(entity);
        when(catalogoRepository.save(entity)).thenReturn(entity);
        when(catalogoMapper.toResp(entity)).thenReturn(respDto);

        Optional<CatalogoRespDto> result = catalogoService.createCatalogo(reqDto);

        assertTrue(result.isPresent());
        assertEquals("COD001", result.get().getCodigo());
        verify(catalogoRepository).findAll();
        verify(catalogoRepository).save(entity);
    }

    @Test
    void createCatalogo_conDuplicado_lanzaApiException() {
        Catalogo duplicado = Catalogo.builder()
                .idCatalogo(2L)
                .tipo("TIPO")
                .codigo("COD001")
                .descripcion("Otro")
                .activo(true)
                .build();

        when(catalogoRepository.findAll()).thenReturn(List.of(duplicado));

        assertThrows(ApiException.class,
                () -> catalogoService.createCatalogo(reqDto));

        verify(catalogoRepository, never()).save(any());
    }

    @Test
    void updateCatalogo_cuandoExiste_actualizaCatalogo() {
        Catalogo actualizado = Catalogo.builder()
                .idCatalogo(1L)
                .tipo("TIPO2")
                .codigo("COD001")
                .descripcion("Actualizado")
                .activo(false)
                .build();

        when(catalogoRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(catalogoMapper.forUpdate(entity, reqDto)).thenReturn(actualizado);
        when(catalogoRepository.save(actualizado)).thenReturn(actualizado);
        when(catalogoMapper.toResp(actualizado)).thenReturn(respDto);

        Optional<CatalogoRespDto> result = catalogoService.updateCatalogo(1L, reqDto);

        assertTrue(result.isPresent());
        verify(catalogoRepository).findById(1L);
        verify(catalogoRepository).save(actualizado);
    }

    @Test
    void updateCatalogo_cuandoNoExiste_lanzaApiException() {
        when(catalogoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ApiException.class,
                () -> catalogoService.updateCatalogo(99L, reqDto));
    }

    // ========= deleteCatalogo =========
    @Test
    void deleteCatalogo_cuandoExiste_eliminaYDevuelveId() {
        when(catalogoRepository.findById(1L)).thenReturn(Optional.of(entity));

        Long deletedId = catalogoService.deleteCatalogo(1L);

        assertEquals(1L, deletedId);
        verify(catalogoRepository).delete(entity);
    }

    @Test
    void deleteCatalogo_cuandoNoExiste_lanzaApiException() {
        when(catalogoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApiException.class,
                () -> catalogoService.deleteCatalogo(1L));

        verify(catalogoRepository, never()).delete(any());
    }
}
