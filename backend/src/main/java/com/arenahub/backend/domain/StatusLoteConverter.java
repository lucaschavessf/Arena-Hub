package com.arenahub.backend.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusLoteConverter implements AttributeConverter<StatusLote, String> {

    @Override
    public String convertToDatabaseColumn(StatusLote attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public StatusLote convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return null;
        }
        try {
            return StatusLote.valueOf(dbData.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Se encontrar algum valor antigo inválido que não está mapeado no enum,
            // você pode decidir se retorna null, um valor padrão, ou lança erro.
            // Retornaremos null ou logaremos o erro e retornaremos AGENDADO como fallback.
            return StatusLote.AGENDADO;
        }
    }
}
