package org.cineticketapi.service;

import org.cineticketapi.model.Genero;
import org.cineticketapi.repository.GeneroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GeneroServiceTest {

    @Mock
    private GeneroRepository generoRepository;

    @InjectMocks
    private GeneroService generoService;

    private Genero genero;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        genero = Genero.builder()
                .idGenero(1L)
                .nombre("Acción")
                .descripcion("Películas con mucha acción")
                .build();
    }

    @Test
    void listar_debeRetornarListaDeGeneros() {
        when(generoRepository.findAll()).thenReturn(List.of(genero));

        List<Genero> resultado = generoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Acción", resultado.get(0).getNombre());
        verify(generoRepository).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_debeRetornarGenero() {
        when(generoRepository.findById(1L)).thenReturn(Optional.of(genero));

        Optional<Genero> resultado = generoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Acción", resultado.get().getNombre());
        verify(generoRepository).findById(1L);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_debeRetornarVacio() {
        when(generoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Genero> resultado = generoService.obtenerPorId(99L);

        assertTrue(resultado.isEmpty());
        verify(generoRepository).findById(99L);
    }

    @Test
    void guardar_debeLlamarSaveYRetornarGenero() {
        when(generoRepository.save(any(Genero.class))).thenReturn(genero);

        Genero resultado = generoService.guardar(genero);

        assertNotNull(resultado);
        assertEquals("Acción", resultado.getNombre());
        verify(generoRepository).save(genero);
    }

    @Test
    void eliminar_debeLlamarDeleteById() {
        doNothing().when(generoRepository).deleteById(1L);

        generoService.eliminar(1L);

        verify(generoRepository).deleteById(1L);
    }
}

