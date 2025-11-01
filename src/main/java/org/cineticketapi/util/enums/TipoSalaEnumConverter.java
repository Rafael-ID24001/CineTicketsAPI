package org.cineticketapi.util.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoSalaEnumConverter implements AttributeConverter<ModelEnums.TipoSala, String> {
    @Override
    public String convertToDatabaseColumn(ModelEnums.TipoSala tipoSala) {
        if(tipoSala == null) return null;
        return tipoSala.getDbValue();
    }

    @Override
    public ModelEnums.TipoSala convertToEntityAttribute(String dbValue) {
        if(dbValue == null) return null;
        return ModelEnums.TipoSala.fromDbValue(dbValue);
    }
}
