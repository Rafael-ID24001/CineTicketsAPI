package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.cineticketapi.util.enums.TipoSalaEnumConverter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SALA_DE_CINE")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaDeCine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sala_de_cine")
    @SequenceGenerator(name = "seq_sala_de_cine", sequenceName = "seq_sala_de_cine", allocationSize = 1)
    @Column(name = "id_sala")
    private Long idSala;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "capacidad_total", nullable = false)
    private Integer capacidadTotal;

    @Convert(converter = TipoSalaEnumConverter.class)
    @Column(name = "tipo_sala", nullable = false)
    private ModelEnums.TipoSala tipoSala;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ModelEnums.EstadoSala estado = ModelEnums.EstadoSala.DISPONIBLE;

}
