package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ASIENTO")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_asiento")
    @SequenceGenerator(name = "seq_asiento", sequenceName = "seq_asiento", allocationSize = 1)
    @Column(name = "id_asiento")
    private Long idAsiento;

    @Column(name = "id_sala", nullable = false)
    private Long idSalaCine;

    @Column(name = "fila", nullable = false)
    private String fila;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_asiento", nullable = false)
    private ModelEnums.TipoAsiento tipoAsiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ModelEnums.EstadoAsiento estado = ModelEnums.EstadoAsiento.DISPONIBLE;

}

