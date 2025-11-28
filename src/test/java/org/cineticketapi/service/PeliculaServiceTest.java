package org.cineticketapi.service;

import org.cineticketapi.dto.PeliculaDto;
import org.cineticketapi.model.Pelicula;
import org.cineticketapi.repository.PeliculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PeliculaServiceTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaService peliculaService;

    private Pelicula pelicula;
    private PeliculaDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pelicula = Pelicula.builder()
                .idPelicula(1L)
                .titulo("Matrix")
                .duracion(120)
                .idGenero(1L)
                .clasificacion("PG-13")
                .sinopsis("Un clásico de ciencia ficción")
                .fechaEstreno(LocalDate.of(1999, 3, 31))
                .build();

        dto = PeliculaDto.builder()
                .idPelicula(1L)
                .titulo("Matrix")
                .duracion(120)
                .idGenero(1L)
                .clasificacion("PG-13")
                .sinopsis("Un clásico de ciencia ficción")
                .fechaEstreno(LocalDate.of(1999, 3, 31))
                .build();
    }

    @Test
    void listar_debeRetornarListaDePeliculas() {
        when(peliculaRepository.findAll()).thenReturn(List.of(pelicula));

        List<PeliculaDto> resultado = peliculaService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Matrix", resultado.get(0).getTitulo());
        verify(peliculaRepository).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_debeRetornarDto() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        Optional<PeliculaDto> resultado = peliculaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Matrix", resultado.get().getTitulo());
        verify(peliculaRepository).findById(1L);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_debeRetornarVacio() {
        when(peliculaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<PeliculaDto> resultado = peliculaService.obtenerPorId(99L);

        assertTrue(resultado.isEmpty());
        verify(peliculaRepository).findById(99L);
    }

    @Test
    void crear_debeGuardarYRetornarDto() {
        Pelicula peliculaGuardada = Pelicula.builder()
                .idPelicula(10L) // id asignado por la BD
                .titulo(dto.getTitulo())
                .duracion(dto.getDuracion())
                .idGenero(dto.getIdGenero())
                .clasificacion(dto.getClasificacion())
                .sinopsis(dto.getSinopsis())
                .fechaEstreno(dto.getFechaEstreno())
                .build();

        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(peliculaGuardada);

        PeliculaDto resultado = peliculaService.crear(dto);

        assertNotNull(resultado.getIdPelicula());
        assertEquals("Matrix", resultado.getTitulo());
        verify(peliculaRepository).save(any(Pelicula.class));
    }

    @Test
    void actualizar_cuandoNoExiste_debeLanzarExcepcion() {
        when(peliculaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> peliculaService.actualizar(99L, dto));
    }

    @Test
    void actualizar_cuandoExiste_debeGuardarCambios() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(pelicula);

        PeliculaDto resultado = peliculaService.actualizar(1L, dto);

        assertEquals("Matrix", resultado.getTitulo());
        verify(peliculaRepository).findById(1L);
        verify(peliculaRepository).save(any(Pelicula.class));
    }

    @Test
    void eliminar_cuandoNoExiste_debeLanzarExcepcion() {
        when(peliculaRepository.existsById(99L)).thenReturn(false);

        assertThrows(NoSuchElementException.class,
                () -> peliculaService.eliminar(99L));
    }

    @Test
    void eliminar_cuandoExiste_debeLlamarDelete() {
        when(peliculaRepository.existsById(1L)).thenReturn(true);

        peliculaService.eliminar(1L);

        verify(peliculaRepository).deleteById(1L);
    }
}

