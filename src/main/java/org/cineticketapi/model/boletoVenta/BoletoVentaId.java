package org.cineticketapi.model.boletoVenta;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoletoVentaId implements Serializable {
    private Long idBoleto;
    private Long idVenta;
}
