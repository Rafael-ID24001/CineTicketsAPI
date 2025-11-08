package org.cineticketapi.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ModelEnums {

    public enum TipoAsiento {
        REGULAR, VIP, DISCAPACITADO
    }
    public enum EstadoAsiento {
        DISPONIBLE, OCUPADO, MANTENIMIENTO
    }

    public enum EstadoBoleto {
        VALIDO, USADO, CANCELADO, REEMBOLSADO
    }

    public enum EstadoFuncion {
        PROGRAMADA, EN_CURSO, FINALIZADA, CANCELADA
    }

    public enum EstadoPelicula {
        ACTIVA, INACTIVA, PROXIMAMENTE
    }

    public enum EstadoSala {
        DISPONIBLE, MANTENIMIENTO, FUERA_DE_SERVICIO
    }

    public enum TipoSala {
        REGULAR("REGULAR"),
        VIP("VIP"),
        IMAX("IMAX"),
        _3D("3D"),
        _4DX("4DX");

        private String dbValue;

        TipoSala(String dbValue) {
            this.dbValue = dbValue;
        }

        @JsonValue  // esto hace que Jackson use getDbValue()
        public String getDbValue() {
            return dbValue;
        }

        @Override
        public String toString() {
            return dbValue;
        }

        public static TipoSala fromDbValue(String dbValue) {
            for (TipoSala t : values()) {
                if (t.getDbValue().equals(dbValue)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("No hay enum para el valor: " + dbValue);
        }

        // para deserializar desde JSON, sirve en el DTO
        @JsonCreator
        public static TipoSala fromString(String value) {
            return fromDbValue(value);
        }
    }

    public enum MetodoPago {
        EFECTIVO, TARJETA, TRANSFERENCIA, DIGITAL
    }
}
