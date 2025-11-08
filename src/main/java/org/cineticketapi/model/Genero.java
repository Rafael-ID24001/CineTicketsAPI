package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "GENERO")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_genero")
    @SequenceGenerator(name = "seq_genero", sequenceName = "seq_genero", allocationSize = 1)
    @Column(name = "id_genero")
    private Long idGenero;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}
