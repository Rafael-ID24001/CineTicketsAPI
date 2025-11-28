package org.cineticketapi.service;

import org.cineticketapi.dto.GeneroDto;
import org.cineticketapi.mapper.GeneroMapper;
import org.cineticketapi.model.Genero;
import org.cineticketapi.repository.GeneroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GeneroServiceTest {

    @Mock
    private GeneroRepository generoRepository;
    @Spy
    private GeneroMapper mapper;

    @InjectMocks
    private GeneroService generoService;

    private Genero genero;
    private GeneroDto generoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        genero = Genero.builder()
                .idGenero(1L)
                .nombre("Acción")
                .descripcion("Películas con mucha acción")
                .build();

        generoDto = GeneroDto.builder()
                .nombre(genero.getNombre())
                .descripcion(genero.getDescripcion())
                .build();
    }

    @Test
    void listar_debeRetornarListaDeGeneros() {
        when(generoRepository.findAll()).thenReturn(List.of(genero));

        List<GeneroDto> resultado = generoService.listar();

        assertEquals(1, resultado.size());
        verify(generoRepository).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_debeRetornarGenero() {
        when(generoRepository.findById(anyLong())).thenReturn(Optional.of(genero));

        Optional<GeneroDto> resultado = generoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        verify(generoRepository).findById(1L);
    }

    @Test
    void guardar_debeLlamarSaveYRetornarGeneroDto() {
        when(generoRepository.save(any(Genero.class))).thenReturn(genero);

        Optional<GeneroDto> resultado = generoService.guardar(generoDto);

        assertNotNull(resultado);
        assertEquals("Acción", resultado.get().getNombre());
        verify(generoRepository).save(any(Genero.class));
    }

    @Test
    void guardar_debeLlamarActualizarYRetornarGeneroDto() {
        when(generoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(genero));
        when(generoRepository.save(any(Genero.class))).thenReturn(genero);

        Optional<GeneroDto> resultado = generoService.actualizar(generoDto, genero.getIdGenero());

        assertNotNull(resultado);
        assertEquals("Acción", resultado.get().getNombre());
        verify(generoRepository).save(genero);
    }

    @Test
    void eliminar_debeLlamarDeleteById() {
        doNothing().when(generoRepository).deleteById(1L);

        generoService.eliminar(1L);

        verify(generoRepository).deleteById(1L);
    }
}

