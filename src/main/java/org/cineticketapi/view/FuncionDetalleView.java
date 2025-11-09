package org.cineticketapi.view;

import java.time.LocalDateTime;
import lombok.Getter; // <-- 1. IMPORTA GETTER
import lombok.Setter; // <-- (Opcional, pero buena idea)

@Getter // <-- 2. AÑADE ESTA ANOTACIÓN
@Setter // <-- (Opcional, pero buena idea)
public class FuncionDetalleView {

    private Long idFuncion;
    private LocalDateTime fechaHora;
    private Double precio;
    private String tituloPelicula;
    private String nombreSala;
    private String clasificacion;

    // El constructor que ya tenías
    public FuncionDetalleView(Long idFuncion, LocalDateTime fechaHora, 
                             Double precio, String tituloPelicula, 
                             String nombreSala, String clasificacion) {
        
        this.idFuncion = idFuncion;
        this.fechaHora = fechaHora;
        this.precio = precio;
        this.tituloPelicula = tituloPelicula;
        this.nombreSala = nombreSala;
        this.clasificacion = clasificacion;
    }

    // ¡NO HAGAS NADA MÁS! Lombok generará los getters
    // (public Long getIdFuncion() { ... }, etc.) por ti.
}
