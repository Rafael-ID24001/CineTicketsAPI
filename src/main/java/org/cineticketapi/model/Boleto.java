package org.cineticketapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.cineticketapi.util.enums.ModelEnums;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "BOLETO")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_boleto")
    @SequenceGenerator(name = "seq_boleto", sequenceName = "seq_boleto", allocationSize = 1)
    @Column(name = "id_boleto")
    private Long idBoleto;

    @Column(name = "id_funcion", nullable = false)
    private Long idFuncion;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @JoinColumn(name = "id_asiento", nullable = false)
    private Long idAsiento;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ModelEnums.EstadoBoleto estado = ModelEnums.EstadoBoleto.VALIDO;

}